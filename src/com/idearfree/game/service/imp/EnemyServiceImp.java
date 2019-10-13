package com.idearfree.game.service.imp;

import com.idearfree.game.dao.EnemyDao;
import com.idearfree.game.dao.imp.EnemyDaoImp;
import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.Collision;
import com.idearfree.game.service.EnemyService;

import java.awt.*;
import java.util.Random;


public class EnemyServiceImp implements EnemyService {
    EnemyDao enemyImp = new EnemyDaoImp();
    int n = 0,one = 0, two = 0, three= 0;
    int time = 0;
    int randomNumber;

    public EnemyServiceImp() {
    }
    public EnemyServiceImp(int x, int y , int type) {
        enemyImp.moveTo(x,y);
        enemyImp.setAircraftType(type);
    }

    public EnemyServiceImp(int x, int y, int life, int type) {
        setLife(life);
        enemyImp.moveTo(x,y);
        enemyImp.setAircraftType(type);
        java.util.Random random = new Random();
        randomNumber = random.nextInt(4);
    }

    @Override
    public void moveTo() {
        int x = enemyImp.getXValue();
        int y = enemyImp.getYValue();
        if(enemyImp.getAircraftType() == 3){ // BOOS 移动
            if(enemyImp.getLife() <= 50 && enemyImp.getLife() >=40 && x == 110) {
                if (y < 100) { //向下移
                    y += 1;
                }else if(y <= 100){
                    n = 1;
                }
            }else if(n == 0){//向右移动
                x += 1;
                if(x >= 220) n = 1;
            }else if(n == 1){//向左移动
                x -= 1;
                if(x == 0) n = 0;
            }
        }else if(randomNumber == 0){ //直下移动
            y += 1;
        }else if(randomNumber == 1){ // 左右弯曲移动
            if(n == 0) {
                if(x >= 160) n = 3;
                else n = 4;
            }
                if (n == 3){  x -= 1; y += 1;}
                else if(n == 4){ x += 1; y += 1;}

                if(x <= -50) n = 4;
                else if(x >= 400) n =3;

        }else if(randomNumber == 2){
            if(n == 0){
                if(x <= 180) n = 1;
                else n = 3;
            }
            if(n == 1){ //向右下移动
                x += 1; y += 1;
                if(y >= 100) n =2;
            }else if(n == 2){ //向右上移动
                x += 1; y -= 1;
                if( y <= 20) n = 3;
            }else if(n == 3){ //向左下移动
                x -= 1; y += 1;
                if(y >= 100) n =4;
            }else if( n == 4){ //向左上移动
                x -= 1; y -= 1;
                if(y <= 20) n = 5;
            }else{
                y += 1;
            }
        }else if(randomNumber == 3){
            if(enemyImp.getXValue() >= 160){
                x += 1; y += 3;
            }else{
                x -= 1; y += 3;
            }
        }

        if(enemyImp.getLife() > 0){
            enemyImp.moveTo(x, y);
        }
    }

    @Override
    public void drawAircraft(Graphics g) {
        switch(enemyImp.getAircraftType()){
            case 1: enemyImp.drawAircraft(g);break;
            case 2: enemyImp.drawAircraft1(g);break;
            case 3: enemyImp.drawAircraft2(g);break;
        }
        drawBullet(g);
    }

    @Override
    public void drawExplode(Graphics g) {
        switch(enemyImp.getAircraftType()) {
            case 1:
                enemyImp.explode(g);
                break;
            case 2:
                enemyImp.explode1(g);
                break;
            case 3:
                enemyImp.explode2(g);
                break;
        }

    }

    @Override
    public void drawBullet(Graphics g) {
        time++;
        java.util.Random random = new Random();
        int speed = (random.nextInt(5) + 1) * 50; // 发子弹间隔
        int width = 0, high = 0;
        if(time % speed == 0 && Collision.enemyBulletGroupSize < Settings.enemyBulletInventory) {
            switch (enemyImp.getAircraftType()){
                case 1:
                    width = 12; high = 10;
                    Collision.enemyBulletGroup.add(new BulletServiceImp(
                            enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                    break;
                case 2:
                    width = 0; high = 30;
                    Collision.enemyBulletGroup.add(new BulletServiceImp(
                        enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                    width = 38; high = 30;
                    Collision.enemyBulletGroup.add(new BulletServiceImp(
                            enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                    break;
                case 3:
                    java.util.Random random1 = new Random();
                    int i = random1.nextInt(4);
                    if(i <= 1){
                        width = 20; high = 80;
                        Collision.enemyBulletGroup.add(new BulletServiceImp(
                            enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                        width = 40; high = 100;
                        Collision.enemyBulletGroup.add(new BulletServiceImp(
                            enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                        width = 80; high = 80;
                        Collision.enemyBulletGroup.add(new BulletServiceImp(
                             enemyImp.getXValue() + width ,enemyImp.getYValue() +high));
                        break;
                    }else if(i == 2){
                        for(int j = 0; i < 6; i ++){
                            if(i < 3) {
                                width = 10; high = 60;
                                Collision.enemyBulletGroup.add(new BulletServiceImp(
                                        enemyImp.getXValue() + width + i * 20
                                        ,enemyImp.getYValue() +high + i * 20));
                            }else{
                                width = 100; high = 60;
                                Collision.enemyBulletGroup.add(new BulletServiceImp(
                                        enemyImp.getXValue() + width - i * 20
                                        ,enemyImp.getYValue() +high + i * 20));

                            }

                        }
                    }else if(i == 3){
                        int bullet3[] = {0,60,190,80, 30, 110, 10, 90,
                                140, 60, 20, 110, 60, 100,80, 200, 100, 0, 100, 10, 180, 50};
                        for(int k = 0; k < bullet3.length/2; k++){
                            Collision.enemyBulletGroup.add(new BulletServiceImp(
                                    enemyImp.getXValue() + bullet3[k + 1]
                                    ,enemyImp.getYValue() +bullet3[k + 2]));
                        }
                    }
            }
            time = 0;
        }
    }

    @Override
    public int getXValue() {
        return enemyImp.getXValue();
    }

    @Override
    public int getYValue() {
        return enemyImp.getYValue();
    }

    @Override
    public void setLife(int n) {
        enemyImp.setLife(n);
    }

    @Override
    public int getType() {
        return enemyImp.getAircraftType();
    }

    @Override
    public int getLife() {
        return enemyImp.getLife();
    }
}
