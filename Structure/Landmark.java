package Structure;
import java.util.*;
public class Landmark{

	public ArrayList<LineSeg> segments;
	
	//Array List of vertices (which are of type coordinate),
	//these will be used to create polygons as Landmarks.

	public Landmark(ArrayList<LineSeg> segments){

		this.segments = segments;

	}
}