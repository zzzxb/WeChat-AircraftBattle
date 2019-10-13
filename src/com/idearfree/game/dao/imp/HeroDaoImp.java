package com.idearfree.game.dao.imp;

import com.idearfree.game.dao.HeroDao;
import com.idearfree.game.eneity.Hero;
import com.idearfree.game.eneity.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class HeroDaoImp implements HeroDao {
    Hero hero = new Hero();
//    public static Image heroImage;
    public static BufferedImage heroImage;
    public static Image heroExplode;
    public static int i = 0;

    static{
        try {
            heroImage = ImageIO.read(HeroDaoImp.class.getResource("hero.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            URL url = HeroDaoImp.class.getResource("hero.gif");
//            InputStream input = url.openStream();
//            heroImage = javax.imageio.ImageIO.read(input);

            URL url1 = HeroDaoImp.class.getResource("heroExplode.gif");
            InputStream input1 = url1.openStream();
            heroExplode = javax.imageio.ImageIO.read(input1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //heroImage = new ImageIcon("images/hero.gif").getImage();
        //heroExplode = new ImageIcon("images/heroExplode.gif").getImage();
    }

    public HeroDaoImp(){
        hero.setLife(Settings.heroLife);
        moveTO(Settings.WIDTH / 2 - 33
                ,Settings.HIGH - 120);
    }

    public int HeroLife(int n){
        hero.setLife(hero.getLife() - n);
        return hero.getLife();
    }

    @Override
    public int getXValue() {
        return hero.getxValue();
    }

    @Override
    public int getYValue() {
        return hero.getyValue();
    }


    @Override
    public void moveTO(int x, int y) {
        if(x >= 0 && x < 250) {
            hero.setxValue(x);
        }
        if(y >= 0 && y < 470) {
            hero.setyValue(y);
        }
    }

    @Override
    public void drawHero(Graphics g) {
        g.drawImage(heroImage,hero.getxValue(),hero.getyValue(),null);
    }

    @Override
    public void Explode(Graphics g) {
        g.drawImage(heroExplode,hero.getxValue(),hero.getyValue(),null);
    }
}
