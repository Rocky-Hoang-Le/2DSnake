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

import javax.swing.JPanel;
import javax.swing.Timer;
import static com.phantomxstudios.snake.Constant.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	// Array to hold snake x and y coordinates
	private ArrayList<Integer> snakeXLength = new ArrayList<>();
	private ArrayList<Integer> snakeYLength = new ArrayList<>();
	// Array to hold point x and y coordinates
	private ArrayList<Integer> enemyXPos = new ArrayList<>();
	private ArrayList<Integer> enemyYPos = new ArrayList<>();

	// Checks to see if snake is going in left right up or down
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private boolean horDir = false;
	private boolean verDir = false;

	// Set how long snake starts of at;
	private int lengthOfSnake = 3;

	// Timer to set how fast snake moves and accepts inputs
	private Timer timer;
	private int delay = 100;

	// Move counter to see if snake is on first move
	private int moves = 0;

	// Random spawn position for point
	private Random random = new Random();
	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);

	// Score variable
	private int score = 0;

	public Gameplay() { // Intialize gameplay window and set all possible spawn points for point.
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		for (int i = 0; i < 845 / 25; i++) {
			enemyXPos.add(25 + (25 * i));
		}
		for (int i = 0; i < 625 / 25; i++) {
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
		// System.out.println("X = " + snakeXLength.get(0) + " Y = " +
		// snakeYLength.get(0));

		// Draw the title image
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

		rightface.paintIcon(this, g, snakeXLength.get(0), snakeYLength.get(0));

		for (int i = 0; i < lengthOfSnake; i++) {
			if (i == 0 && right) {
				rightface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && left) {
				leftface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && up) {
				upface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
			if (i == 0 && down) {
				downface.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}

			if (i != 0) {
				snakeBody.paintIcon(this, g, snakeXLength.get(i), snakeYLength.get(i));
			}
		}


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

		for (int i = 1; i < lengthOfSnake; i++) {
			if (snakeXLength.get(i).equals(snakeXLength.get(0)) && snakeYLength.get(i).equals(snakeYLength.get(0))) {
				right = false;
				left = false;
				up = false;
				down = false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to Restart", 350, 340);
			}
		}

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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			verDir = false;
			horDir = true;
			right = true;
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			verDir = false;
			horDir = true;
			left = true;
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			verDir = true;
			horDir = false;
			up = true;
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			verDir = true;
			horDir = false;
			down = true;
			move();
		}

	}
	
	private void move() {
		moves++;
		if (horDir) {
			if (!right)
				left = true;
			else {
				right = true;
				left = false;
			}
			
			up = down = false;
		}
		if (verDir) {
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
