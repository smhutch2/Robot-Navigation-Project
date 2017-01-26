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
import javafx.animation.AnimationTimer;
import javafx.animation.*;

public class testFX extends Application{

	public double width = 1000.0;
	public double height = 1000.0;
	public int i = 0;
	public ArrayList<Coordinate> coordList = new ArrayList();
	public ArrayList<Double> angleList = new ArrayList();

	//image representing the robot
	public Image roboImage = new Image("RoboImage.jpeg");
	public ImageView robotImage = new ImageView();

	public void start(Stage stage){

		stage.setTitle("JavaFX Window");

 

   		Group rootNode = new Group();
   		Scene myScene = new Scene(rootNode, width, height);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width, height);

		//new environment created
		Environment testEnvi = new Environment(width, height);
		testEnvi.robot.navigate();
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

		

		//image representing the robot
		robotImage.setImage(roboImage);
		robotImage.setFitHeight(testEnvi.robot.height);
		robotImage.setFitWidth(testEnvi.robot.width);

        //set X and Y to robot center val
        robotImage.setX(500);
        robotImage.setY(600);
        robotImage.setLayoutX(-testEnvi.robot.width/2);
        robotImage.setLayoutY(-testEnvi.robot.height/2);

		rootNode.getChildren().addAll(robotImage, myCanvas, goal);
		

		Line[] line = new Line[testEnvi.landmarks.size()];

		for(int j = 0 ; j < testEnvi.landmarks.size() ; j++){

			for(int i = 0 ; i < testEnvi.landmarks.get(j).vertices; i++){

				line[i] = new Line(testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[0].y, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].x, testEnvi.landmarks.get(j).lineSegList.get(i).ends[1].y); 
				rootNode.getChildren().add(line[i]);

			}

		}

		coordList = testEnvi.robot.steps;
		angleList = testEnvi.robot.angles;

		//buttons for iteration navigation
		Button nextStep = new Button("Next");
		Button prevStep = new Button("Back");

		nextStep.relocate(0, 0);
		prevStep.relocate(0, 50);

		rootNode.getChildren().addAll(nextStep, prevStep);

		//button handlers
		nextStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            	final int a = i;
            	printNextStep(a, rootNode);
            	System.out.println("Step: " + i);
            	i++;

            }
        });

        prevStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            	final int a = i;
            	printNextStep(a, rootNode);
            	System.out.println("Step: " + i);
            	i--;

            }
        });


        new AnimationTimer(){

        	long last = 0;
        	int count = 0;
        	//System.out.println("inside timer");

        	public void handle(long currentNanoTime){
        		
        		if((currentNanoTime-last) >= 10000){
        			System.out.println("inside handle");
        			count++;
        			printNextStep(count,rootNode);
        			last = currentNanoTime;
        		}
        		
        	}	
        }.start();


		stage.show();

	}

	//printing the solution
	public void printNextStep(int step, Group rootNode){

			double x = coordList.get(step).x;
			double y = coordList.get(step).y;
       		robotImage.setX(x);
        	robotImage.setY(y);
        	robotImage.setRotate((180/Math.PI)*angleList.get(step)+90);
        	Circle prevPos = new Circle(x, y, width/200);
        	prevPos.toBack();
        	rootNode.getChildren().add(prevPos);
        	robotImage.toFront();

    }
}