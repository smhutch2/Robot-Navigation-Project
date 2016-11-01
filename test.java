//This program is for test purposes
import java.lang.*;
import java.io.*;
import java.util.*;
import Structure.*;
//import java.lang.Math;

class test {
	
	public static void main(String args[]){
		
//		Robot testRobot = new Robot(0, 0, 0, 10, 10, 15);
		//Scanner input = new Scanner(System.in); //Init scanner
/* 		System.out.println(testRobot.corners[0].x+" "+testRobot.corners[0].y+" "+testRobot.corners[1].x+" "+testRobot.corners[1].y+" "+testRobot.corners[2].x+" "+testRobot.corners[2].y+" "+testRobot.corners[3].x+" "+testRobot.corners[3].y);
		System.out.println(testRobot.edges[0].direction.x+" "+testRobot.edges[0].direction.y+" "+testRobot.edges[1].direction.x+" "+testRobot.edges[1].direction.y+" "+testRobot.edges[2].direction.x+" "+testRobot.edges[2].direction.y+" "+testRobot.edges[3].direction.x+" "+testRobot.edges[3].direction.y);
 */		Coordinate point1 = new Coordinate(1,2);
		Coordinate point2 = new Coordinate(3,6);
		LineSeg line = new LineSeg(point1,point2);
		
		Coordinate point3 = new Coordinate(2,4);
		Coordinate point4 = new Coordinate(4,8);
		LineSeg line1 = new LineSeg(point3,point4);
		
		System.out.println(line.checkCross(line1));
	}
}