package Structure;
import Robot.*;

import java.util.ArrayList;
import java.util.Random;

public class Environment{
	
	ArrayList<Landmark> landmarks;
	Robot robot;
	int vertices;
	Coordinate goalPos;
	double width, height, posX, posY, maxSize;

	Environment(ArrayList<Landmark> landmarks, double width, double height, Robot robot){

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

		int arWidth = 10;
		int arHeight = 9;
		Random rand = new Random();
		Coordinate temp = new Coordinate(0, 0);
		int vertices;

		Coordinate robotPos = new Coordinate(width/2, height);
		Coordinate goalPos = new Coordinate(width/2, 0);
		double robotSize = width/20;

		Robot robot = new Robot(robotPos.x, robotPos.y, 0.0, robotSize, robotSize, robotSize/5);

		ArrayList<Landmark> landmarks = new ArrayList<>();

		Landmark landmarkAr[][] = new Landmark[arWidth][arHeight];

		for(int i = 0 ; i < arWidth ; i++){

			for(int j = 0 ; j < arHeight ; j++){
				
				vertices = rand.nextInt(22) + 3;
				temp.x = (double)j*100;
				temp.y = ((double)i+1)*100;
				landmarkAr[i][j] = randomLandmark(robotSize, vertices, temp);
				landmarks.add(landmarkAr[i][j]);
				if(i == 4){
					landmarkAr[i][j] = null;
				}

			}

		}

		Environment newEnvironment = new Environment(landmarks, width, height, robot);

		return newEnvironment;

	}

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
				//temp2 = new Coordinate(x, y);
				System.out.println("C1 = "+ temp2.x +", "+ temp2.y + "	C2 = "+ temp0.x +", "+ temp0.y);
				lineSegList.add(new LineSeg(temp2, temp0));
			}

			else{
				temp2 = new Coordinate(x, y);
				System.out.println("C1 = "+ temp1.x +", "+ temp1.y + "	C2 = "+ temp2.x +", "+ temp2.y);
				lineSegList.add(new LineSeg(temp1, temp2));
				temp1 = temp2;
			}

		}

		System.out.println("Landmark size = " + lineSegList.size());
		landmark = new Landmark(lineSegList, vertices);
		return landmark;

	}

}

