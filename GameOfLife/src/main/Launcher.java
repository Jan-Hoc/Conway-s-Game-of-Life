package main;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {
	private static int width;
	private static int height;
	private static int scl = 12;
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) (screenSize.getWidth() / scl);
		height = (int) ((screenSize.getHeight() / scl) - 3);
		
		
		Game game = new Game(width, height, scl);
		game.start();

	}
}
