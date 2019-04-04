package com.idearfree.game.dao;


import java.awt.*;

public interface HeroDao {
    void moveTO(int x, int y);
    void drawHero(Graphics g);
    void Explode(Graphics g);
    int HeroLife(int n);
    int getXValue();
    int getYValue();
}
