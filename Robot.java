//This class makes an object for the robot
import java.lang.Math.*;

public class Robot{

	//Robot characteristics
	public double height;
	public double width;
	public double speed;

	//Robot position variables
	public Coordinate center;
	public double theta;
	public Coordinate corners[] = new Coordinate[4];
	public LineSeg edges[] = new LineSeg[4];

	Robot(double x, double y, double theta, double height, double width, double speed) {
		center = new Coordinate(x,y);
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.theta = theta;
		updateCorners();
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
	
}