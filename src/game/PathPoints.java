package game;

import java.util.*;

public class PathPoints {
	private List <Coordinate> line;
	public PathPoints(Scanner s) {
		line = new ArrayList<Coordinate>();
		int counter = s.nextInt();

		for(int n = 0; n < counter; n++) {
			Coordinate c = new Coordinate(s.nextInt(), s.nextInt());	
			line.add(c);
		}	
	}

    public PathPosition getStart()
    {	
        return new PathPosition(new ArrayList<Coordinate>(line));
    }
}
