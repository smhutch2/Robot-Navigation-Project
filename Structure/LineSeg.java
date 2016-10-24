//This class defines the position of lines
public class LineSeg {	

	//This is the direction vector for the line
	public Vector direction;
	
	public Coordinate ends[] = new Coordinate[2];
	
	LineSeg(Coordinate c1, Coordinate c2){
		ends[0] = new Coordinate(c1.x,c1.y);
		ends[1] = new Coordinate(c2.x,c2.y);
		setDirection();
	}
	
	//sets the direction vector from the two points
	public void setDirection(){
		//a vector is second point minus first point:
		direction = new Vector(ends[1].x-ends[0].x, ends[1].y-ends[0].y);
	}
	
	//sees if point is on line
	public boolean checkTouch(double x, double y){

		boolean work = false;
		
		//checks to make sure the point is inbetween the points
		if(inBetween(ends[0].x,ends[1].x,x)&&inBetween(ends[0].y,ends[1].y,y)){
	
			//sees if same scalar will work for both x and y
			double scalarx =(x-ends[0].x)/(direction.x);
			double scalary =(y-ends[0].y)/(direction.y);
			
			//if one part of the direction vector is zero, it needs to account for the NaN		
			if(Double.isNaN(scalarx)){
				if(!Double.isNaN(scalary)){
					work =true;
				}
			}
			if(Double.isNaN(scalary)){
				if(!Double.isNaN(scalarx)){
					work =true;
				}
			}
			if(scalarx==scalary)
			{
				work = true;
			}
		}
		return work;
	}
	
	//checks to see if a number is in between two others
	private boolean inBetween(double e1, double e2, double val){
		
		boolean work = false;
		
		if(e2<e1){
			work = (val<e1&&val>e2);
		}
		if(e2>e1){
			work = (val>e1&&val<e2);
		}
		if(e2==e1){
			work = (val==e1);
		}
		return work;
	}
	
	
}