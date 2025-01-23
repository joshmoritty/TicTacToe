package me.joshmoritty.tictactoe;

public class Board {

	public final int size;
	public final int winCount;

	private final Mark[][] data;
	
	public Board(int size, int winCount) {
		this.size = size;
		this.winCount = winCount;
		
		data = new Mark[size][size];
	}

	public boolean canPlaceMark(Coordinate c) {
		return c.x >= 0
				&& c.x < size
				&& c.y >= 0
				&& c.y < size
				&& data[c.y][c.x] == null;
	}
	
	public void placeMark(Player player, Coordinate c) {
		if (!canPlaceMark(c)) return;

		Mark mark = new Mark(player);
		placeMark(mark, c);
	}
	
	private void placeMark(Mark mark, Coordinate c) {
		data[c.y][c.x] = mark;
	}
	
	public Player getWinnerAtPosition(Coordinate c) {
		int maxDist = size - 1;
		int lineCount = 0;
		Player player = data[c.y][c.x].getPlayer();

		int[][] directionDeltas = {
				{1, 0}, // Vertical
				{0, 1}, // Horizontal
				{1, 1}, // Diagonal Down
				{-1, 1} // Diagonal Up
		};
		int direction = 0;

		while (lineCount < winCount && direction < 4) {
			lineCount = 0;
			int i = clamp(c.y - maxDist * directionDeltas[direction][0]);
			int j = clamp(c.x - maxDist * directionDeltas[direction][1]);
			int max_i = clamp(c.y + maxDist * directionDeltas[direction][0]);
			int max_j = clamp(c.x + maxDist * directionDeltas[direction][1]);

			while (i <= max_i && j <= max_j) {
				if (data[i][j] != null && data[i][j].getPlayer().equals(player)) {
					lineCount++;
					if (lineCount == winCount) {
						break;
					}
				} else {
					lineCount = 0;
				}
				i += directionDeltas[direction][0];
				j += directionDeltas[direction][1];
			}
			direction++;
		}

		if (lineCount < winCount) return null;

		return player;
	}

	public Mark getMarkAt(Coordinate c) {
		return data[c.y][c.x];
	}
	
	private int clamp(int n) {
		return Math.max(0, Math.min(n, size - 1));
	}
}
