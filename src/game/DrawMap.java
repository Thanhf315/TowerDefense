package game;

import java.awt.*;

public class DrawMap {
    private Image backdrop;
    private Image tree1;
    private Image tree2;
    private Image spawner;
    private Image road1;
    private Image road2;
    private Image road3;
    private Image road4;
    private Image road5;
    private Image road6;
    private Image rock1;
    private Image rock2;
    private Image chest1;
    private Image chest2;
    private Image bone;

    public DrawMap(Graphics g) {
        ImageLoader loader = ImageLoader.getLoader();
        backdrop = loader.getImage("resources/Dirt.png");
        tree1 = loader.getImage("resources/Tree1.png");
        tree2 = loader.getImage("resources/Tree2.png");
        spawner = loader.getImage("resources/spawner.png");
        road1 = loader.getImage("resources/Road1.png");
        road2 = loader.getImage("resources/Road2.png");
        road3 = loader.getImage("resources/Road3.png");
        road4 = loader.getImage("resources/Road4.png");
        road5 = loader.getImage("resources/Road5.png");
        road6 = loader.getImage("resources/Road6.png");
        chest1 = loader.getImage("resources/Chest1.png");
        chest2 = loader.getImage("resources/Chest2.png");
        rock1 = loader.getImage("resources/Rock1.png");
        rock2 = loader.getImage("resources/Rock2.png");
        bone = loader.getImage("resources/Bone.png");
        g.drawImage(backdrop, 0, 0, null);
        g.drawImage(road1, 0, 600, null);
        g.drawImage(spawner, 0, 550, null);
        g.drawImage(road2, 500, 400, null);
        g.drawImage(road3, 600, 400, null);
        g.drawImage(road4, 900, 300, null);
        g.drawImage(road5, 1300, 100, null);
        g.drawImage(road6, 1300, 0, null);
        g.drawImage(bone, 0, 0, 400, 200, null);
        g.drawImage(chest2, 0, 200, null);
        g.drawImage(chest1, 100, 400, null);
        g.drawImage(bone, 100, 500, null);
        g.drawImage(tree2, 300, 300, null);
        g.drawImage(chest2, 500, 0, 350, 350, null);
        g.drawImage(rock2, 1000, 600, 400, 200, null);
        g.drawImage(rock1, 600, 300, null);
        g.drawImage(tree2, 700, 500, 300, 300, null);
        g.drawImage(tree1, 200, 700, null);
        g.drawImage(tree1, 300, 700, null);
        g.drawImage(rock2, 900, 100, null);
        g.drawImage(bone, 1100, 400, null);
        g.drawImage(tree1, 1200, 100, null);
        g.drawImage(chest1, 1300, 500, null);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1400, 0, 300, 800);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        MachineGunTower machineguntower = new MachineGunTower(new Coordinate(1500, 350));
        machineguntower.draw(g);
        g.drawString("MachineGunTower Cost: 200", 1410, 475);
        SniperTower snipertower = new SniperTower(new Coordinate(1500, 500));
        snipertower.draw(g);
        g.drawString("SniperTower Cost: 200", 1450, 625);
        NormalTower NormalTower = new NormalTower(new Coordinate(1500, 650));
        NormalTower.draw(g);
        g.drawString("NormalTower Cost: 100", 1450, 775);
    }
}
