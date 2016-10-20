//This class makes an object for the robot
import java.lang.Math.*;

public class Robot {

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
		double direct = Math.sqrt((height/2)*(height/2)+(width/2)*(width/2));
		double dtheta = theta+Math.PI/4;
		
		for(int i = 0; i < 4;i++)
		{		
			corners[i] = new Coordinate(center.x+Math.cos(dtheta)*direct,center.y+Math.sin(dtheta)*direct);
			dtheta +=Math.PI/2;			
		}
		
	}
	
}