package com.idearfree.game.service;

import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.imp.EnemyServiceImp;
import com.idearfree.game.service.imp.HeroServiceImp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GamePanel extends JPanel {
    public Image homeBackground;
    public static Image startBackground;
    public HeroService heroService = new HeroServiceImp();
    EnemyService enemy = new EnemyServiceImp();
    int n = 0; //延迟飞机飞行速度
    int delay = 0; //延迟飞机出现间隔时间
    static Music music = new Music();
    String level = "";

    public GamePanel(){
        try {
            music.bgm();
            URL url = GamePanel.class.getResource("background1.png");
            InputStream input = url.openStream();
            homeBackground = javax.imageio.ImageIO.read(input);

            URL url1 = GamePanel.class.getResource("background2.png");
            InputStream input1 = url1.openStream();
            startBackground = javax.imageio.ImageIO.read(input1);
//            homeBackground = new ImageIcon("images/background1.png").getImage();
//            startBackground = new ImageIcon("images/background2.png").getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        if(Settings.start && Settings.mouseEnter){ //开始
            paintStartBackground(g);
            heroService.moveTO(Settings.xValue, Settings.yValue);
            paintEnemy(g);
            Collision.run(g);
            paintHero(g);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Tempus Sans ITC",1,16));
            g.drawString("LIFE: " + Settings.heroLife, 5, 45);
            g.drawString("SCORE: " + Settings.score, 5, 30);
            g.drawString("LEVEL: " + Settings.thisLevel, 5, 15);
        }else if(Settings.heroLife <= 0) {
            Settings.heroLife = 3;
            Settings.saveLevel = 0;
            Settings.score = 0;
            Settings.xValue = 160 ;
            Settings.xValue = 520 ;
            Collision.heroBulletGroup.clear();
            Collision.explodeGroup.clear();
            Collision.enemyGroup.clear();
            Collision.enemyBulletGroup.clear();
            Collision.heroBulletGroupSize = 0;
            Collision.enemyBulletGroupSize = 0;
            Collision.enemyGroupSize = 0;
            heroService.moveTO(160, 520);
        }else if(!Settings.mouseEnter && Settings.start){
            paintStartBackground(g);
            g.setFont(new Font("Tempus Sans ITC",2,60));
            g.drawString("Pause", 90, 200);
        }else{
            paintHomeBackground(g);
        }

        Thried thried = new Thried();
        thried.run();
    }
public class Thried implements Runnable {

        @Override
        public void run() {
            repaint();
            try {
                Thread.sleep(Settings.bulletSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintHomeBackground(Graphics g) {
        g.drawImage(homeBackground,0,0,null);
    }

    public void paintStartBackground(Graphics g) {
        g.drawImage(startBackground,0,0,null);
    }

    public void paintHero(Graphics g) {
        heroService.drawHero(g);
    }

    public void paintEnemy(Graphics g) {
        n++; delay++;
        if(Settings.thisLevel == Settings.nextLevel){
            Settings.level = Settings.level1;
            Settings.saveLevel = 0;
            Settings.nextLevel++;
        }

        if(delay % 400 == 0){
            for(int i = Settings.saveLevel; i < Settings.level.length / 6 ;i++) {
                int count = i * 6;
                int x = Settings.level[count],
                        y = Settings.level[count + 1],
                        life = Settings.level[count + 2],
                        type = Settings.level[count + 3],
                        road = Settings.level[count + 4],
                        delay1 = Settings.level[count + 5];
                if(delay1 == 66){
                    level = "";
                    Settings.saveLevel = i + 1;
                    break;
                }else if(delay1 >= 1 && delay1 < 9) {
                    level = "LEVEL " + delay1;
                    Settings.saveLevel = i + 1;
                    break;
                }else if( delay1 == 9){
                    level = "Warning";
                    Settings.saveLevel = i + 1;
                    break;
                }

               Collision.enemyGroup.add(new EnemyServiceImp(x, y, life, type));

                if(delay1 == 99){
                    level = "";
                    Settings.saveLevel = i + 1;
                    break;
                }
            }
        }
        g.setColor(Color.RED);
        g.setFont(new Font("Snap ITC",1,20));
        g.drawString(level, 110,100);

        for(int i = 0; i < Collision.enemyGroupSize; i++) {
            enemy = Collision.enemyGroup.get(i);
            int x = enemy.getXValue(),
                y = enemy.getYValue();

            if(y > Settings.HIGH){
                Collision.enemyGroup.remove(enemy);
                Collision.enemyGroupSize--;
            }
            if(n % Settings.enemySpeed == 0){
                enemy.moveTo();
            }
            enemy.drawAircraft(g);
        }

    }
}
