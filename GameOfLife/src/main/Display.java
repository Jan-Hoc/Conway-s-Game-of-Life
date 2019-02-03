package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display implements KeyListener {
	private int width;
	private int height;
	private int scl;
	private String title;

	public static JFrame frame;
	private Canvas canvas;

	public Display(int width, int height, int scl, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.scl = scl;

		createDisplay();

	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLocation(0, 0);
		frame.addKeyListener(this);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setBackground(Color.white);

		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();

	}

	public void keyPressed(KeyEvent e) {
		frame.setVisible(false);
		Game game = new Game(width / scl, height / scl, scl);
		game.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Canvas getCanvas() {
		return canvas;
	}

}
