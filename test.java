//This program is for test purposes
import java.lang.*;
import java.io.*;
import java.util.*;
import Structure.*;
import RobotDir.Sensor;
import RobotDir.Robot;
//import java.lang.Math;

class test {
	
	public static void main(String args[]){
		ArrayList<Landmark> landmarks = new ArrayList<Landmark>();
		ArrayList<LineSeg> linesegs = new ArrayList<LineSeg>();
		linesegs.add(new LineSeg(new Coordinate(-10,80), new Coordinate(30,80)));
		linesegs.add(new LineSeg(new Coordinate(30,80), new Coordinate(0,40)));
		linesegs.add(new LineSeg(new Coordinate(0,40), new Coordinate(-20,25)));
		linesegs.add(new LineSeg(new Coordinate(-20,25), new Coordinate(-10,80)));
		landmarks.add(new Landmark(linesegs));
		Coordinate pos = new Coordinate(0,20);
		//							x, y, t,  h,  w,  s,  d, angleRange, f         , pos, re,
		Robot testRobot = new Robot(0, 0, 0, 10, 10, 15, 80, Math.PI/2f, Math.PI/2f, pos, 50, landmarks);
		
		testRobot.readSensor();
		//Scanner input = new Scanner(System.in); //Init scanner
/* 		System.out.println(testRobot.corners[0].x+" "+testRobot.corners[0].y+" "+testRobot.corners[1].x+" "+testRobot.corners[1].y+" "+testRobot.corners[2].x+" "+testRobot.corners[2].y+" "+testRobot.corners[3].x+" "+testRobot.corners[3].y);
		System.out.println(testRobot.edges[0].direction.x+" "+testRobot.edges[0].direction.y+" "+testRobot.edges[1].direction.x+" "+testRobot.edges[1].direction.y+" "+testRobot.edges[2].direction.x+" "+testRobot.edges[2].direction.y+" "+testRobot.edges[3].direction.x+" "+testRobot.edges[3].direction.y);
 		Coordinate point1 = new Coordinate(1,2);
		Coordinate point2 = new Coordinate(3,6);
		LineSeg line = new LineSeg(point1,point2);
		
		Coordinate point3 = new Coordinate(4,8);
		Coordinate point4 = new Coordinate(6,12);
		LineSeg line1 = new LineSeg(point3,point4);*/
		
		for(int i = 0; i < testRobot.mainSensor.res;i++){
			System.out.println(testRobot.newSense[i].x+"\t"+testRobot.newSense[i].y+"\t"+testRobot.newSense[i].exists);
		}
/* 		System.out.println();
 		for(int i = 0; i < landmarks.get(0).segments.size();i++){
			System.out.println(landmarks.get(0).segments.get(0).end[0].x+"\t"+landmarks.get(0).segments.end[0].y);
		}  */
		
	}
}