package game;

import java.awt.Graphics;
import java.awt.Image;

abstract public class Tower 
{
	protected Coordinate position;	
	protected Image tower; 			
	protected int width;
	protected int height;
	protected double timeSinceLastFire;
	
	public void draw(Graphics g)
	{
		g.drawImage(tower, position.getX() , position.getY() , null);
	}
	
	public void setPosition(Coordinate c)
	{
		position = c;
	}
	
	abstract void interact(Game game, double deltaTime);
}





