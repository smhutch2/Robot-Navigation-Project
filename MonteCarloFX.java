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
import javafx.scene.text.Font;


public class MonteCarloFX extends Application{

	public double width = 500.0;
	public double height = 750.0;
	public double eWidth = 1000;
	public double eHeight = 1000;
	public Group rootNode = new Group();
	public int trialsVal = 100;
	public double rangeVal = eWidth/10;
	public double resVal = 11.0;
	public double angleRangeVal = Math.PI/2;
	public double speedVal = eWidth/2000;
	public double thresholdVal = 200;

	public int success = 0;
	public ArrayList<Integer> stepList = new ArrayList();
	public Label successes;
    public Label avgStep;

    public Font font1 = new Font(30.0);
    public Font font2 = new Font(20.0);


	public void start(Stage stage){

		stage.setTitle("JavaFX Window");
   		Scene myScene = new Scene(rootNode, width, height);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width, height);

		Label titleText = new Label("Monte Carlo Simulation");
		titleText.setFont(font1);

		TextField range = new TextField("50 - 150");
		Label rangeLabel = new Label("Sensor Range: 100");
		rangeLabel.relocate(100, 80);
		range.relocate(100, 100);

		TextField res = new TextField("9 - 21 (odd numbers)");
		Label resLabel = new Label("Sensor Resolution: 11");
		resLabel.relocate(100, 180);
		res.relocate(100, 200);

		TextField angleRange = new TextField("PI/4 - PI (radians)");
		Label angleRangeLabel = new Label("Sensor Angle Range: PI/2");
		angleRangeLabel.relocate(100, 280);
		angleRange.relocate(100, 300);

		TextField speed = new TextField("0.5");
		Label speedLabel = new Label("Robot Movement Speed: 0.5");
		speedLabel.relocate(100, 380);
		speed.relocate(100, 400);

		TextField threshold = new TextField("50 - 200");
		Label thresholdLabel = new Label("Failure Threshold: 200");
		thresholdLabel.relocate(100, 480);
		threshold.relocate(100, 500);

		TextField trials = new TextField("n trials");
		Label trialsLabel = new Label("Trials: 100");
		trialsLabel.relocate(100, 580);
		trials.relocate(100, 600);

		Button run = new Button("Run Simulation");
		run.relocate(100, 650);


		

		rootNode.getChildren().addAll(myCanvas, rangeLabel, resLabel, angleRangeLabel, speedLabel, trialsLabel, thresholdLabel, threshold, run, speed, trials, titleText, range, res, angleRange);
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

        threshold.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            	thresholdVal = Double.parseDouble(threshold.getText());
            	System.out.println(thresholdVal);

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

            	rootNode.getChildren().removeAll(successes, avgStep);

          		while(i < trialsVal){

          			Environment testEnvi = new Environment(eWidth, eHeight, rangeVal, resVal, angleRangeVal, speedVal, thresholdVal);
          			iterate(testEnvi);
          			i++;

            	}

            	System.out.println("Successes: " + success);

            	for(i = 0 ; i < stepList.size() ; i ++){

            		k += stepList.get(i);

            	}

            	double avg = k / stepList.size();

            	System.out.println("Average Steps: " + avg);

            	successes = new Label("Success Percentage: " + (double)success*100/trialsVal + "%");
            	avgStep = new Label("Average Steps: " + avg);

            	successes.relocate(350, 450);
            	avgStep.relocate(350, 500);

            	successes.setFont(font2);
    			avgStep.setFont(font2);

            	rootNode.getChildren().addAll(successes, avgStep);

            	success = 0;
            	stepList.clear();

            }
        });

	}


	public void iterate(Environment testEnvi){

		if(testEnvi.robot.navigate()){

			success++;
			System.out.println("SOLVED     Steps: " + testEnvi.robot.steps.size());

		}
		else{
			System.out.println("FAIL");
		}

		stepList.add(testEnvi.robot.steps.size());

	}
}