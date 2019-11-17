package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class PathPoints {
	private List <Coordinate> path;
	public PathPoints(Scanner s) {
		path = new ArrayList<Coordinate>();
		int counter = s.nextInt();

		for(int n = 0; n < counter; n++) {
			Coordinate c = new Coordinate(s.nextInt(), s.nextInt());	
			path.add(c);	
		}	
	}

    public PathPosition getStart()
    {	
        return new PathPosition(new ArrayList<Coordinate>(path));
    }
}
