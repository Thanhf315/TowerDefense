package game;

public class TankerEnemy extends Enemy 
{
	TankerEnemy(PathPosition p)
	{
		ImageLoader loader = ImageLoader.getLoader();
		this.enemy = loader.getImage("resources/TankerEnemy.png");
		this.position = p;
		this.width = 100;
		this.height = 100;
		this.speed = 1;
	}

}

