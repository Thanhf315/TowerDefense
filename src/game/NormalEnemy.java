package game;

	public class NormalEnemy extends Enemy {
		NormalEnemy(PathPosition p) {
			ImageLoader loader = ImageLoader.getLoader();
			this.enemy = loader.getImage("resources/NormalEnemy.png");
			this.position = p;
			this.width = 100;
			this.height = 100;
			this.health = 100;
			this.speed = 3;
			this.armor = 3;
			this.reward = 1;
		}

	}