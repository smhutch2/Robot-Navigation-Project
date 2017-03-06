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
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;


public class MonteCarloFX extends Application{

	public double width = 400.0;
	public double height = 600.0;
	public double eWidth = 1000;
	public double eHeight = 1000;
	public Group rootNode = new Group();
	public int trialsVal = 100;
	public double rangeVal = eWidth/20;
	public double resVal = 11.0;
	public double angleRangeVal = Math.PI/2;
	public double speedVal = eWidth/2000;

	public int success = 0;
	public ArrayList<Integer> stepList = new ArrayList();

	public void start(Stage stage){

		stage.setTitle("JavaFX Window");
   		Scene myScene = new Scene(rootNode, width, height);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width, height);

		Label titleText = new Label("Monte Carlo Simulation");

		TextField range = new TextField("Sensor Range");
		range.relocate(100, 100);
		TextField res = new TextField("Sensor Resolution");
		res.relocate(100, 200);
		TextField angleRange = new TextField("Sensor Angle Range");
		angleRange.relocate(100, 300);
		TextField speed = new TextField("Robot Movement Speed");
		speed.relocate(100, 400);
		TextField trials = new TextField("Trials");
		trials.relocate(100, 500);

		Button run = new Button("Run Simulation");
		run.relocate(100, 600);


		

		rootNode.getChildren().addAll(myCanvas, run, speed, trials, titleText, range, res, angleRange);
		stage.show();

		range.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

           		rangeVal = Double.parseDouble(range.getText());

            }
        });

        res.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

           		resVal = Double.parseDouble(res.getText());

            }
        });

        angleRange.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            	angleRangeVal = Double.parseDouble(angleRange.getText());

            }
        });

        speed.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            	speedVal = Double.parseDouble(speed.getText());

            }
        });

        trials.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            	trialsVal = Integer.parseInt(trials.getText());

            }
        });

        run.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            	//WIP//
            	int i = 0;
            	int k = 0;

          		while(i < trialsVal){

          			Environment testEnvi = new Environment(eWidth, eHeight, rangeVal, resVal, angleRangeVal, speedVal);
          			iterate(testEnvi);
          			i++;

            	}

            	System.out.println("Amount of successes: " + success);
            	i = 0;

            	while(i <= stepList.size()){

            		k += stepList.get(i);

            	}

            	double avg = k / stepList.size();

            	System.out.println("Average Steps: " + avg);
            	success = 0;
            	stepList.clear();

            }
        });

	}


	public void iterate(Environment testEnvi){

		if(testEnvi.robot.navigate()){

			success++;

		}

		stepList.add(testEnvi.robot.steps.size());

	}
}