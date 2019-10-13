package com.idearfree.game.service.imp;


import com.idearfree.game.dao.HeroDao;
import com.idearfree.game.dao.imp.HeroDaoImp;
import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.Collision;
import com.idearfree.game.service.HeroService;
import com.idearfree.game.service.Music;

import java.awt.*;

public class HeroServiceImp implements HeroService {
   static HeroDao heroImp = new HeroDaoImp();
   public static int time1 = 0,time2 = 0;
    static Music music = new Music();

    @Override
    public void moveTO(int x, int y) {
        if(Settings.heroLife > 0){
            x -= 33; y -= 60;
            heroImp.moveTO(x, y);
        }
    }

    public void drawBullet(Graphics g){
        time1++;
        if(time1 % 200 == 0 && Collision.heroBulletGroupSize < Settings.heroBulletInventory) {
            Collision.heroBulletGroup.add(new BulletServiceImp(
                    heroImp.getXValue()+8,heroImp.getYValue()+14));
            Collision.heroBulletGroup.add(new BulletServiceImp(
                    heroImp.getXValue()+52,heroImp.getYValue()+14));
            time1 = 0;
            music.one();
        }

    }

    @Override
    public void drawHero(Graphics g) {
        if(Settings.heroLife > 0){
            heroImp.drawHero(g);
            if(Settings.start){
                drawBullet(g);
            }
        }else{
                heroImp.Explode(g);
        }
    }
}
