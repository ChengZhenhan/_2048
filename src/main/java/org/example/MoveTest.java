package org.example;

import javax.swing.*;
import java.awt.*;

public class MoveTest extends JFrame {
//    JFrame jFrame = new JFrame();
	int x,y = 0;

	MoveTest() {
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("test");
        this.setVisible(true);
    }

	public void paint(Graphics g) {
//        super.paint(g);
		g.setColor(Color.WHITE);
//		g.draw3DRect(x-1,y-1,10,10,true);
		g.drawRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.BLUE);
		g.drawRect(x,y,10,10);
        x+=1;
        y+=1;
//        this.repaint();
    }
}