package game;

public class NormalBullet extends Bullet {
	public NormalBullet(Coordinate pos, Coordinate target) {
		ImageLoader loader = ImageLoader.getLoader();
		this.picture = loader.getImage("resources/NormalBullet.png");
		this.posX = pos.x;
		this.posY = pos.y;		
		this.velocityX = target.x - this.posX;
		this.velocityY = target.y - this.posY;
		this.TTL = 0;
		this.strength = 30;
	}

	public void updatepos(double deltaTime) {
		posX += velocityX*deltaTime;
		posY += velocityY*deltaTime;
	}
	public void updateTTL(double deltaTime){
		TTL+=deltaTime;
	}
}
