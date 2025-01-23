package me.joshmoritty.tictactoe;

public class Player {

    public static final char[] DEFAULT_PLAYER_SYMBOLS = {'O', 'X'};

    public final String name;
    public final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
