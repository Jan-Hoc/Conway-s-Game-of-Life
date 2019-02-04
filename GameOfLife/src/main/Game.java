package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	private int width;
	private int height;
	private int scl;
	
	private int fps = 10;
	private boolean running = false;
	private static Thread thread;
	private BufferStrategy bs;
	
	private Display display;
	private String title = "Game of Life";
	
	private Graphics g;
	
	private World world;
	
	
	public Game(int width, int height, int scl) {
		this.width = width;
		this.height = height;
		this.scl = scl;
		this.world = new World(width, height, scl);
	}
	
	private void init() {
		display = new Display(width * scl, height * scl, scl, title);
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {

		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		world.update();
		
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width * scl, height * scl);
		
		world.show(g);
		
		bs.show();
		g.dispose();
	}

	public void run() {
		init();
		
		while(running) {
			long startTime = System.nanoTime();

			tick();
			render();

			long URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			long waitTime = 1000 / fps - URDTimeMillis;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}
		}

		stop();
		
	}
	

}
