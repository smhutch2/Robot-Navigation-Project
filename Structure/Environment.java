package Structure;
import Robot.*;

import java.util.ArrayList;
import java.util.Random;

public class Environment{
	
	public ArrayList<Landmark> landmarks;
	public Robot robot;
	public int vertices;
	public Coordinate goalPos;
	public double width, height;

	public Environment(ArrayList<Landmark> landmarks, double width, double height, Robot robot){

	this.landmarks = landmarks;
	this.width = width;
	this.height = height;
	this.robot = robot;

	Coordinate q1 = new Coordinate(0, 0);
	Coordinate q2 = new Coordinate(width, 0);
	Coordinate q3 = new Coordinate(0, height);
	Coordinate q4 = new Coordinate(width, height);

	}

	public static Environment randomEnvironment(double width, double height){

		Coordinate q1 = new Coordinate(0, 0);
		Coordinate q2 = new Coordinate(width, 0);
		Coordinate q3 = new Coordinate(0, height);
		Coordinate q4 = new Coordinate(width, height);

		Coordinate robotPos = new Coordinate(width/2, height);
		Coordinate goalPos = new Coordinate(width/2, 0);
		double robotSize = width/20;

		Robot robot = new Robot(robotPos.x, robotPos.y, 0.0, robotSize, robotSize, robotSize/5);

		double landmarkW = robotSize;
		double landmarkH = robotSize;

		ArrayList<Landmark> mark = new ArrayList<>();

		Environment newEnvironment = new Environment(mark, width, height, robot);

		return newEnvironment;

	}

	public static Landmark randomLandmark(double landmarkW, double landmarkH, int vertices){

		Random x = new Random();
		Random y = new Random();
		ArrayList<Coordinate> coordinateList = new ArrayList<>();

		for(int i = 0 ; i < vertices ; i++){

			double xVal = x.nextInt((int)landmarkW);
			double yVal = y.nextInt((int)landmarkH);

			coordinateList.add(new Coordinate(xVal, yVal));

		}

		Landmark antiGreg = new Landmark(coordinateList, vertices);

		return antiGreg;

	}

}

