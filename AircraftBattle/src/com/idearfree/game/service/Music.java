package com.idearfree.game.service;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Music {
//    static File file = new File("images/buibui.wav");
//    static File file1 = new File("images/bomb.wav");
//    static File file2 = new File("images/bg.wav");
//    static URI uri;
//    static URI uri1;
//    static URI uri2;
//    static URL url;
//    static URL url1;
//    static URL url2;
    static AudioClip aau;
    static AudioClip aau1;
    static AudioClip aau2;

    static {
//        try {
            //uri = file.toURI();
            //url= uri.toURL();
            URL url = Music.class.getResource("buibui.wav");
            aau = Applet.newAudioClip(url);

//            uri1 = file1.toURI();
//            url1 = uri1.toURL();
            URL url1 = Music.class.getResource("bomb.wav");
            aau1 = Applet.newAudioClip(url1);

//            uri2 = file2.toURI();
//            url2 = uri2.toURL();
            URL url2 = Music.class.getResource("bg.wav");
            aau2 = Applet.newAudioClip(url2);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    public void one() {
        aau.play();
    }
    public void bomb() {
        aau1.play();
    }
    public void bgm(){
        aau2.loop();
    }
    public void bgmClose(){
        aau2.stop();
    }
}
