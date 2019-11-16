package game;

import java.awt.*;

abstract public class Enemy implements GameEntity {
	protected PathPosition position;	
	protected Image enemy;
	protected int width, height, speed, armor, health, reward;

	public void advance() {
		position.advance(10 + speed);
	}
	
	public void draw(Graphics g) {
		Coordinate c = position.getCoordinate();
		g.drawImage(enemy, c.x - width/2 , c.y - height/2, null);
		g.setColor(Color.RED);
		if(health<=100) {
			g.fillRect(c.x - width / 2, c.y - height / 2 - 10, health, 10);
		}
		else if (health>100 && health <=300) {
			g.fillRect(c.x - width / 2 - 75, c.y - height / 2 - 10, health, 10);
		}
		else g.fillRect(c.x - width /2  - 150, c.y - height / 2 - 10, health, 10);
	}

	public PathPosition getPosition()
	{
		return position;
	}
	
}
