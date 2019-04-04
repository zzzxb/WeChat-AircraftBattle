package com.idearfree.game;

import com.idearfree.game.eneity.Settings;
import com.idearfree.game.service.GamePanel;
import com.idearfree.game.service.Mouse;

import javax.swing.*;

/**
 * AircraftBattle
 * 2018-10-5 ~ 2018-10-7
 * @author Zzzxb
 *
 * 写完代码后看了看别人的代码，同一个游戏代码风格却差距太多了，我的太杂乱无章了。
 * 设计模式，编程思想，算法...还有很大的需要的我学的呢。
 * 我跟别人的差距真的不是一点两点了，以后潜心修炼吧，让自己变的优秀起来!
 */
public class RunGame extends JFrame{
    GamePanel gamePanel = new GamePanel();
    Mouse mouse = new Mouse();

    public RunGame(){
        this.setContentPane(gamePanel);
        this.addMouseListener(mouse.mouseAdapter);
        this.addMouseMotionListener(mouse.mouseAdapter);
    }

    public static void main(String[] args) {
        RunGame runGame = new RunGame();
        runGame.setTitle(Settings.title);
        runGame.setSize(Settings.WIDTH, Settings.HIGH);
        runGame.setLocationRelativeTo(null);
        runGame.setResizable(false);
        runGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        runGame.setVisible(true);
    }
}
