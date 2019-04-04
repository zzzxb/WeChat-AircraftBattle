package com.idearfree.game.service;

import java.awt.*;

public interface BulletService {
    void moveTo();
    void moveTo(int n);
    void drawBullt(Graphics g);
    int getYValue();
    int getXValue();
}
