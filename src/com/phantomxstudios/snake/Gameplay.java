package com.phantomxstudios.snake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel {
	private ImageIcon titleImage;
	
	public Gameplay() {
		
	}
	
	public void paint(Graphics g) {
		
		
		// Draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 21, 11);
		
		// Draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 841, 577);
		
		// Draw background
		g.setColor(Color.black);
		g.fillRect(25, 75, 840, 575);
		
	}

}
