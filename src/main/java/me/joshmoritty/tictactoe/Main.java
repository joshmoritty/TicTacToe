package me.joshmoritty.tictactoe;

public class Main {
	
	public static void main(String[] args) {
		TerminalUI ui = new TerminalUI();
		Game game = new Game(ui);

		game.start();
	}
}
