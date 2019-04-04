package com.idearfree.game.dao;

import java.awt.*;

public interface BulletDao {
    void moveTo(int x, int y);
    void drawBullt(Graphics g);
    int getYValue();
    int getXValue();
}
