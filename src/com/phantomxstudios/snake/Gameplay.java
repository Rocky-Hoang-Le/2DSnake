package com.phantomxstudios.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private ArrayList<Integer> snakeXLength = new ArrayList<>();
	private ArrayList<Integer> snakeYLength = new ArrayList<>();
	
	private ArrayList<Integer> enemyXPos = new ArrayList<>();
	private ArrayList<Integer> enemyYPos = new ArrayList<>();

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rightface;
	private ImageIcon leftface;
	private ImageIcon upface;
	private ImageIcon downface;

	private int lengthOfSnake = 3;

	private Timer timer;
	private int delay = 100;

	private ImageIcon snakeBody;

	private int moves = 0;

	private ImageIcon titleImage;
	
	private ImageIcon enemy;
	
	private Random random = new Random();
	
	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);
	
	private int score = 0;

	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		for (int i = 0; i < 845/25; i++) {
			enemyXPos.add(25 + (25 * i));
		}
		for (int i = 0; i < 625/25; i++) {
			enemyYPos.add(75 + (25 * i));
		}
		
	}
	

	public void paint(Graphics g) {
		
		if (moves == 0) {
			snakeXLength.add(100);
			snakeXLength.add(75);
			snakeXLength.add(50);

			snakeYLength.add(100);
			snakeYLength.add(100);
			snakeYLength.add(100);

		}
		//System.out.println("X = " + snakeXLength.get(0) + " Y = " + snakeYLength.get(0));

		// Draw the title image
		titleImage = new ImageIcon("titleimage.png");
		titleImage.paintIcon(this, g, 21, 11);

		// Draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 841, 577);

		// Draw background
		g.setColor(Color.black);
		g.fillRect(25, 75, 840, 575);
		
		// Draw Score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);
		
		// Draw Length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " + lengthOfSnake, 780, 50);

		rightface = new ImageIcon("rightface.png");
		rightface.paintIcon(this, g, snakeXLength.get(0), snakeYLength.get(0));

		for (int i = 0; i < lengthOfSnake; i++) {
			if (i == 0 && right) {
				rightface = new ImageIcon("rightface.png");
				rightface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && left) {
				leftface = new ImageIcon("leftface.png");
				leftface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && up) {
				upface = new ImageIcon("upface.png");
				upface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && down) {
				downface = new ImageIcon("downface.png");
				downface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}

			if (i != 0) {
				snakeBody = new ImageIcon("body.png");
				snakeBody.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
		}
		
		enemy = new ImageIcon("enemy.png");
		
		if ((enemyXPos.get(xPos).equals(snakeXLength.get(0))) && (enemyYPos.get(yPos).equals(snakeYLength.get(0)))) {
			System.out.println("Im collected");
			score++;
			snakeXLength.add(0);
			snakeYLength.add(0);
			lengthOfSnake++;
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}
		
		enemy.paintIcon(this, g, enemyXPos.get(xPos), enemyYPos.get(yPos));
		System.out.println("Enemy X = " + enemyXPos.get(xPos) + " Enemy Y = " + enemyYPos.get(yPos));

		
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (right) {
			for (int i = lengthOfSnake - 2; i >= 0; i--) {
				snakeYLength.set(i + 1, snakeYLength.get(i));
			}
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				if (i == 0)
					snakeXLength.set(i, snakeXLength.get(i) + 25);
				else
					snakeXLength.set(i, snakeXLength.get(i - 1));
				if (snakeXLength.get(i) > 845)
					snakeXLength.set(i, 25);
			}
			repaint();
		}
		
		if (left) {
			for (int i = lengthOfSnake - 2; i >= 0; i--) {
				snakeYLength.set(i + 1, snakeYLength.get(i));
			}
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				if (i == 0)
					snakeXLength.set(i, snakeXLength.get(i) - 25);
				else
					snakeXLength.set(i, snakeXLength.get(i - 1));
				if (snakeXLength.get(i) < 25)
					snakeXLength.set(i, 845);
			}
			repaint();
		}
		
		if (up) {
			for (int i = lengthOfSnake - 2; i >= 0; i--) {
				snakeXLength.set(i + 1, snakeXLength.get(i));
			}
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				if (i == 0)
					snakeYLength.set(i, snakeYLength.get(i) - 25);
				else
					snakeYLength.set(i, snakeYLength.get(i - 1));
				if (snakeYLength.get(i) < 75)
					snakeYLength.set(i, 625);
			}
			repaint();
		}
		
		if (down) {
			for (int i = lengthOfSnake - 2; i >= 0; i--) {
				snakeXLength.set(i + 1, snakeXLength.get(i));
			}
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				if (i == 0)
					snakeYLength.set(i, snakeYLength.get(i) + 25);
				else
					snakeYLength.set(i, snakeYLength.get(i - 1));
				if (snakeYLength.get(i) > 625)
					snakeYLength.set(i, 75);
			}
			repaint();
		}
		
		
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("Right Pressed");
			moves++;
			right = true;
			if (!left)
				right = true;
			else {
				right = false;
				left = true;
			}

			up = down = false;
		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right)
				left = true;
			else {
				right = true;
				left = false;
			}

			up = down = false;
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down)
				up = true;
			else {
				up = false;
				down = true;
			}

			left = right = false;
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up)
				down = true;
			else {
				down = false;
				up = true;
			}

			left = right = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
