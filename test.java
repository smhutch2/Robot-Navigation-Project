//This program is for test purposes
import java.lang.*;
import java.io.*;
import java.util.*;
//import java.lang.Math;

class test {
	
	public static void main(String args[]){

		Robot testRobot = new Robot(5, 10, 3, 100, 100, 15);
		//Scanner input = new Scanner(System.in); //Init scanner
		testRobot.updateCorners();
		System.out.println(testRobot.corners[0].x+" "+testRobot.corners[0].y+" "+testRobot.corners[1].x+" "+testRobot.corners[1].y);
		System.out.println(testRobot.corners[2].x+" "+testRobot.corners[2].y+" "+testRobot.corners[3].x+" "+testRobot.corners[3].y);
		
	}
}