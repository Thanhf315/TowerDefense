package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

abstract public class Bullet implements GameEntity {
	protected int posX, posY, strength;
	protected double velocityX, velocityY;
	protected double TTL;
	protected Image picture;

	public abstract void updatepos(double deltaTime);
	public abstract void updateTTL(double deltaTime);

	public void interact(GameField game, double deltaTime, Bullet b) {

		List<Enemy> enemies = game.enemies;
		for(Enemy e: new LinkedList<Enemy>(enemies)) {
			double dx, dy, dist;
			dx = e.position.getCoordinate().x - posX; 
			dy = e.position.getCoordinate().y - posY;
			dist = Math.sqrt((dx*dx) + (dy*dy));
			if(e.health<=0) {
				game.enemies.remove(e);
				game.scoreCounter += e.reward;
				game.killsCounter += 1;
			}
			else if (dist<=50) {
				game.updatehealth(e,b);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(picture, posX, posY, null);
	}
	
	public boolean isDone() {
		return TTL >= 1;
	}
}
