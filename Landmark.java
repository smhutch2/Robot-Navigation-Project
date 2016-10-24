public class Landmark{

	ArrayList<LineSeg> segments;
	
	//Array List of vertices (which are of type coordinate),
	//these will be used to create polygons as Landmarks.

	public Landmark(ArrayList<LineSeg> segments){

		this.segments = segments;

	}
}