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
	}

	public void updateCorners() {
		double direct = Math.sqrt((height/2)*(height/2)+(width/2)*(width/2));
		double dtheta = theta+Math.PI/4;
		
		corners[0].x = center.x+Math.cos(dtheta)*direct;
		corners[0].y = center.y+Math.sin(dtheta)*direct;
	
		corners[1].x = center.x-Math.cos(dtheta)*direct;
		corners[1].y = center.y+Math.sin(dtheta)*direct;
		
		corners[2].x = center.x-Math.cos(dtheta)*direct;
		corners[2].y = center.y-Math.sin(dtheta)*direct;
	
		corners[3].x = center.x+Math.cos(dtheta)*direct;
		corners[3].y = center.y-Math.sin(dtheta)*direct;
	}
	
}