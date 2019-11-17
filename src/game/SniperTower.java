package game;

import java.util.List;

public class SniperTower extends Tower {

	public SniperTower(Coordinate pos) {
		ImageLoader loader = ImageLoader.getLoader();
		this.tower = loader.getImage("resources/SniperTower.png");
		this.position = pos;
		this.width = 50;
		this.height = 50;
	}
	
	public void interact(GameField game, double deltaTime) {
		timeSinceLastFire += deltaTime;
		
		if(timeSinceLastFire < 2)
			return;
		
		List<Enemy> enemies = game.enemies;
		
		for(Enemy e: enemies) {

			Coordinate enemyPos = e.getPosition().getCoordinate();
			double dx, dy, dist;
			dx = enemyPos.x - position.x; 
			dy = enemyPos.y - position.y;
			dist = Math.sqrt((dx*dx) + (dy*dy));
			
			Coordinate pos = new Coordinate(position.x, position.y);	
			
			if(dist <= 325)
			{	SniperBullet sniperbullet = new SniperBullet(pos, enemyPos);
				game.bullets.add(sniperbullet);
				timeSinceLastFire = 0;
				return;
			}	
		} 
	}	
}
