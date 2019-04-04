package com.idearfree.game.service;

import java.awt.*;

public interface EnemyService {
    void moveTo();
    void drawAircraft(Graphics g);
    void drawExplode(Graphics g);
    void drawBullet(Graphics g);
    int getXValue();
    int getYValue();
    int getLife();
    void setLife(int n);
    int getType();
}
