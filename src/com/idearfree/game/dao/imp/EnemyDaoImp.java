package com.idearfree.game.dao.imp;


import com.idearfree.game.dao.EnemyDao;
import com.idearfree.game.eneity.Enemy;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class EnemyDaoImp implements EnemyDao {
    public static Image aircraft;
    public static Image aircraftExplode;
    public static Image aircraft1;
    public static Image aircraftExplode1;
    public static Image aircraft2;
    public static Image aircraftExplode2;
    Enemy enemy = new Enemy();

    static {
        try {
            URL url = HeroDaoImp.class.getResource("enemy1.png");
            InputStream input = url.openStream();
            aircraft= javax.imageio.ImageIO.read(input);

            URL url1 = HeroDaoImp.class.getResource("enemy2.png");
            InputStream input1 = url1.openStream();
            aircraft1= javax.imageio.ImageIO.read(input1);

            URL url2 = HeroDaoImp.class.getResource("boos.png");
            InputStream input2 = url2.openStream();
            aircraft2= javax.imageio.ImageIO.read(input2);

            URL url3 = HeroDaoImp.class.getResource("enemyExplode1.gif");
            InputStream input3 = url3.openStream();
            aircraftExplode = javax.imageio.ImageIO.read(input3);

            URL url4 = HeroDaoImp.class.getResource("enemyExplode2.gif");
            InputStream input4 = url4.openStream();
            aircraftExplode1 = javax.imageio.ImageIO.read(input4);

            URL url5 = HeroDaoImp.class.getResource("boosExplode1.gif");
            InputStream input5 = url5.openStream();
            aircraftExplode2 = javax.imageio.ImageIO.read(input5);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        aircraft = new ImageIcon("images/enemy1.png").getImage();
//        aircraft1 = new ImageIcon("images/enemy2.png").getImage();
//        aircraft2 = new ImageIcon("images/boos.png").getImage();
//        aircraftExplode = new ImageIcon("images/enemyExplode1.gif").getImage();
//        aircraftExplode1 = new ImageIcon("images/enemyExplode2.gif").getImage();
//        aircraftExplode2 = new ImageIcon("images/boosExplode1.gif").getImage();
    }

    @Override
    public void moveTo(int x, int y) {
        enemy.setxValue(x);
        enemy.setyValue(y);
    }

    @Override
    public void drawAircraft(Graphics g) {
        g.drawImage(aircraft,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public void drawAircraft1(Graphics g) {
        g.drawImage(aircraft1,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public void drawAircraft2(Graphics g) {
        g.drawImage(aircraft2,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public void explode(Graphics g) {
        g.drawImage(aircraftExplode,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public void explode1(Graphics g) {
        g.drawImage(aircraftExplode1,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public void explode2(Graphics g) {
        g.drawImage(aircraftExplode2,enemy.getxValue(),enemy.getyValue(),null);
    }

    @Override
    public int getXValue() {
        return enemy.getxValue();
    }

    @Override
    public int getYValue() {
        return enemy.getyValue();
    }

    @Override
    public int getLife() {
        return enemy.getLife();
    }

    @Override
    public void setLife(int life) {
        enemy.setLife(life);
    }

    @Override
    public void setAircraftType(int type) {
        enemy.setAircraftType(type);
    }

    @Override
    public int getAircraftType() {
        return enemy.getAircraftType();
    }

}
