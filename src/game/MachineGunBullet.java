
package game;

public class MachineGunBullet extends Bullet {
    public MachineGunBullet(Coordinate pos, Coordinate target) {
        ImageLoader loader = ImageLoader.getLoader();
        this.picture = loader.getImage("resources/MachineGunBullet.png");
        this.posX = pos.x;
        this.posY = pos.y;
        this.velocityX = target.x - this.posX;
        this.velocityY = target.y - this.posY;
        this.TTL = 0;
        this.strength = 20;
    }
    public void updatepos(double deltaTime) {
        posX += velocityX*deltaTime;
        posY += velocityY*deltaTime;
    }
    public void updateTTL(double deltaTime){
        TTL+=deltaTime;
    }
}

