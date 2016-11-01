package Structure;

public class LineSeg {	

	//This is the direction vector for the line
	public Vector direction;
	public Coordinate ends[] = new Coordinate[2];
	
	public LineSeg(Coordinate c1, Coordinate c2){
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
	
	//checks if another lineseg crosses this line seg
	public boolean checkCross(LineSeg intersect)
	{
		boolean hits = false;
		double x = 0;
		double y = 0;
		//checks for the cases that would cause the scalar calculate to return NaN
		if(direction.x==0 && intersect.direction.y==0){
			System.out.println("here");
			x=ends[0].x;
			y=intersect.ends[0].y;		
		}
		else if(direction.y==0 && intersect.direction.x==0){
			System.out.println("here2");			
			x=intersect.ends[0].x;
			y=ends[0].y;			
		}
		else if(direction.x==0&&intersect.direction.x==0){
			if(ends[0].x==intersect.ends[0].x && (inBetween(ends[0].y,ends[1].y,intersect.ends[0].y) || inBetween(ends[0].y,ends[1].y,intersect.ends[1].y))){
				return true;
			}
			else return false;
		}
		else if(direction.y==0 && intersect.direction.y==0){
			if(ends[0].y==intersect.ends[0].y && (inBetween(ends[0].x,ends[1].x,intersect.ends[0].x) || inBetween(ends[0].x,ends[1].x,intersect.ends[1].x))){
				return true;
			}
			else return false;
		} 
		//parallel case
/* 		else if((direction.x/intersect.direction.x)==(direction.y/intersect.direction.y)) {
			
		}return false; */
		//calculates the x and y for any other case
		else{
			double scalar1 = (intersect.direction.x*(ends[0].y-intersect.ends[0].y)-intersect.direction.y*(ends[0].x-intersect.ends[0].x))/(direction.x*intersect.direction.y-intersect.direction.x*direction.y);
			
			x = ends[0].x + scalar1*direction.x;
			y = ends[0].y + scalar1*direction.y;
		}
		
		System.out.println("end: x: "+ends[0].x+" y: "+ends[0].y);
		System.out.println("direction1: x: "+direction.x+" y: "+direction.y);
		System.out.println("direction2: x: "+intersect.direction.x+" y: "+intersect.direction.y);		
		System.out.println("x: "+x+" y: "+y);
		
		//checks if it is in the range and domain of the lines
		if(inBetween(ends[0].x, ends[1].x, x) && inBetween(ends[0].y, ends[1].y, y)) hits = true;			
		
		return hits;		
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