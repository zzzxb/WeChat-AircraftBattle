package com.idearfree.game.dao.imp;

import com.idearfree.game.dao.BulletDao;
import com.idearfree.game.eneity.Bullet;
import com.idearfree.game.eneity.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BulletDaoImp implements BulletDao{
    Bullet bullet = new Bullet();
    public static Image bulletImg;

    static{
        //bulletImg = new ImageIcon("images/bullet.png").getImage();
        try {
            URL url = HeroDaoImp.class.getResource("bullet.png");
            InputStream input = null;
            input = url.openStream();
            bulletImg = javax.imageio.ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveTo(int x, int y) {
        bullet.setxValue(x);
        bullet.setyValue(y);
    }

    @Override
    public void drawBullt(Graphics g) {
        g.drawImage(bulletImg,bullet.getxValue(),bullet.getyValue(),null);
    }

    @Override
    public int getYValue() {
        return bullet.getyValue();
    }

    @Override
    public int getXValue() {
        return bullet.getxValue();
    }
}
