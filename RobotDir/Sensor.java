//this class is the sensor on the robot
package RobotDir;
import Structure.*;
import java.lang.Math.*;
import java.util.*;


public class Sensor{
	public double distance; //distance of sight
	public double angleRange; //half of the sector angle
	public double facing; //angle that it is facing relative to robot
	public Coordinate pos; //position relative to the center of the robot
	public double res; //how many distance readings it returns minus 1
	public ArrayList<Landmark> landmarks; //needs to get the pointers for all of the landmarks in the environment
	
	public Sensor(double distance, double angleRange, double facing, Coordinate pos, double res, ArrayList<Landmark> landmarks){
		this.distance = distance;
		this.angleRange = angleRange;
		this.facing = facing;
		this.pos = pos;
		this.res = res;
		this.landmarks = landmarks;
	}
	
	//fills newSense with coordinates of the sensor reading
	public void sense(Coordinate center, Coordinate newSense[]){
		LineSeg values[] = new LineSeg[(int)res];
		double angleInc = (angleRange*2)/(res-1);
		double currentAngle = facing-angleRange;
		Coordinate offset = new Coordinate(0,0);
		offset.x = pos.x;
		offset.y = pos.y;
		offset.transform(center);
		
		//creates a lineseg for each increment and sees if there is contact
		for(int i =0;i<res;i++){
			double angle=facing-angleRange+angleInc*i; //the angle of the current lineseg
			Coordinate point = new Coordinate(Math.cos(angle)*distance+offset.x, Math.sin(angle)*distance+offset.y); //the point at the end of the lineSeg
			LineSeg lineSense = new LineSeg(offset, point); //uses the point and the origin point to make lineseg			
			
			//checks every landmark
			for(int j = 0; j < landmarks.size(); j++){
				Landmark mark = landmarks.get(j); //the pointer for the specific landmark
				
				//checks every lineseg
				for(int k = 0; k < mark.lineSegList.size();k++){
					LineSeg line = mark.lineSegList.get(k); //the pointer for specific lineseg
					
					//if there is an intersection, it adds it to newSense, if there's not it adds point
					Coordinate intersect = lineSense.checkCross(line);
					//if it intersects
					if(intersect.exists){
						//if there is a point already for that reading
						if(newSense[i]!=null){
							LineSeg comp1 = new LineSeg(offset,intersect);
							LineSeg comp2 = new LineSeg(offset,newSense[i]);
							//adds the new point only if its closer
							if(comp1.getMagnitude()<comp2.getMagnitude()) newSense[i] = intersect;
						}
						else newSense[i] = intersect;
					} 
				}
			}
			
			if(newSense[i]==null)	newSense[i] = point;
		}
	}	
}