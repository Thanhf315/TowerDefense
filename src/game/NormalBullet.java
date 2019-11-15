package game;

public class NormalBullet extends Bullet {
	public NormalBullet(Coordinate pos, Coordinate target) {
		ImageLoader loader = ImageLoader.getLoader();
		this.picture = loader.getImage("resources/NormalBullet.png");
		this.posX = pos.x;
		this.posY = pos.y;		
		this.velocityX = target.x - this.posX;
		this.velocityY = target.y - this.posY;
		this.ageInSeconds = 0;
		this.strength = 30;
	}	
}
