package game;

import java.awt.*;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import javax.swing.*;

enum GameState { SETUP, UPDATE, DRAW, WAIT, END }
public class Game implements Runnable{
    private PathPoints line;

    private GamePanel gamePanel;
    private GameState state;

    private int frameCounter;
    private long lastTime;

    private boolean placingMachineGunTower;
    private Tower newMachineGunTower;
    private boolean placingNormalTower;
    private Tower newNormalTower;
    private boolean placingSniperTower;
    private Tower newSniperTower;

    private double elapsedTime;
    private boolean gameIsOver;
    private boolean gameIsWon;

	public int[][] Map = {
			{0, 0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1 ,0},
			{0, 0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0},
			{0, 0 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1 ,0},
			{0, 0 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0},
			{1, 0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,1},
			{0, 0 ,0 ,1 ,1 ,0 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0},
			{0, 0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
			{1, 1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
	};

    int livesCounter;
    int scoreCounter;
    int killsCounter;

    List<Enemy> enemies;

    List<Tower> towers;

    List<Bullet> bullets;

    public Game ()
    {

        state = GameState.SETUP;
        gamePanel = new GamePanel(this);

        Thread t = new Thread(this);
        t.start();
    }

    public void run ()
    {

        while (true)
        {

            if (state == GameState.SETUP)
            {
                doSetupStuff();
            }

            else if (state == GameState.UPDATE)
            {
                doUpdateTasks();
            }

            else if (state == GameState.DRAW)
            {
                gamePanel.repaint();

                try { Thread.sleep(5); } catch (Exception e) {}
            }

            else if (state == GameState.WAIT)
            {

                try { Thread.sleep(100); } catch (Exception e) {}

                state = GameState.UPDATE;
            }

            else if (state == GameState.END) {}
        }
    }

    private void doSetupStuff ()
    {
    	JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Tower Defense");
        f.setContentPane(gamePanel);
        f.pack();
        f.setVisible(true);

        livesCounter = 10;
        scoreCounter = 2000;
        killsCounter = 0;

        frameCounter = 0;
        lastTime = System.currentTimeMillis();

		ClassLoader myLoader = this.getClass().getClassLoader();
        InputStream pointStream = myLoader.getResourceAsStream("resources/line.txt");
        Scanner s = new Scanner (pointStream);
        line  = new PathPoints(s);

        enemies = new LinkedList<Enemy>();

        towers = new LinkedList<Tower>();

        bullets = new LinkedList<Bullet>();

        placingMachineGunTower = false;
        newMachineGunTower = null;
        placingNormalTower = false;
        newNormalTower = null;
        placingSniperTower = false;
        newSniperTower = null;
        gameIsOver = false;
    	gameIsWon = false;
        state = GameState.UPDATE;
    }

    private void doUpdateTasks() {
		if (gameIsOver) {
			state = GameState.DRAW;
			return;
		}

		if (gameIsWon) {
			state = GameState.DRAW;
			return;
		}

		long currentTime = System.currentTimeMillis();
		elapsedTime = ((currentTime - lastTime) / 1000.0);
		lastTime = currentTime;

		for (Tower t : new LinkedList<Tower>(towers)) {
			t.interact(this, elapsedTime);

		}
			for (Bullet b : new LinkedList<Bullet>(bullets)) {
				b.interact(this, elapsedTime, b);
				if (b.isDone())
					bullets.remove(b);
			}

			for (Enemy e : new LinkedList<Enemy>(enemies)) {
				e.advance();
				if (e.getPosition().isAtTheEnd()) {
					enemies.remove(e);
					livesCounter--;
				}
			}

			this.generateEnemies();
			frameCounter++;
			this.placeMachineGunTower();
			this.placeNormalTower();
			this.placeSniperTowers();

			if (livesCounter <= 0) {
				gameIsOver = true;
				livesCounter = 0;
			}

			if (killsCounter >= 500) {
				gameIsWon = true;
				killsCounter = 500;
			}

			state = GameState.DRAW;

    }
    public void draw(Graphics g)
    {

        if (state != GameState.DRAW)
            return;

        new DrawMap(g);

		g.setColor(Color.BLACK);

		g.drawString("Lives: "  + livesCounter, 1450, 75);
		g.drawString("Money Earned: " + scoreCounter , 1450, 100);
		g.drawString("Enemies Stopped: " +killsCounter, 1450, 125);

		for(Tower t: new LinkedList<Tower>(towers))
			t.draw(g);

        for(Enemy e: new LinkedList<Enemy>(enemies))
		e.draw(g);



		for(Bullet b: new LinkedList<Bullet>(bullets))
			b.draw(g);
		g.setColor(Color.WHITE);
		for(int i= 50; i<=800; i+=50)
			g.drawLine(0, i, 1700, i);
		for(int i= 50; i<=1700; i+=50)
			g.drawLine(i, 0, i, 800);

        if(newMachineGunTower != null)
        	newMachineGunTower.draw(g);

        if(newNormalTower != null)
        	newNormalTower.draw(g);

        if(newSniperTower != null)
        	newSniperTower.draw(g);

        ImageLoader loader = ImageLoader.getLoader();
		Image endGame = loader.getImage("resources/game_over.png");

        if(livesCounter <= 0)
        	g.drawImage(endGame, 0, 0, null);

		if(killsCounter >= 500)
		{	g.setFont(new Font("Braggadocio", Font.ITALIC, 90));
        	g.drawString("You Win!!!", 10, 250);
		}

        state = GameState.WAIT;
    }

    public void generateEnemies()
    {
    	if(frameCounter % 30 == 0)
    	{
    		enemies.add(new NormalEnemy(line.getStart()));
    	}
 		else if(frameCounter % 25 == 0 && frameCounter >= 50)
 		{
 			enemies.add(new NormalEnemy(line.getStart()));
 		}
	 	else if(frameCounter % 20 == 0 && frameCounter >= 100)
	 	{
	 		enemies.add(new NormalEnemy(line.getStart()));
	 		enemies.add(new SmallerEnemy(line.getStart()));
	 	}
 		else if(frameCounter % 15 == 0 && frameCounter >= 150)
 		{
 			enemies.add(new NormalEnemy(line.getStart()));
 			enemies.add(new SmallerEnemy(line.getStart()));
 		}
	 	else if(frameCounter % 10 == 0 && frameCounter >= 200)
	 	{
	 		enemies.add(new NormalEnemy(line.getStart()));
	 		enemies.add(new SmallerEnemy(line.getStart()));
	 		enemies.add(new TankerEnemy(line.getStart()));
	 	}
	 	else if(frameCounter % 5 == 0 && frameCounter >= 250)
	 	{
	 		enemies.add(new NormalEnemy(line.getStart()));
	 		enemies.add(new SmallerEnemy(line.getStart()));
	 		enemies.add(new TankerEnemy(line.getStart()));
	 		enemies.add(new BossEnemy(line.getStart()));
	 	}
    }

    public void placeMachineGunTower()
    {
    	Coordinate mouseLocation = new Coordinate(gamePanel.mouseX, gamePanel.mouseY);

    	if(gamePanel.mouseX > 1500 && gamePanel.mouseX < 1600 &&
    		gamePanel.mouseY > 350 && gamePanel.mouseY < 450 &&
    		gamePanel.mouseIsPressed && scoreCounter >= 200)
    	{
	    		placingMachineGunTower= true;
	    		newMachineGunTower= new MachineGunTower(mouseLocation);
    	}
    	else if(gamePanel.mouseX > 0 && gamePanel.mouseX < 1400 &&
        	gamePanel.mouseY > 0 && gamePanel.mouseY < 800 &&
        	gamePanel.mouseIsPressed && placingMachineGunTower
        	&& line.distanceToPath(gamePanel.mouseX, gamePanel.mouseY) > 60)
		{
			int y = (int) (mouseLocation.x/100);
			int x = (int) (mouseLocation.y/100);
			if(Map[x][y]==1) {
				mouseLocation.x = (int) (mouseLocation.x / 100) * 100;
				mouseLocation.y = (int) (mouseLocation.y / 100) * 100;
				newMachineGunTower.setPosition(mouseLocation);
				towers.add(new MachineGunTower(mouseLocation));
				scoreCounter -= 200;
				newMachineGunTower = null;
				placingMachineGunTower = false;
				Map[x][y] = 0;
			}
    	}
    	if(newMachineGunTower != null)
    	{
    		newMachineGunTower.setPosition(mouseLocation);
    	}
    }

	public void placeSniperTowers()
	{
		Coordinate mouseLocation = new Coordinate(gamePanel.mouseX, gamePanel.mouseY);

		if(gamePanel.mouseX > 1500 && gamePanel.mouseX < 1600 &&
				gamePanel.mouseY > 500 && gamePanel.mouseY < 600 &&
				gamePanel.mouseIsPressed && scoreCounter >= 200)
		{
			placingSniperTower = true;
			newSniperTower = new SniperTower(mouseLocation);
		}
		else if(gamePanel.mouseX > 0 && gamePanel.mouseX < 1400 &&
				gamePanel.mouseY > 0 && gamePanel.mouseY < 800 &&
				gamePanel.mouseIsPressed && placingSniperTower
				&& line.distanceToPath(gamePanel.mouseX, gamePanel.mouseY) > 60)
		{
			int y = (int) (mouseLocation.x/100);
			int x = (int) (mouseLocation.y/100);
			if(Map[x][y]==1) {
				mouseLocation.x = (int) (mouseLocation.x / 100) * 100;
				mouseLocation.y = (int) (mouseLocation.y / 100) * 100;
				newSniperTower.setPosition(mouseLocation);
				towers.add(new SniperTower(mouseLocation));
				scoreCounter -= 200;
				newSniperTower = null;
				placingSniperTower = false;
				Map[x][y] = 0;
			}
		}

		if(newSniperTower != null)
		{
			newSniperTower.setPosition(mouseLocation);
		}
	}

	public void updatehealth(Enemy e, Bullet b) {
    	e.health -= (b.strength-e.armor);
	}

    public void placeNormalTower()
    {
    	Coordinate mouseLocation = new Coordinate(gamePanel.mouseX, gamePanel.mouseY);

    	if(gamePanel.mouseX > 1500 && gamePanel.mouseX < 1600 &&
    		gamePanel.mouseY > 650 && gamePanel.mouseY < 750 &&
    		gamePanel.mouseIsPressed && scoreCounter >= 100)
    	{
	    		placingNormalTower = true;
	    		newNormalTower = new NormalTower(mouseLocation);
    	}
    	else if(gamePanel.mouseX > 0 && gamePanel.mouseX < 1400 &&
        	gamePanel.mouseY > 0 && gamePanel.mouseY < 800 &&
        	gamePanel.mouseIsPressed && placingNormalTower
        	&& line.distanceToPath(gamePanel.mouseX, gamePanel.mouseY) > 60)
		{
			int y = (int) (mouseLocation.x/100);
			int x = (int) (mouseLocation.y/100);
			if(Map[x][y]==1) {
				mouseLocation.x = (int) (mouseLocation.x/100)*100;
				mouseLocation.y = (int) (mouseLocation.y/100)*100;
				newNormalTower.setPosition(mouseLocation);
				towers.add(new NormalTower(mouseLocation));
				scoreCounter -= 100;
				newNormalTower = null;
				placingNormalTower = false;
				Map[x][y] = 0;
			}
    	}
    	if(newNormalTower != null)
    	{
    		newNormalTower.setPosition(mouseLocation);
    	}
    }


}
