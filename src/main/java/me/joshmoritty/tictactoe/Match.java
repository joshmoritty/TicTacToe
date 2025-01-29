package me.joshmoritty.tictactoe;

public class Match {

    public final Player[] players;
    public final Board board;
    public final MatchSettings settings;
    private int movesCount = 0;
    private MatchResult result = null;

    public Match(Player[] players, MatchSettings settings) {
        this.players = players;
        this.settings = settings;
        board = new Board(settings.size, settings.winCount);
    }

    public void placeMark(Coordinate c) {
        if (result != null) return;

        Player player = getCurrentPlayer();

        movesCount++;
        board.placeMark(player, c);

        Player winner = board.getWinnerAtPosition(c);
        if (winner != null) {
            result = new MatchResult(MatchResult.Type.WIN, players, winner, movesCount);
        } else if (noMovesLeft()) {
            result = new MatchResult(MatchResult.Type.DRAW, players, null, movesCount);
        }
    }

    public Player getCurrentPlayer() {
        return players[getCurrentPlayerIndex()];
    }

    public MatchResult getResult() {
        return result;
    }

    public boolean isOngoing() {
        return result == null;
    }

    private int getCurrentPlayerIndex() {
        return movesCount % players.length;
    }

    private boolean noMovesLeft() {
        return movesCount >= settings.size * settings.size;
    }
}
