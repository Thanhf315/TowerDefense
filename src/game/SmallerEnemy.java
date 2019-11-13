package game;


public class SmallerEnemy extends Enemy 
{
	SmallerEnemy(PathPosition p)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/SmallerEnemy.png");
		this.position = p;
		this.width = 100;
		this.height = 100;
		this.speed = 3;
	}

}

