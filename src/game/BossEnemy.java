package game;

public class BossEnemy extends Enemy 
{

	BossEnemy(PathPosition p)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/BossEnemy.png");
		this.position = p;
		this.width = 100;
		this.height = 100;
		this.speed = 1;
	}

}

