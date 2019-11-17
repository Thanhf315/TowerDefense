package game;

public class BossEnemy extends Enemy {
	BossEnemy(PathPosition p) {
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/BossEnemy.png");
		this.position = p;
		this.width = 200;
		this.height = 200;
		this.health = 500;
		this.armor = 8;
		this.reward = 10;
	}
}

