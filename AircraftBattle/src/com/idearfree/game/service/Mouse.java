package com.idearfree.game.service;

import com.idearfree.game.eneity.Settings;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse{
   public MouseAdapter mouseAdapter= new MouseAdapter() {
       public void mouseClicked(MouseEvent e) {
           if(e.getClickCount() == 6){
                Settings.heroLife += 1;
           }
       }
        public  void mouseExited(MouseEvent e){
            Settings.mouseEnter = false;
        }

        public void mouseEntered(MouseEvent e) {
            Settings.mouseEnter = true;
        }

        @Override
        public void mouseReleased(MouseEvent e){
            if(!Settings.start){
                Settings.start = true;
            }
        }
        public void mouseMoved(MouseEvent e) {
            if(Settings.start){
                Settings.xValue = e.getX();
                Settings.yValue = e.getY();
            }
        }
    };
}
