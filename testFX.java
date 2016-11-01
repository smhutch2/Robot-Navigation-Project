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
import Robot.*;
import java.util.ArrayList;
//import java.lang.Math;

public class testFX extends Application{

	public void start(Stage stage){

		stage.setTitle("JavaFX Window");

    	double width = 700;
    	double height = 700;

   		Group rootNode = new Group();
   		Scene myScene = new Scene(rootNode, width, height);
		stage.setScene(myScene);
		Canvas myCanvas = new Canvas(width, height);

		Robot greg = new Robot(350, 350, 3.0, 100, 100, 10);


		Line l1 = new Line(greg.corners[0].x, greg.corners[0].y, greg.corners[1].x, greg.corners[1].y);
		Line l2 = new Line(greg.corners[1].x, greg.corners[1].y, greg.corners[2].x, greg.corners[2].y);
		Line l3 = new Line(greg.corners[2].x, greg.corners[2].y, greg.corners[3].x, greg.corners[3].y);
		Line l4 = new Line(greg.corners[3].x, greg.corners[3].y, greg.corners[0].x, greg.corners[0].y);

		l1.setStrokeWidth(10);
		l2.setStrokeWidth(5);
		l3.setStrokeWidth(5);
		l4.setStrokeWidth(5);

		System.out.println("BORK");

		rootNode.getChildren().addAll(myCanvas, l1, l2, l3, l4);

		int vertices = 100;

		//requires vertice variable!
		Landmark antiGreg = Environment.randomLandmark(150, 150, vertices);

		Line[] line = new Line[vertices];

		System.out.println("BORK2");


		//turn into method!
		for(int i = 0 ; i < vertices ; i++){

			if(i == vertices - 1){
				line[i] = new Line(antiGreg.coordinateList.get(i).x, antiGreg.coordinateList.get(i).y, antiGreg.coordinateList.get(0).x, antiGreg.coordinateList.get(0).y);
				rootNode.getChildren().add(line[i]);
				System.out.println("MEOW");
			}
			else{
				line[i] = new Line(antiGreg.coordinateList.get(i).x, antiGreg.coordinateList.get(i).y, antiGreg.coordinateList.get(i+1).x, antiGreg.coordinateList.get(i+1).y); 
				rootNode.getChildren().add(line[i]);
				System.out.println(antiGreg.coordinateList.get(i).x +"    "+ antiGreg.coordinateList.get(i).y);
				System.out.println("MEOW");
			}

		}

		stage.show();

	}
}