//This program is for test purposes
import java.lang.*;
import java.io.*;
import java.util.*;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Node.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import Structure.*;
import RobotDir.*;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import java.lang.Math;

public class testFX extends Application{

	public void start(Stage stage){

		stage.setTitle("JavaFX Window");

    	double width = 1000.0;
		double height = 1000.0;

   		Group rootNode = new Group();
   		Scene myScene = new Scene(rootNode, width+50, height+50);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width+50, height+50);

		Environment testEnvi = new Environment(width, height);

		Line b1 = new Line(testEnvi.bottomBorder.ends[0].x, testEnvi.bottomBorder.ends[0].y, testEnvi.bottomBorder.ends[1].x, testEnvi.bottomBorder.ends[1].y);
		Line b2 = new Line(testEnvi.topBorder.ends[0].x, testEnvi.topBorder.ends[0].y, testEnvi.topBorder.ends[1].x, testEnvi.topBorder.ends[1].y);
		Line b3 = new Line(testEnvi.leftBorder.ends[0].x, testEnvi.leftBorder.ends[0].y, testEnvi.leftBorder.ends[1].x, testEnvi.leftBorder.ends[1].y);
		Line b4 = new Line(testEnvi.rightBorder.ends[0].x, testEnvi.rightBorder.ends[0].y, testEnvi.rightBorder.ends[1].x, testEnvi.rightBorder.ends[1].y);

		b1.setStrokeWidth(10);
		b2.setStrokeWidth(10);
		b3.setStrokeWidth(10);
		b4.setStrokeWidth(10);

		rootNode.getChildren().addAll(b1, b2, b3, b4);

		Line l1 = new Line(testEnvi.robot.corners[0].x, testEnvi.robot.corners[0].y, testEnvi.robot.corners[1].x, testEnvi.robot.corners[1].y);
		Line l2 = new Line(testEnvi.robot.corners[1].x, testEnvi.robot.corners[1].y, testEnvi.robot.corners[2].x, testEnvi.robot.corners[2].y);
		Line l3 = new Line(testEnvi.robot.corners[2].x, testEnvi.robot.corners[2].y, testEnvi.robot.corners[3].x, testEnvi.robot.corners[3].y);
		Line l4 = new Line(testEnvi.robot.corners[3].x, testEnvi.robot.corners[3].y, testEnvi.robot.corners[0].x, testEnvi.robot.corners[0].y);

		l1.setStrokeWidth(5);
		l2.setStrokeWidth(5);
		l3.setStrokeWidth(5);
		l4.setStrokeWidth(5);

		Image roboImage = new Image("RoboImage.png");
		ImageView robotImage = new ImageView();
        robotImage.setImage(roboImage);
        //robotImage.setX(testEnvi.robot.corners[0].x);
        //robotImage.setY(testEnvi.robot.corners[0].y);

        robotImage.setX(100.0);
        robotImage.setY(100.0);

		rootNode.getChildren().addAll(robotImage, myCanvas);
		//rootNode.getChildren().addAll(myCanvas, l1, l2, l3, l4);

		/*
		for(int j = 0 ; j < 10 ; j++){

			Random rand = new Random();
			vertices = rand.nextInt(20);
			vertices += 300;

			Coordinate c = new Coordinate(rand.nextInt(1200), rand.nextInt(700));
			Landmark antiGreg = Environment.randomLandmark(300, vertices, c);
			Line[] line = new Line[vertices];


		//turn into method!
			for(int i = 0 ; i < vertices; i++){

				System.out.println(antiGreg.lineSegList.get(i).ends[0].x +"		"+ antiGreg.lineSegList.get(i).ends[0].y);
				line[i] = new Line(antiGreg.lineSegList.get(i).ends[0].x, antiGreg.lineSegList.get(i).ends[0].y, antiGreg.lineSegList.get(i).ends[1].x, antiGreg.lineSegList.get(i).ends[1].y); 
				rootNode.getChildren().add(line[i]);

			}
		}
		*/

		Line[] line = new Line[testEnvi.landmarks.size()];

		for(int j = 0 ; j < testEnvi.landmarks.size() ; j++){

			for(int i = 0 ; i < testEnvi.landmarks.get(j).vertices; i++){

				line[i] = new Line(testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].y, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].y); 
				rootNode.getChildren().add(line[i]);

			}

		}


		stage.show();

	}
}