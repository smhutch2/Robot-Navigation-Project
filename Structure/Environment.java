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

	//THESE VARIABLES NEED TO CHANGE WITH WIDTH AND LENGTH BUDDY!
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

		robotPos = new Coordinate(width/2, height);
		goalPos = new Coordinate(width/2, 0);
		robotSize = width/20;

		robot = new Robot(robotPos.x, robotPos.y, 0.0, robotSize, robotSize, robotSize/5);
		randomLandmarks();

	}

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

/*
	public void randomLandmarksRadial(){



	}
	*/

	/*
	public static Landmark randomLandmark(double maxSize, int vertices, double posX, double posY){

		Random x = new Random();
		Random y = new Random();
		Random size = new Random();
		boolean check = false;
		Coordinate temp = new Coordinate(0.0, 0.0);
		LineSeg tempL;

		double sizeVal = size.nextInt((int)maxSize / 2);
		sizeVal += maxSize / 2.0;

		ArrayList<LineSeg> lineSegList = new ArrayList<>();

		for(int i = 0 ; i < vertices ; i++){

			System.out.println("WHAT ARE THOSE!?");

			if(i == 0){

				double xVal = x.nextInt((int)sizeVal);
				double yVal = y.nextInt((int)sizeVal);

				yVal += posY;
				xVal += posX;
				temp = new Coordinate(xVal, yVal);

			}
			else{
				System.out.println("CHEESE 1");
				check = false;
				while(check == false){

					System.out.println("CHEESE 2");
					double xVal = x.nextInt((int)sizeVal);
					double yVal = y.nextInt((int)sizeVal);

					xVal += posX;
					yVal += posY;

					tempL = new LineSeg(temp, new Coordinate(xVal, yVal));

					if(i == 1){
						System.out.println("here!");
						lineSegList.add(tempL);
						temp.x = xVal;
						temp.y = yVal;
						check = true;
					}

					//PROBLEMS
					if else(i == vertices){

						double xVal = x.nextInt((int)sizeVal);
						double yVal = y.nextInt((int)sizeVal);

						xVal += posX;
						yVal += posY;

						tempL = new LineSeg(new Coordinate(xVal, yVal), )

						for(int j = 1 ; j < i ; j++){
							if(tempL.checkCross(lineSegList.get(j-1))) work = false;
						}
						if(work){
							lineSegList.add(tempL);
							check = true;
						}


					}
					else{
						boolean work = true;
						for(int j = 1 ; j < i ; j++){
							System.out.println("here!2");
							if(tempL.checkCross(lineSegList.get(j-1))) work = false;
						}
						if(work){
							System.out.println("here!3!");
							lineSegList.add(tempL);
							temp.x = xVal;
							temp.y = yVal;
							check = true;
						}
					}
				}
			}
		}

		Landmark landmarks = new Landmark(lineSegList, vertices);

		return landmarks;

	}
	*/

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

		System.out.println(lineSegList.size());
		landmark = new Landmark(lineSegList, vertices);
		return landmark;

	}

}