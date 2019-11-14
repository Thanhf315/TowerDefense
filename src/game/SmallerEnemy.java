package game;


public class SmallerEnemy extends Enemy 
{
	SmallerEnemy(PathPosition p)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/SmallerEnemy.png");
		this.position = p;
		this.width = 80;
		this.height = 80;
		this.health = 50;
		this.speed = 4;
		this.armor = 0;
		this.reward = 2;

	}

}

