package me.joshmoritty.tictactoe;

public class MatchResult {

	public enum Type {
		WIN,
		DRAW
	}

	public final Type type;
	public final Player[] players;
	public final Player winner;
	public final int movesCount;
	
	public MatchResult(Type type, Player[] players, Player winner, int movesCount) {
		this.type = type;
		this.players = players;
		this.winner = winner;
		this.movesCount = movesCount;
	}

	public int getPlayerMoveCount(Player player) {
		int index;
		for (index = 0; index < players.length; index++) {
			if (players[index] == player) break;
		}
		if (index == players.length) return 0;

		return movesCount / players.length
				+ (movesCount % players.length > index ? 1 : 0);
	}
}
