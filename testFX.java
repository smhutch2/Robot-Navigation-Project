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
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.event.*;

public class testFX extends Application{

	public int i;
	public ArrayList<Coordinate> coordList;
	public ArrayList<Double> angleList;

	//image representing the robot
	public Image roboImage = new Image("RoboImage.jpeg");
	public ImageView robotImage = new ImageView();

	public void start(Stage stage){

		stage.setTitle("JavaFX Window");

    	double width = 1000.0;
		double height = 1000.0;

   		Group rootNode = new Group();
   		Scene myScene = new Scene(rootNode, width, height);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width, height);

		//new environment created
		Environment testEnvi = new Environment(width, height);
		Rectangle goal = new Rectangle(testEnvi.goalPos.x, testEnvi.goalPos.y, 50, 50);


		//all these lines are just the borders of the environment
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

		//button for next iteration
		Button nextStep = new Button("Next");

		nextStep.relocate(0, 0);

		//image representing the robot
		robotImage.setImage(roboImage);
		robotImage.setFitHeight(testEnvi.robot.height);
		robotImage.setFitWidth(testEnvi.robot.width);

        //set X and Y to robot center val
        robotImage.setX(500);
        robotImage.setY(500);

		rootNode.getChildren().addAll(robotImage, myCanvas, goal, nextStep);
		

		Line[] line = new Line[testEnvi.landmarks.size()];

		for(int j = 0 ; j < testEnvi.landmarks.size() ; j++){

			for(int i = 0 ; i < testEnvi.landmarks.get(j).vertices; i++){

				line[i] = new Line(testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].y, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].y); 
				rootNode.getChildren().add(line[i]);

			}

		}



		
		Robot.navigate(coordList, angleList);

		//button handler
		nextStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            	final int a = i;
            	printNextStep(a, rootNode);
            	System.out.println("Step: " + i);
            	i++;

            }
        });


		stage.show();

	}

	//printing the solution
	public void printNextStep(int step, Group rootNode){

       		robotImage.setX(coordList.get(step).x);
        	robotImage.setY(coordList.get(step).y);
        	robotImage.setRotate((180/Math.PI)*angleList.get(step));
        	rootNode.getChildren().add(robotImage);

    }
}