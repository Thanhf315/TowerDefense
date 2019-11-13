
package game;

public class MachineGunBullet extends Bullet
{
    public MachineGunBullet(Coordinate pos, Coordinate target)
    {
        ImageLoader loader = ImageLoader.getLoader();
        this.picture = loader.getImage("resources/MachineGunBullet.png");
        this.posX = pos.x;
        this.posY = pos.y;
        this.velocityX = target.x - this.posX;
        this.velocityY = target.y - this.posY;
        this.ageInSeconds = 0;
    }
}

