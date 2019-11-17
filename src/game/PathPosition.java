package game;

import java.util.List;

public class PathPosition {
	
	private int pos;
	private List <Coordinate> line;
	
	PathPosition(List<Coordinate> points) {
		this.pos = 0;
		this.line = points;
	}
	
    public boolean isAtTheEnd ()
    {   
    	return pos == line.size()-1;
    }
    
    public Coordinate getCoordinate () {
    	if(isAtTheEnd())  return line.get(line.size()-1);

    	int startX = line.get(pos).x;
    	int startY = line.get(pos).y;
    	
        return new Coordinate(startX, startY);
    }
    
    public void updatepos() {
		if (isAtTheEnd())
			return;

    	int startX = line.get(pos).x;
    	int endX = line.get(pos + 1).x;
    	
    	int startY = line.get(pos).y;
    	int endY = line.get(pos + 1).y;

		pos++;
    }
}