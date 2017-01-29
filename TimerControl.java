import java.lang.*;
import java.io.*;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.animation.*;

public class TimerControl extends AnimationTimer{
	
    long last = 0;
    int count = 0;

    testFX TEST;

   	@Override
    public void handle(long currentNanoTime){
        		
        if((currentNanoTime-last) >= 1000){
        	count++;
      		TEST.PrintNextStep(count);
       		last = currentNanoTime;
       	}   		
    }

}