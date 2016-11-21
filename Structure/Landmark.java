package Structure;
import java.util.ArrayList;

public class Landmark{

	public ArrayList<LineSeg> lineSegList;
	public int vertices;
	
	//Array List of vertices (which are of type coordinate),
	//these will be used to create polygons as Landmarks.

	public Landmark(ArrayList<LineSeg> lineSegList, int vertices){

		this.lineSegList = lineSegList;
		this.vertices = vertices;

	}
}
