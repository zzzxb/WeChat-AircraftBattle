package com.idearfree.game.dao;

import java.awt.*;

public interface EnemyDao {
    void moveTo(int x, int y);
    void drawAircraft(Graphics g);
    void drawAircraft1(Graphics g);
    void drawAircraft2(Graphics g);
    void explode(Graphics g);
    void explode1(Graphics g);
    void explode2(Graphics g);
    int getXValue();
    int getYValue();
    int getLife();
    void setLife(int life);
    void setAircraftType(int type);
    int getAircraftType();
}
