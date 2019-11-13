package game;

import java.util.List;

public class NormalTower extends Tower
{

	public NormalTower(Coordinate pos)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.tower = loader.getImage("resources/NormalTower.png");
		this.position = pos;
		this.width = 50;
		this.height = 50;
	}
	
	public void interact(Game game, double deltaTime)
	{
		timeSinceLastFire += deltaTime;
		
		if(timeSinceLastFire < 1)
			return;
		
		List<Enemy> enemies = game.enemies;
		
		for(Enemy e: enemies)
		{	
			
			Coordinate enemyPos = e.getPosition().getCoordinate();
			double dx, dy, d;
			dx = enemyPos.x - position.x; 
			dy = enemyPos.y - position.y;
			d = Math.sqrt((dx*dx) + (dy*dy));
			
			Coordinate pos = new Coordinate(position.x, position.y);	
			
			if(d < 80)
			{	NormalBullet normalbullet = new NormalBullet(pos, enemyPos);
				game.bullets.add(normalbullet);
				timeSinceLastFire = 0;
				return;
			}	
		} 
	}	
}
