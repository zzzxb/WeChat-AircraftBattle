package com.idearfree.game.service;

import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.imp.EnemyServiceImp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Collision {
    public static List<EnemyService> enemyGroup = new ArrayList();
    public static List<BulletService> heroBulletGroup = new ArrayList();
    public static List<EnemyService> explodeGroup = new ArrayList();
    public static List<BulletService> enemyBulletGroup = new ArrayList();

    public static int enemyGroupSize;
    public static int heroBulletGroupSize;
    public static int enemyBulletGroupSize;
    public static int explodeGroupSize;
    static Music music = new Music();
    static int time = 0;
    static int time2 = 0;
    static int time3 = 0;
    static int gameOver = 0;

    public static void run(Graphics g){
        enemyGroupSize = enemyGroup.size();
        heroBulletGroupSize = heroBulletGroup.size();
        explodeGroupSize = explodeGroup.size();
        enemyBulletGroupSize = enemyBulletGroup.size();

        overstepHeroBullet(g);
        overstepEnemyBullet(g);
        oversetopHeroAndEnemy(g);
        Explode(g);
    }

    public static void Explode(Graphics g) {
        time2++;
        for(int i = 0; i < explodeGroupSize; i++){
            EnemyService explode = explodeGroup.get(i);
            explode.drawExplode(g);

            //这里还需改进，碰撞时间不一样但移除时间一样有的不显示效果
            if(time2 % 200 == 0){
            explodeGroup.remove(explode);
            explodeGroupSize--;
            time2 = 0;
            }
        }
    }

    public static int enemyCollision(Graphics g, int x, int y, int width, int high) {
        int c = 0; //出bug了，逻辑有点问题
        if (Settings.heroLife > 0)
        for(int i = 0; i < enemyGroupSize; i++){
            EnemyService enemy = enemyGroup.get(i);
            int enemyX = enemy.getXValue(),
                enemyY = enemy.getYValue();

            if(y < enemyY + 20 && y + high > enemyY && x + width> enemyX
                    && x < enemyX + 32 && enemy.getType() == 1) {
                c = 1;
                enemy.setLife(enemy.getLife() - 1);
            }else if (y < enemyY + 55 && y + high > enemyY && x < enemyX + 46
                    && x + width > enemyX && enemy.getType() == 2){
                enemy.setLife(enemy.getLife() - 1);
                c = 1;
            }else if(y < enemyY + 160 && y + high > enemyY && x < enemyX + 110
                    && x + width > enemyX && enemy.getType() == 3) {
                enemy.setLife(enemy.getLife() - 1);
                c = 1;
            }
            if (enemy.getLife() <= 0) { //发生碰撞后保存并移除
                if(enemy.getType() == 3){
                    Settings.thisLevel ++;
                }
                Settings.score +=  enemy.getType();
                music.bomb();
                enemyGroup.remove(enemy);
                explodeGroup.add(new EnemyServiceImp(
                        enemy.getXValue(),enemy.getYValue(),enemy.getType()));
                enemyGroupSize--;
                c = 1;
            }
            if( c == 1){
                return 1;
            }
        }
        return 0;
    }

    public static void oversetopHeroAndEnemy(Graphics g){
        int x = Settings.xValue - 15;
        int y = Settings.yValue - 55;
        int n = enemyCollision(g,x,y,20,65);

        if(n == 1 || Settings.heroLife <= 0){
            if(Settings.heroLife > 0){
                Settings.heroLife -= 1;
            }

            if(Settings.heroLife <= 0){
                gameOver ++;


                if(gameOver % 2000 == 0){
                    Settings.start = false;
                    g.setColor(Color.BLACK);
                    gameOver = 0;
                }else {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Snap ITC", 1, 20));
                    g.drawString("Game Over!", 100, 200);
                }

            }
        }
    }
    public static int overstepHeroAndBullet(Graphics g,int x, int y, int width, int high) {
        int herox = Settings.xValue - 25;
        int heroy = Settings.yValue - 55;
        if(x + width > herox && x < herox + 40 && y + high > heroy && y < heroy + 75
                && Settings.heroLife > 0 && Settings.heroLife > 0){

            Settings.heroLife -= 1;
            return 1;
        }
        return 0;
    }

    public static void overstepHeroBullet(Graphics g) {
        time++;
        for(int i = 0 ; i <heroBulletGroupSize; i++) {
            BulletService bs = heroBulletGroup.get(i);
            int n = enemyCollision(g,bs.getXValue(),bs.getYValue(),5,10);


            if(bs.getYValue()< 0 || n == 1){
                heroBulletGroup.remove(bs);
                heroBulletGroupSize--;
            }

            if(time % 1== 0 && n != 1 ) {
                bs.moveTo();
                time = 0;
            }

            bs.drawBullt(g);
        }
    }

    public static void overstepEnemyBullet(Graphics g) {
        time3++;
        for(int i = 0 ; i <enemyBulletGroupSize; i++) {
            BulletService bs = enemyBulletGroup.get(i);

            int n = overstepHeroAndBullet(g,bs.getXValue(),bs.getYValue(), 5, 10);
            if(bs.getYValue() > 580 || n == 1){
                enemyBulletGroup.remove(bs);
                enemyBulletGroupSize--;
            }

            java.util.Random random = new Random();
            int speed = (random.nextInt(3) + 1); // 敌方子弹速度
            if(time3 % speed == 0) {
                bs.moveTo(1);
                time3 = 0;
            }

            bs.drawBullt(g);
        }
    }
}
