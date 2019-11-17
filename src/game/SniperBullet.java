package game;

public class SniperBullet extends Bullet {
	public SniperBullet(Coordinate pos, Coordinate target) {
		ImageLoader loader = ImageLoader.getLoader();
		this.picture = loader.getImage("resources/SniperBullet.png");
		this.posX = pos.x;
		this.posY = pos.y;		
		this.velocityX = (target.x - this.posX)/2;
		this.velocityY = (target.y - this.posY)/2;
		this.TTL = 0;
		this.strength = 50;
	}

	public void updatepos(double deltaTime) {
		posX += velocityX*deltaTime/2;
		posY += velocityY*deltaTime/2;
	}
	public void updateTTL(double deltaTime){
		TTL+=deltaTime/3;
	}
}
