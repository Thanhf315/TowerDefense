package game;

public class TankerEnemy extends Enemy 
{
	TankerEnemy(PathPosition p)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/TankerEnemy.png");
		this.position = p;
		this.width = 150;
		this.height = 150;
		this.health = 300;
		this.speed = 3;
		this.armor = 5;
		this.reward = 3;
	}

}

