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
		linesegs.add(new LineSeg(new Coordinate(-30,50), new Coordinate(0,50)));
		linesegs.add(new LineSeg(new Coordinate(0,50), new Coordinate(0,60)));
		linesegs.add(new LineSeg(new Coordinate(0,60), new Coordinate(-30,60)));
		linesegs.add(new LineSeg(new Coordinate(-30,60), new Coordinate(-30,50)));
		
		ArrayList<LineSeg> linesegs2 = new ArrayList<LineSeg>();
		linesegs2.add(new LineSeg(new Coordinate(30,60), new Coordinate(50,60)));
		linesegs2.add(new LineSeg(new Coordinate(50,60), new Coordinate(50,50)));
		linesegs2.add(new LineSeg(new Coordinate(50,50), new Coordinate(30,50)));
		linesegs2.add(new LineSeg(new Coordinate(30,50), new Coordinate(30,60)));
		
		landmarks.add(new Landmark(linesegs));
		landmarks.add(new Landmark(linesegs2));
		
		//Coordinate pos = new Coordinate(0,20); 
		//							x, y, t,  h,  w,  s,  d, angleRange, f         , pos, re,
		Robot testRobot = new Robot(0, 0, Math.PI/2, 10, 10, 1, Math.PI/10,70,Math.PI/2,0,new Coordinate(0,0),17,landmarks, new Coordinate(0,100));
		
		testRobot.goPos(new Coordinate(10,10));
		
		System.out.println(testRobot.center.x+"\t"+testRobot.center.y+"\t"+testRobot.theta);
		
		testRobot.front = new Coordinate(30,30);
		testRobot.updateCenter();

		System.out.println(testRobot.center.x+"\t"+testRobot.center.y+"\t"+testRobot.theta);
		
	//	testRobot.reverse(new Coordinate(0,0));
	//	System.out.println(testRobot.distance(new Coordinate(0,0), new Coordinate(15,15)));
//		testRobot.turnDes();
//		System.out.println("Next place is: "+"x: "+testRobot.nextPos().x+" y: "+testRobot.nextPos().y);
	//	testRobot.iterate(new Coordinate(0,0));
		
/* 		testRobot.readSensor();
		for(int i = 0; i < testRobot.newSense.length;i++){
			System.out.println(testRobot.newSense[i].x+"\t"+testRobot.newSense[i].y+"\t"+testRobot.checkGap(testRobot.newSense[i],i));
		} */
		
	
	/* 		ArrayList<Coordinate> steps = new ArrayList();
		ArrayList<Double> angles = new ArrayList();
		testRobot.navigate();
		steps = testRobot.steps;
		angles = testRobot.angles;
		System.out.println(steps.size());
		for(int i = 0; i< steps.size();i++){
			Coordinate point = steps.get(i);
			double angle = angles.get(i);
			System.out.println(point.x+"\t"+point.y+"\t"+angle);
		} */
/* 		boolean there = false;
		
		while(there == false){
			there = testRobot.move(new Coordinate(0,10));
			System.out.println("pos: x: "+testRobot.center.x+" y: "+testRobot.center.y);
		} */
		
		//Scanner input = new Scanner(System.in); //Init scanner
/* 		System.out.println(testRobot.corners[0].x+" "+testRobot.corners[0].y+" "+testRobot.corners[1].x+" "+testRobot.corners[1].y+" "+testRobot.corners[2].x+" "+testRobot.corners[2].y+" "+testRobot.corners[3].x+" "+testRobot.corners[3].y);
		System.out.println(testRobot.edges[0].direction.x+" "+testRobot.edges[0].direction.y+" "+testRobot.edges[1].direction.x+" "+testRobot.edges[1].direction.y+" "+testRobot.edges[2].direction.x+" "+testRobot.edges[2].direction.y+" "+testRobot.edges[3].direction.x+" "+testRobot.edges[3].direction.y);
 		Coordinate point1 = new Coordinate(1,2);
		Coordinate point2 = new Coordinate(3,6);
		LineSeg line = new LineSeg(point1,point2);
		
		Coordinate point3 = new Coordinate(4,8);
		Coordinate point4 = new Coordinate(6,12);
		LineSeg line1 = new LineSeg(point3,point4);*/
		
/* 		for(int i = 0; i < testRobot.mainSensor.res;i++){
			System.out.println(testRobot.newSense[i].x+"\t"+testRobot.newSense[i].y+"\t"+testRobot.newSense[i].exists);
		} 
 /*		System.out.println();
		
		Coordinate tr = new Coordinate(1,1);
		Coordinate hi = new Coordinate(-1,-2);
		tr.transform(hi);
		System.out.println(tr.x+"\t"+tr.y);
		
 /* 		for(int i = 0; i < landmarks.get(0).segments.size();i++){
			System.out.println(landmarks.get(0).segments.get(0).end[0].x+"\t"+landmarks.get(0).segments.end[0].y);
		}   */
		
	}
}