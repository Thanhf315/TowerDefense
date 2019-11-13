package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

abstract public class Bullet 
{
	protected int posX, posY;
	protected double velocityX, velocityY;
	protected double ageInSeconds;
	protected Image picture;
	
	public void interact(Game game, double deltaTime)
	{
		ageInSeconds += deltaTime;
		posX += velocityX*deltaTime;
		posY += velocityY*deltaTime;
		
		List<Enemy> enemies = game.enemies;
		for(Enemy e: new LinkedList<Enemy>(enemies))
		{
			double dx, dy, dist;
			
			dx = e.position.getCoordinate().x - posX; 
			dy = e.position.getCoordinate().y - posY;
			
			dist = Math.sqrt((dx*dx) + (dy*dy));
			
			if(dist < 40)	
			{	
				game.enemies.remove(e);
				game.scoreCounter += 3;
				game.killsCounter += 1;
			}
		}
	}

	public void draw(Graphics g)
	{
		g.drawImage(picture, posX, posY, null);
		
	}
	
	public boolean isDone()
	{
		return ageInSeconds >= 1;
	}
}