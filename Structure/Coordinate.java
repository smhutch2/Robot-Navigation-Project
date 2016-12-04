package Structure;

public class Coordinate{

	public double x;
	public double y;
	public boolean exists;

	//This is a constructor that creates a coordinate, with an x and y.

	public Coordinate(double x, double y){

		this.x = x;
		this.y = y;
		exists =true;
	}
	
	public Coordinate(double x, double y, boolean exists){
		this.x = x;
		this.y = y;
		this.exists = exists;
	}
	
	public void transform(Coordinate move){
		x+=move.x;
		y+=move.y;
	}
}