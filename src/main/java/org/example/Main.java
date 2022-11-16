package org.example;


public class Main {
    static Engine engine = new Engine();
    public static void main(String[] args) {
        System.out.println("add");
        engine.addNum();
        engine.show();
        new Paint(engine.getMap());
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