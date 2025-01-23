package me.joshmoritty.tictactoe;

public class MatchSettings {

    public static final int DEFAULT_PLAYER_COUNT = 2;
    public static final int MAX_PLAYER_COUNT = 10;
    public static final int DEFAULT_SIZE = 3;
    public static final int MAX_SIZE = 50;
    public static final int DEFAULT_WIN_COUNT = 3;

    public final int size;
    public final int winCount;

    public MatchSettings() {
        this.size = DEFAULT_SIZE;
        this.winCount = DEFAULT_WIN_COUNT;
    }

    public MatchSettings(int size, int winCount) {
        this.size = size;
        this.winCount = winCount;
    }
}
