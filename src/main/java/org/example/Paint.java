package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


import static java.lang.System.exit;


class Paint extends JPanel implements Runnable {
    int[][] map;
    /**
     *  0 -> 不移动
     *  1 -> 初步移动
     *  2 -> 合并
     */
    int step = 0;
    Color BG_COLOR = new Color(0xbbada0);
    private static final int BLOCK_SIZE = 64;
    private static final int BLOCK_MARGIN = 16;
    public Paint(int[][] map) {
        this.map = Arrays.copyOf(map,map.length);
//        this.setSize(500,500);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setTitle("2048");
//        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exit(0);
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        System.out.println("left");
                        int[][] t = new int[5][5];
                        Main.engine.CopyArray(map, t);
                        if (Arrays.deepEquals(t, Main.engine.left())) { break; }
                        Main.engine.setMap(Main.engine.left());
                        Main.engine.next();
                    }
                    case KeyEvent.VK_RIGHT -> {
                        System.out.println("right");
                        int[][] t = new int[5][5];
                        Main.engine.CopyArray(map, t);
                        if (Arrays.deepEquals(t, Main.engine.right())) { break; }
                        Main.engine.setMap(Main.engine.right());
                        Main.engine.next();
                    }
                    case KeyEvent.VK_DOWN -> {
                        System.out.println("down");
                        int[][] t = new int[5][5];
                        Main.engine.CopyArray(map, t);
                        if (Arrays.deepEquals(t, Main.engine.down())) { break; }
                        Main.engine.setMap(Main.engine.down());
                        Main.engine.next();
                    }
                    case KeyEvent.VK_UP -> {
                        System.out.println("up");
                        int[][] t = new int[5][5];
                        Main.engine.CopyArray(map, t);
                        if (Arrays.deepEquals(t, Main.engine.up())) { break; }
                        Main.engine.setMap(Main.engine.up());
                        Main.engine.next();
                    }
                }
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        // super.paint(g);

        g.setColor(BG_COLOR);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 42));
        int w2 = getWordWidth(new Font("Arial", Font.BOLD, 42), "2048");
        g.drawString("2048", (this.getWidth() - w2) / 3, (this.getHeight() - 4*BLOCK_SIZE - 3*BLOCK_MARGIN) / 2 - w2 / "2048".length());
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        w2 = getWordWidth(new Font("Arial", Font.BOLD, 24), "Score:"+String.valueOf(Main.engine.getScore()));
        g.drawString("Score:"+String.valueOf(Main.engine.getScore()),(this.getWidth() - w2) / 3 * 2, (this.getHeight() - 4*BLOCK_SIZE - 3*BLOCK_MARGIN) / 2 - w2 / ("Score:"+String.valueOf(Main.engine.getScore())).length());
//        getFontMetrics(new Font("Arial", Font.BOLD, 42));
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                Font font = new Font("Arial", Font.BOLD, map[i][j] < 100 ? 36 : map[i][j] < 1000 ? 32 : 24);
                final FontMetrics fm = getFontMetrics(font);
                g.setFont(font);
                final int w = fm.stringWidth(String.valueOf(map[i][j]));
                final int h = -(int) fm.getLineMetrics(String.valueOf(map[i][j]), g).getBaselineOffsets()[2];
//                int x = (j * BLOCK_SIZE + (j - 1) * BLOCK_MARGIN) + (this.getWidth()  - (j * BLOCK_SIZE + (j - 1) * BLOCK_MARGIN)) / 4;
//                int y = (i * BLOCK_SIZE + (i - 1) * BLOCK_MARGIN) + (this.getHeight() - (i * BLOCK_SIZE + (i - 1) * BLOCK_MARGIN)) / 4;
                int x = ((j - 1) * (BLOCK_SIZE + BLOCK_MARGIN)) + (this.getWidth()  - 4*BLOCK_SIZE - 3*BLOCK_MARGIN) / 2;
                int y = ((i - 1) * (BLOCK_SIZE + BLOCK_MARGIN)) + (this.getHeight() - 4*BLOCK_SIZE - 3*BLOCK_MARGIN) / 2;
                g.setColor(getBackground(map[i][j]));
                g.fillRoundRect(x, y, BLOCK_SIZE, BLOCK_SIZE, 14, 14);
                if (map[i][j] != 0) {
                    g.setColor(Color.GRAY);
                    g.drawString(String.valueOf(map[i][j]), x + (BLOCK_SIZE - w) / 2, y + BLOCK_SIZE - (BLOCK_SIZE - h) / 2 - 2);
                }
            }
        }
        if(Main.engine.WinorLose() == 1) {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.RED);
            final int w = getFontMetrics(new Font("Arial", Font.BOLD, 48)).stringWidth("YOU WIN!!!");
            final int h = -(int) getFontMetrics(new Font("Arial", Font.BOLD, 48)).getLineMetrics("YOU WIN!!!", g).getBaselineOffsets()[2];
            g.drawString("YOU WIN!!!", (this.getWidth() - w) / 2, this.getHeight() - (this.getHeight() - h) / 2 - 2);
        } else if(Main.engine.WinorLose() == 2) {
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.RED);
            final int w = getFontMetrics(new Font("Arial", Font.BOLD, 48)).stringWidth("YOU LOSS!!!");
            final int h = -(int) getFontMetrics(new Font("Arial", Font.BOLD, 48)).getLineMetrics("YOU LOSS!!!", g).getBaselineOffsets()[2];
            g.drawString("YOU LOSS!!!", (this.getWidth() - w) / 2, this.getHeight() - (this.getHeight() - h) / 2 - 2);
        }
    }

    public Color getBackground(int value) {
        return switch (value) {
            case 2 -> new Color(0xeee4da);
            case 4 -> new Color(0xede0c8);
            case 8 -> new Color(0xf2b179);
            case 16 -> new Color(0xf59563);
            case 32 -> new Color(0xf67c5f);
            case 64 -> new Color(0xf65e3b);
            case 128 -> new Color(0xedcf72);
            case 256 -> new Color(0xedcc61);
            case 512 -> new Color(0xedc850);
            case 1024 -> new Color(0xedc53f);
            case 2048 -> new Color(0xedc22e);
            default -> new Color(0xcdc1b4);
        };
    }

    public int getWordWidth(Font font, String content) {
        return getFontMetrics(font).stringWidth(content);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
