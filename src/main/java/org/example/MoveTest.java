package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveTest extends JFrame implements ActionListener {
//    JFrame jFrame = new JFrame();
	int x,y = 0;
	MoveTest() {
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("test");
        this.setVisible(true);
        Timer timer = new Timer(50, this);
        timer.start();
    }

	public void paint(Graphics g) {
        super.paint(g);
		g.setColor(Color.WHITE);
//		g.draw3DRect(x-1,y-1,10,10,true);
		g.drawRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.BLUE);
		g.drawRect(x,y,10,10);
//        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x+=1;
        y+=1;
        repaint();
    }
}
