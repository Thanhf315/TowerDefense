package game;

import java.awt.Graphics;
import java.awt.Image;

abstract public class Enemy 
{
	protected PathPosition position;	
	protected Image enemy;
	protected int width, height, speed, armor, health, reward;

	public void advance()
	{
		position.advance(10 + speed);	
	}
	
	public void draw(Graphics g)
	{
		Coordinate c = position.getCoordinate();
		g.drawImage(enemy, c.x - width /2, c.y - height/2, null);
	}
	
	public PathPosition getPosition()
	{
		return position;
	}
	
}
