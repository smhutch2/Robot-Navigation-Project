package Structure;

import RobotDir.*;

import java.util.ArrayList;
import java.util.Random;

public class Environment{
	
	public ArrayList<Landmark> landmarks;
	public Robot robot;
	public int vertices;
	public double width, height, posX, posY, maxSize;
	public LineSeg bottomBorder;
	public LineSeg topBorder;
	public LineSeg leftBorder;
	public LineSeg rightBorder;
	public Coordinate robotPos;
	public Coordinate goalPos;
	public double robotSize;
	public int arWidth = 10;
	public int arHeight = 8;

	public Environment(double width, double height){

		this.landmarks = new ArrayList<>();
		this.width = width;
		this.height = height;
		this.bottomBorder = new LineSeg(new Coordinate(0.0, 0.0), new Coordinate(width, 0.0));
		this.topBorder = new LineSeg(new Coordinate(0.0, height), new Coordinate(width, height));
		this.leftBorder = new LineSeg(new Coordinate(0.0, height), new Coordinate(0.0, 0.0));
		this.rightBorder = new LineSeg(new Coordinate(width, height), new Coordinate(width, 0.0));

		this.robotPos = new Coordinate(500, 600);
		this.goalPos = new Coordinate(width/2, 0);
		this.robotSize = width/20;

		
		//randomLandmarks();
		randomLandmarksRadial();
		robot = new Robot(robotPos.x, robotPos.y, Math.PI/2, robotSize, robotSize, robotSize/5, Math.PI/10, width/20, Math.PI/2, 0.0d, new Coordinate(0, 0), 11.0d, landmarks, goalPos);

	}

	//this environment creates randomly generated landmarks in a grid-like pattern
	public void randomLandmarks(){

		Random rand = new Random();
		Coordinate temp = new Coordinate(0, 0);
		int vertices;
		Landmark landmarkAr[][] = new Landmark[arHeight][arWidth];

		for(int i = 0 ; i < arHeight  ; i++){

			for(int j = 0 ; j < arWidth ; j++){
				

				if(j == 4){
				//	landmarkAr[i][j] = null;
				}
				else{
					vertices = rand.nextInt(22) + 3;
					temp.x = (double)j*100;
					temp.y = ((double)i+1)*100;
					landmarkAr[i][j] = randomLandmark(robotSize, vertices, temp);
					landmarks.add(landmarkAr[i][j]);
				}

			}

		}
	}

	//this is another random enironment creator using radial patterns. This allows for the environment to have a solution but still be generated randomly
	public void randomLandmarksRadial(){

		Random angle = new Random();
		Random vertices = new Random();

		double theta;
		double radius;
		int vertice;
		double x, y;
		double spacing = 4 * robotSize;
		double spacingAngle;
		int j, i;
		int layers = 5;

		System.out.println("In Rand Rad!");

		for(j = 1 ; j < layers ; j++){

			radius = (j * 200) + 100;
			spacingAngle = spacing / radius;
			//theta = angle.nextDouble() * Math.PI;
			theta = spacingAngle;

			System.out.println(radius+" Layer Loop " + Math.floor((Math.PI - theta) * radius / spacing));
			
			int landmarkAmount = (int)Math.floor((Math.PI - theta) * radius / spacing);

			for(i = 0 ; i < landmarkAmount ; i++){

				System.out.println("LM Loop");

				vertice = vertices.nextInt(17) + 3;
				y = Math.sin(theta) * radius;
				x = Math.cos(theta) * radius + width/2;
				Coordinate xy = new Coordinate(x, y);
				landmarks.add(randomLandmark(robotSize, vertice, xy)); // I changed this here but it should be a variable
				theta += spacingAngle;

				System.out.println(x+ "  " +y);

			}

		}
	}


	//creates a random landmark with a set position, size and amount of vertices
	public static Landmark randomLandmark(double maxSize, int vertices, Coordinate position){

		double angle = 0.0;
		double x;
		double y;
		double distance;
		int i;
		Random size = new Random();
		Coordinate temp0 = new Coordinate(0, 0);
		Coordinate temp1 = new Coordinate(0, 0);
		Coordinate temp2 = new Coordinate(0, 0);
		ArrayList<LineSeg> lineSegList = new ArrayList<>();
		Landmark landmark;
		
		for(i = 0 ; i < vertices + 1 ; i++){
			
			distance = maxSize/4 + size.nextInt(3*(int)maxSize/4);
			x = position.x + distance*Math.cos(angle);
			y = position.y + distance*Math.sin(angle);
			angle += 2*Math.PI/vertices;

			if(i == 0){
				temp0 = new Coordinate(x, y);
				temp1 = temp0;
			}

			else if(i == vertices){
				lineSegList.add(new LineSeg(temp2, temp0));
			}

			else{
				temp2 = new Coordinate(x, y);
				lineSegList.add(new LineSeg(temp1, temp2));
				temp1 = temp2;
			}

		}

		//System.out.println(lineSegList.size());
		landmark = new Landmark(lineSegList, vertices);
		return landmark;

	}

}