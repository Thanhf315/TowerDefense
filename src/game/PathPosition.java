package game;

import java.util.List;

public class PathPosition {
	
	private int segment;			
	private double percentage;		
	private List <Coordinate> path;	
	
	PathPosition(List<Coordinate> points) {
		this.segment = 0;		
		this.percentage = 0;	
		this.path = points;	
	}
	
    public boolean isAtTheEnd ()
    {   
    	return segment == path.size()-1;	
    }
    
    public Coordinate getCoordinate () {
    	if(isAtTheEnd())
    		return path.get(path.size()-1);
    	
    	int startX = path.get(segment).x;
    	int endX = path.get(segment + 1).x;
    	
    	int startY = path.get(segment).y;
    	int endY = path.get(segment + 1).y;

    	int dX = endX - startX;
    	int dY = endY - startY;
 	
    	int ballX = startX + (int) (dX*percentage);
    	int ballY = startY + (int) (dY*percentage);
    	
        return new Coordinate(ballX, ballY);    
    }
    
    public void advance (double distance) {
        if(isAtTheEnd())
        	return;		
    	int startX = path.get(segment).x;
    	int endX = path.get(segment + 1).x;
    	
    	int startY = path.get(segment).y;
    	int endY = path.get(segment + 1).y;
    	
    	int dX = endX - startX;
    	int dY = endY - startY;
    	
    	double length = Math.sqrt((double) (dX*dX) + (double) (dY*dY));
    	double unit = 1/length;	
    	percentage += unit*distance;	
    	if(percentage > 1) {
    		segment ++;										
    		distance = distance-(1-percentage)*length;	
    		percentage = 0;									
    		advance(distance);						
    	}
    }
}