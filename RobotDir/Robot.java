//This class makes an object for the robot
package RobotDir;
import java.lang.Math.*;
import Structure.*;
import java.util.*;


public class Robot{

	//Robot characteristics
	public double height;
	public double width;
	public double speed;
	public double turnspeed;
	public Sensor mainSensor;
	public double range;
	public double angleRange;
	public double res;

	//Robot position variables
	public Coordinate center;
	public double theta;
	public Coordinate corners[] = new Coordinate[4];
	public LineSeg edges[] = new LineSeg[4];
	public Coordinate newSense[];
	public ArrayList<Coordinate> steps;
	public ArrayList<Double> angles;
	
	//Environment variables
	ArrayList<Landmark> landmarks;
	Coordinate goalPos;
	
	//Navigate Variables
	public double turnRange;
	ArrayList<Coordinate> hits = new ArrayList();
	public long its;

	public Robot(double x, double y, double theta, double height, double width, double speed, double turnspeed, ArrayList<Landmark> landmarks, Coordinate GoalPos) {
		center = new Coordinate(x,y);
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.theta = theta;
		this.turnspeed = turnspeed;
		this.goalPos = goalPos;		
		updateCorners();
	}

	//res needs to be odd,
	public Robot(double x, double y, double theta, double height, double width, double speed, double turnspeed, double range, double angleRange, double facing, Coordinate pos, double res, ArrayList<Landmark> landmarks, Coordinate goalPos) {
		center = new Coordinate(x,y);
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.theta = theta;
		this.landmarks = landmarks;
		this.turnspeed = turnspeed;
		this.goalPos = goalPos;
		this.range = range;
		this.angleRange = angleRange;
		this.res = res;
		updateCorners();
		newSense = new Coordinate[(int)res];
		mainSensor = new Sensor(range, angleRange, facing, pos, res, landmarks);
		steps = new ArrayList();
		angles = new ArrayList();
	}	
	
	//checks to make sure the gap is wide enough
 	public boolean checkGap(Coordinate reading, int index){
		if(newSense != null){
			double angle = Math.PI; 
			double tempangle = Math.PI;
			boolean there = false;
			double length =0;
			for(int i = 0; i < newSense.length;i++){			
				length = distance(newSense[i],center);
				if(length<range){
					tempangle = (angleRange/res)*Math.abs(index-i);
					there = true;
					if(tempangle < angle) angle = tempangle;
				}
			}
			double gap = Math.sin(angle)*length;
			if(there == false){
				return true;
			}
			return (gap>width/4);
		}
		return true;
	} 
	
	//updates the corners of the robot based on the center position and the angle
	public void updateCorners() {
		//direct is the straight line from center to corner
		double direct = Math.sqrt((height/2)*(height/2)+(width/2)*(width/2));
		//dtheta is the angle of this line
		double dtheta = theta+Math.PI/4;
		
		//calculates the coordinate of that corner and then increments the angle by 90
		for(int i = 0; i < 4;i++){		
			corners[i] = new Coordinate(center.x+Math.cos(dtheta)*direct,center.y+Math.sin(dtheta)*direct);
			dtheta +=Math.PI/2;			
		}
		
		updateEdges();
	}
	
	//updates the edges based on the corners
	public void updateEdges() {

		for(int i = 0; i < 3;i++){		
			edges[i] = new LineSeg(corners[i],corners[i+1]);
		}
		edges[3] = new LineSeg(corners[3],corners[0]);
		
	}	
	
	//updates sensor array
	public void readSensor(){
		mainSensor.sense(center,theta,newSense);
	}
	
	//rounds numbers
	public double round(double num){
		if(num < 0.000001 && num > -0.000001) num = 0;
		return num;
	}
	
