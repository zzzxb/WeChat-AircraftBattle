package com.idearfree.game.service.imp;

import com.idearfree.game.dao.BulletDao;
import com.idearfree.game.dao.imp.BulletDaoImp;
import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.BulletService;

import java.awt.*;

public class BulletServiceImp implements BulletService {
    BulletDao bulletImp = new BulletDaoImp();

    BulletServiceImp(int x, int y) {
        bulletImp.moveTo(x, y);
    }
    @Override
    public void moveTo() {
        int x = bulletImp.getXValue();
        int y = bulletImp.getYValue() - Settings.bulletSpeed;
        bulletImp.moveTo(x, y);
    }

    @Override
    public void moveTo(int n) {
        int x = bulletImp.getXValue();
        int y = bulletImp.getYValue() + n;
        bulletImp.moveTo(x, y);
    }

    @Override
    public void drawBullt(Graphics g) {
        bulletImp.drawBullt(g);
    }

    @Override
    public int getYValue() {
        return bulletImp.getYValue();
    }

    @Override
    public int getXValue() {
        return bulletImp.getXValue();
    }
}
