package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class PathPoints 
{
	private List <Coordinate> path;
	public PathPoints(Scanner s)
	{	
		path = new ArrayList<Coordinate>();		
		int counter = s.nextInt(); 			
		
		for(int n = 0; n < counter; n++)
		{	
			Coordinate c = new Coordinate(s.nextInt(), s.nextInt());	
			path.add(c);	
		}	
	}
	
	public void drawLine(Graphics g)
	{	
		g.setColor(Color.WHITE);
		
		for(int n = 0; n < path.size()-1; n++)	
		{	
			g.drawLine(path.get(n).x, path.get(n).y, path.get(n+1).x, path.get(n+1).y);
		}	
	}
	
    public PathPosition getStart()
    {	
        return new PathPosition(new ArrayList<Coordinate>(path));
    }
    
    public double distanceToPath (double px, double py)
    {
        double minDistance = Double.MAX_VALUE;
        
        for (int i = 0; i < path.size()-1; i ++)
        {        
            double sx = path.get(i).x;
            double sy = path.get(i).y;
            double ex = path.get(i+1).x;
            double ey = path.get(i+1).y;
                        
            double vx = ex - sx;
            double vy = ey - sy;
            double vl = Math.sqrt(vx * vx + vy * vy);
            vx /= vl;
            vy /= vl;
              
            double dx = px - sx;
            double dy = py - sy;
            double dl = Math.sqrt(dx * dx + dy * dy);

            double segDist = dx * vx + dy * vy;
            double distance;
            if (segDist < 0)
                distance = Math.sqrt((vx-sx)*(vx-sx) + (vy-sy)*(vy-sy));
            else if (segDist > vl)
                distance = Math.sqrt((vx-ex)*(vx-ex) + (vy-ey)*(vy-ey));
            else
                distance = Math.sqrt(dl*dl - segDist*segDist);     
            if (distance < minDistance)
                minDistance = distance;
        }
        
        return minDistance;
    }

}