	//checks if it is hitting any landmarks
	public boolean checkContact(){
		LineSeg temp;
		for(int i =0;i<4;i++){
			temp = edges[i];
			for(int j = 0; j < landmarks.size(); j++){
				Landmark mark = landmarks.get(j); //the pointer for the specific landmark
				
				//checks every lineseg
				for(int k = 0; k < mark.lineSegList.size();k++){
					LineSeg line = mark.lineSegList.get(k); //the pointer for specific lineseg	
					
					Coordinate intersect = temp.checkCross(line);
					if(intersect.exists) return true;
				}
			}
		}
		return false;
	}

 	//rotates robot incrementally towards given heading; returns true when there
	public boolean rotate(double heading){
		double tSpeed;
		boolean there;
		
		if(theta>Math.PI*2) theta = theta - (Math.PI*2);
		
		if(Math.abs(heading-theta) < 0.00001){
			return true;
		}
		
		if(heading<theta){
			tSpeed=turnspeed*-1;
		}
		else tSpeed=turnspeed;
		
		if(Math.abs(theta-heading)<=turnspeed){
			theta=heading;
			there =true;
		}
		else {
			theta+=tSpeed;
			if(theta>Math.PI*2) theta = theta - (Math.PI*2);
			there = false;
		}
		updateCorners();
		return there;
	}
	
	//increments robot motion towards the destination; returns true when there
	public boolean move(Coordinate dest){
		boolean there;
		double xSpeed = round(speed*Math.cos(theta));
		double ySpeed = round(speed*Math.sin(theta));		
		
		if(((Math.abs(dest.x-center.x)-Math.abs(xSpeed))<0.00001) && ((Math.abs(dest.y-center.y)-Math.abs(ySpeed))<0.00001)){
			center.x = dest.x;
			center.y = dest.y;
			there = true;
		}
		else{
			center.x += xSpeed;
			center.y += ySpeed;
			there = false;
		}
		updateCorners();
		return there;
	}

	//move backwards, opposite angle of theta
	public boolean moveBack(Coordinate dest){
		boolean there;
		double xSpeed = round(speed*Math.cos(theta))*-1;
		double ySpeed = round(speed*Math.sin(theta))*-1;		
		
		if(((Math.abs(dest.x-center.x)-Math.abs(xSpeed))<0.00001) && ((Math.abs(dest.y-center.y)-Math.abs(ySpeed))<0.00001)){
			center.x = dest.x;
			center.y = dest.y;
			there = true;
		}
		else{
			center.x += xSpeed;
			center.y += ySpeed;
			there = false;
		}
		updateCorners();
		return there;
	}
	
 	//takes the robot to a position
	public boolean goPos(Coordinate point){ //coordinate is relative to robot
		if(distance(center,point)<0.00001) return true;
		double heading = Math.atan2((point.y-center.y),(point.x-center.x));
		boolean there = false;
		while(!there){
			there = rotate(heading);
			save();
			if(checkContact()) return false;
		}
		there = false;
		if(distance(center,point)<0.00001) return true;
		while(!there){
			there = move(point);
			save();			
			if(checkContact()) return false;
		}
		return true;
	} 
	
	//this reverses failed contact
	public boolean reverse(Coordinate point){
		if(distance(center,point)<0.00001) return true;
		theta = Math.atan2((center.y-point.y),(center.x-point.x));
		boolean there = false;
		while(!there){
			there = moveBack(point);
			save();
			if(checkContact()) return false;
		}
		updateCorners();
		return true;		
	}

	//turns robot to face destination
	public boolean turnDes(){
		double heading = Math.atan2((goalPos.y-center.y),(goalPos.x-center.x));
		boolean there = false;
		if(distance(center,goalPos)<0.00001) return true;
		while(!there){
			there = rotate(heading);
			save();
			if(checkContact()) return false;
		}
		return true;
	} 
	
	//finds sensor reading to go to
 	public Coordinate nextPos(){
		readSensor();
		Coordinate next = newSense[(int)(newSense.length/2)];
		Coordinate current = newSense[0];
		int index = (int)(newSense.length/2);
		for(int i = 0; i < (int)(newSense.length/2); i++){
			for(int k = 0; k<=1;k++){
				if(k == 0)index = newSense.length - i-1;
				if(k == 1)index = i;
				current = newSense[index];
				double cDis = distance(center,current);
				double nDis = distance(center,next);
				if(cDis > nDis || Math.abs(cDis-nDis)<0.00001){
					next = current;
				} 
			
			}
		}
		return next;
	} 
	
