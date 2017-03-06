import java.lang.*;
import java.io.*;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.animation.*;

public class TimerControl extends AnimationTimer{
	
    long last = 0;
    int count = 0;

    environmentFX TEST;

   	@Override
    public void handle(long currentNanoTime){
        		
        if((currentNanoTime-last) >= 1000){

          if(count == TEST.coordList.size() - 1){

            this.stop();

          }

          else{
            	count++;
      	   	  TEST.PrintNextStep(count);
              TEST.aniSlider.adjustValue((double)count/TEST.coordList.size());
       	  	  last = currentNanoTime;
          }

       	}   		
    }

}