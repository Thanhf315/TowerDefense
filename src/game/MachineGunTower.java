package game;

import java.util.List;

public class MachineGunTower extends Tower {

    public MachineGunTower(Coordinate pos) {
        ImageLoader loader = ImageLoader.getLoader();
        this.tower = loader.getImage("resources/MachineGunTower.png");
        this.position = pos;
        this.width = 50;
        this.height = 50;
    }

    public void interact(Game game, double deltaTime) {
        timeSinceLastFire += deltaTime;
        if(timeSinceLastFire < 1)
            return;

        List<Enemy> enemies = game.enemies;

        for(Enemy e: enemies) {
            Coordinate enemyPos = e.getPosition().getCoordinate();
            double dx, dy, dist;
            dx = enemyPos.x - position.x;
            dy = enemyPos.y - position.y;
            dist = Math.sqrt((dx*dx) + (dy*dy));

            Coordinate pos = new Coordinate(position.x, position.y);

            if(dist <= 200) {
                MachineGunBullet machinegunbullet = new MachineGunBullet(pos, enemyPos);
                game.bullets.add(machinegunbullet);
                timeSinceLastFire = 0;
                return;
            }
        }
    }
}

