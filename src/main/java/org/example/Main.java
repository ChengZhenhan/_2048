package org.example;


import javax.swing.*;

public class Main {
    static Engine engine = new Engine();
    public static void main(String[] args) {
        System.out.println("add");
        engine.addNum();
        engine.show();
        JFrame frame = new JFrame("2048");
        Paint _2048 = new Paint(engine.getMap());
        frame.add(_2048);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(_2048.getKeyListeners()[0]);
        Thread thread = new Thread(_2048);
        thread.start();
//        new MoveTest();
//        System.out.println("up");
//        engine.setMap(engine.up());
//        engine.show();
//        System.out.println("down");
//        engine.setMap(engine.down());
//        engine.show();
//        System.out.println("right");
//        engine.setMap(engine.right());
//        engine.show();
//        System.out.println("left");
//        engine.setMap(engine.left());
//        engine.show();
    }
}
