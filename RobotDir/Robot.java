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
	public Sensor mainSensor;

	//Robot position variables
	public Coordinate center;
	public double theta;
	public Coordinate corners[] = new Coordinate[4];
	public LineSeg edges[] = new LineSeg[4];
	public Coordinate newSense[];

	public Robot(double x, double y, double theta, double height, double width, double speed) {
		center = new Coordinate(x,y);
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.theta = theta;
		updateCorners();
	}

	public Robot(double x, double y, double theta, double height, double width, double speed, double distance, double angleRange, double facing, Coordinate pos, double res, ArrayList<Landmark> landmarks) {
		center = new Coordinate(x,y);
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.theta = theta;
		updateCorners();
		newSense = new Coordinate[(int)res];
		mainSensor = new Sensor(distance, angleRange, facing, pos, res, landmarks);
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
	
	public void readSensor()
	{
		mainSensor.sense(center,newSense);
	}
}