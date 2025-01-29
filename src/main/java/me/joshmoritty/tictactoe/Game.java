package me.joshmoritty.tictactoe;

public class Game {

    private final TerminalUI ui;
    private Match currentMatch;
    private boolean running = true;
    boolean rematch = false;

    public Game(TerminalUI ui) {
        this.ui = ui;
    }

    public void start() {
        ui.displayGameStart();
        while (running) {
            currentMatch = setupMatch();

            ui.displayMatchStart();

            do {
                ui.displayBoard(currentMatch.board);

                Coordinate c = ui.pollPlaceCoordinate(
                        currentMatch.getCurrentPlayer(), currentMatch.board
                );
                currentMatch.placeMark(c);
            } while (currentMatch.isOngoing());

            ui.displayBoard(currentMatch.board);
            ui.displayResult(currentMatch.getResult());

            rematch = ui.pollRematch();
            if (!rematch) {
                running = ui.pollContinue();
            }
        }
    }

    private Match setupMatch() {
        MatchSettings settings;
        Player[] players;

        if (rematch) {
            players = currentMatch.players;
            settings = currentMatch.settings;
        } else {
            boolean useDefaultMatchSettings = ui.pollUseDefaultMatchSettings();
            int playerCount;

            if (useDefaultMatchSettings) {
                playerCount = MatchSettings.DEFAULT_PLAYER_COUNT;
                settings = new MatchSettings();
            } else {
                playerCount = ui.pollPlayerCount(2, MatchSettings.MAX_PLAYER_COUNT);
                int size = ui.pollSize(2, MatchSettings.MAX_SIZE);
                int winCount = ui.pollWinCount(2, size);
                settings = new MatchSettings(size, winCount);
            }

            players = new Player[playerCount];
            for (int i = 0; i < playerCount; i++) {
                String name = "Player " + (i + 1);
                players[i] = new Player(
                        name,
                        useDefaultMatchSettings
                                ? Player.DEFAULT_PLAYER_SYMBOLS[i]
                                : ui.pollPlayerMark(name)
                );
            }
        }

        return new Match(players, settings);
    }
}