	//this is the recursive method that navigates towards the destination
	public boolean iterate(Coordinate locate){
		its++;
		System.out.println(its);

		//if its at the destination it will bubble back up
		if(distance(center,goalPos)<0.00001) return true;

		//tries to go toward position given, and returns false it if fails
		if(!goPos(locate)){
			System.out.println("here5");
			return false;
		} 
		
		//tries to turn, returns false if fails
		if(!turnDes()){
			System.out.println("here6");			
			return false;
		}	
 
		//fill newSense array
		readSensor();

		//if it is closer to destination than sensor range, 
		if(range > distance(goalPos,center)){
			System.out.println("here2");
			return goPos(new Coordinate(goalPos.x,goalPos.y));
		} 
		
		//error 3,5,1
		//the following code runs a loop that tries different points, this is essential to recursion
		Coordinate current = newSense[(int)(newSense.length/2)];
		int index = (int)(newSense.length/2);
		for(int i = 0; i < (int)(newSense.length/2)+1; i++){
			for(int k = 0; k<=1;k++){
				if(k == 0)index = (int)(newSense.length/2) - i;
				if(k == 1)index = (int)(newSense.length/2) + i;
				if(i == 0 && k == 1)break;
				current = newSense[index];
				double cDis = distance(locate,current);
				if(Math.abs(cDis-range)<0.00001 && checkGap(current,index)){
					System.out.println("here3");
					if(iterate(current)){
						System.out.println("here4");
						return true;
					}
					else{
						reverse(locate);
						
					}
				} 				
			}
		}		
		System.out.println("here1");
		return false;
	}
	
	public boolean iterate2(Coordinate locate){
		its++;
 
		//fill newSense array
		readSensor();

		//if it is closer to destination than sensor range, 
		if(range > distance(goalPos,center)){
			System.out.println("here2");
			return goPos(new Coordinate(goalPos.x,goalPos.y));
		} 
		
		//error 3,5,1
		//the following code runs a loop that tries different points, this is essential to recursion
		Coordinate current = newSense[(int)(newSense.length/2)];
		int index = (int)(newSense.length/2);
		for(int i = 0; i < (int)(newSense.length/2)+1; i++){
			for(int k = 0; k<=1;k++){
				if(k == 0)index = (int)(newSense.length/2) - i;
				if(k == 1)index = (int)(newSense.length/2) + i;
				current = newSense[index];
				double cDis = distance(locate,current);
//				System.out.println("current: "+current.x+"\t"+current.y+"\t"+cDis);
				if(Math.abs(cDis-range)<0.00001){
					System.out.println("here3");
					if(checkThere(current) && iterate(current)){
						System.out.println("here4");
						return true;
					}
				} 				
			}
		}		
		System.out.println("here1");
		return false;
	}	
	
	public boolean checkThere(Coordinate location){

		//if its at the destination it will bubble back up
		if(distance(center,goalPos)<0.00001) return true;

		//tries to go toward position given, and returns false it if fails
		if(!goPos(location)){
			System.out.println("here5");
			reverse(location);
			turnDes();
			return false;
		} 
		
		//tries to turn, returns false if fails
		if(!turnDes()){
			System.out.println("here6");
			reverse(location);
			turnDes();			
			return false;
		}
		return true;
	}
	
	//calculates the distance between coordinates
 	public double distance(Coordinate c1, Coordinate c2){
		LineSeg length = new LineSeg(c1,c2);
		return length.getMagnitude();		
	}
	
	//calls the recursive method to navigate
	public void navigate(){
		iterate(center);

	}
	
	//saves a position so that it can be illustrated later
	public void save(){
		Coordinate temp = new Coordinate(center.x, center.y);
		steps.add(temp);
		angles.add(theta);
	}
	
}