package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class World {
	private int scl;
	private int[][] pieces;
	private int[][] oldpieces;

	public World(int width, int height, int scl) {
		this.scl = scl;

		this.pieces = new int[width + 2][height + 2];

		for (int i = 1; i < pieces.length - 1; i++) {

			for (int j = 1; j < pieces[0].length - 1; j++) {

				pieces[i][j] = ThreadLocalRandom.current().nextInt(0, 6);
				if (pieces[i][j] == 0) {
					pieces[i][j] = 1;
				}

				else {
					pieces[i][j] = 0;
				}

			}
		}

	}

	public void update() {
		oldpieces = deepcopy(pieces);

		for (int i = 1; i < pieces.length - 1; i++) {

			for (int j = 1; j < pieces[0].length - 1; j++) {
				if (pieces[i][j] == 0) {

					if (countNeighbors(i, j) == 3) {
						pieces[i][j] = 1;
					}
				} else {
					if (countNeighbors(i, j) < 2 || countNeighbors(i, j) > 3) {
						pieces[i][j] = 0;
					}
				}
			}
		}

	}

	public void show(Graphics g) {
		for (int i = 1; i < pieces.length - 1; i++) {

			for (int j = 1; j < pieces[0].length - 1; j++) {

				if (pieces[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillRect((i - 1) * scl, (j - 1) * scl, scl, scl);

				}
			}
		}

	}

	private int countNeighbors(int x, int y) {
		int counter = 0;

		for (int i = -1; i < 2; i++) {

			for (int j = -1; j < 2; j++) {

				counter += oldpieces[x + i][y + j];
			}
		}
		if (pieces[x][y] == 1) {
			counter -= 1;
		}
		return counter;
	}

	private int[][] deepcopy(int[][] original) {
		if (original == null) {
			return null;
		}

		final int[][] result = new int[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}
		return result;
	}
}
