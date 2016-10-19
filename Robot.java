//This class makes an object for the robot
public class Robot {

	//Robot characteristics
	public double height

	//Robot position variables
	public double x;
	public double y;
	public double theta;
	public Vertex[] corners = new Vertex[4];

	Robot(double x, double y, double theta)
	{
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
}