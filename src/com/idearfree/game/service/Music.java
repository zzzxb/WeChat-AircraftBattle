package com.idearfree.game.service;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music {
    static AudioClip aau;
    static AudioClip aau1;
    static AudioClip aau2;
    static URL url1;
    static URL url;

    static {
            url1 = Music.class.getResource("bomb.wav");
            url = Music.class.getResource("buibui.wav");
            URL url2 = Music.class.getResource("bg.wav");
            aau2 = Applet.newAudioClip(url2);
    }

    public void one() {
        aau = Applet.newAudioClip(url);
        aau.stop();
        aau.play();
    }
    public void bomb() {
        aau1 = Applet.newAudioClip(url1);
        aau1.stop();
        aau1.play();
    }
    public void bgm(){
        aau2.loop();
    }
    public void bgmClose(){
        aau2.stop();
    }
}
