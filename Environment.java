public class Environment{
	
	ArrayList<Landmark> landmarks;

	Environment(Arraylist<Landmark> landmarks, double width, double height){

	this.landmarks = landmarks;
	this.width = width;
	this.height = height;

	Coordinate q1 = new Coordinate(0, 0);
	Coordinate q2 = new Coordinate(width, 0);
	Coordinate q3 = new Coordinate(0, height);
	Coordinate q4; = new Coordinate(width, height);

	}