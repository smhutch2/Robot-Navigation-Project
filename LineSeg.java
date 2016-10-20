//This class defines the position of lines
public class LineSeg {
	public double m;
	public double b;
	//lines is defined as y=mx+b
	
	public Coordinate ends[] = new Coordinate[2];
	
	Line(double m, double b, Coordinate c1, Coordinate c2){
		this.m = m;
		this.b = b;
		ends[0] = new Coordinate(c1.x,c1.y);
		ends[1] = new Coordinate(c2.x,c2.y);
	}
	
	public boolean checkTouch()
	
}