package Structure;
import Robot.*;


import java.util.ArrayList;

public class Landmark{

	public ArrayList<Coordinate> coordinateList;
	public ArrayList<LineSeg> segmentList;
	public double vertices;
	
	//Array List of coordinates (which are of type Coordinate),
	//these will be used to create polygons as Landmarks.

	public Landmark(ArrayList<Coordinate> coordinateList, double vertices){

		this.coordinateList = coordinateList;
		this.vertices = vertices;
		this.segmentList = segmentList;

		segmentList = new ArrayList<LineSeg>();
		
		for(int i = 0 ; i < vertices - 1; i++){
			System.out.println("i is:" + i);
			segmentList.add(i, new LineSeg(coordinateList.get(i), coordinateList.get(i+1)));
		}
		segmentList.add((int)vertices - 1, new LineSeg(coordinateList.get((int)vertices - 1), coordinateList.get(0)));

	}

}