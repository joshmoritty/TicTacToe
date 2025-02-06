package me.joshmoritty.tictactoe;

import java.util.Scanner;

public class TerminalUI {

    private final Scanner s = new Scanner(System.in);

    public int pollSize(int min, int max) {
        while (true) {
            System.out.print("Board size [" + min + "-" + max + "] > ");
            String line = s.nextLine();
            try {
                int size = Integer.parseInt(line.trim());

                if (size >= min && size <= max) {
                    return size;
                } else {
                    System.out.println("Please input an integer " + min + "-" + max + "!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Please input an integer " + min + "-" + max + "!");
            }
        }
    }

    public int pollWinCount(int min, int max) {
        while (true) {
            System.out.print("How many in a row to win? [" + min + "-" + max + "] > ");
            String line = s.nextLine();
            try {
                int winCount = Integer.parseInt(line.trim());

                if (winCount >= min && winCount <= max) {
                    return winCount;
                } else {
                    System.out.println("Please input an integer " + min + "-" + max + "!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Please input an integer " + min + "-" + max + "!");
            }
        }
    }

    public int pollPlayerCount(int min, int max) {
        while (true) {
            System.out.print("How many players? [" + min + "-" + max + "] > ");
            String line = s.nextLine();
            try {
                int playerCount = Integer.parseInt(line.trim());

                if (playerCount >= min && playerCount <= max) {
                    return playerCount;
                } else {
                    System.out.println("Please input an integer " + min + "-" + max + "!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Please input an integer " + min + "-" + max + "!");
            }
        }
    }

    public char pollPlayerMark(String playerName) {
        while (true) {
            System.out.print(playerName + "'s Mark [1 letter] > ");
            String line = s.nextLine();
            if (line.length() == 1) {
                return line.charAt(0);
            } else {
                System.out.println("Please input 1 letter!");
            }
        }
    }

    public boolean pollRematch() {
        while (true) {
            System.out.print("Want a rematch? [y/n] > ");
            String line = s.nextLine();
            if (line.equalsIgnoreCase("y")) {
                return true;
            } else if (line.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please input y/n!");
            }
        }
    }

    public boolean pollContinue() {
        while (true) {
            System.out.print("Continue playing? [y/n] > ");
            String line = s.nextLine();
            if (line.equalsIgnoreCase("y")) {
                return true;
            } else if (line.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please input y/n!");
            }
        }
    }

    public boolean pollUseDefaultMatchSettings() {
        while (true) {
            System.out.print("Play classic (3x3, 2 players)? [y/n] > ");
            String line = s.nextLine();
            if (line.equalsIgnoreCase("y")) {
                return true;
            } else if (line.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please input y/n!");
            }
        }
    }

    public Coordinate pollPlaceCoordinate(Player player, Board board) {
        while (true) {
            System.out.print(player.name + "'s move [x y] > ");
            String line = s.nextLine();
            String[] tokens = line.trim().split(" ");

            if (tokens.length != 2) {
                System.out.println("Please input two integers!");
                continue;
            }
            try {
                Coordinate c = new Coordinate(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1])
                );

                if (board.canPlaceMark(c)) {
                    System.out.println();
                    return c;
                } else {
                    System.out.println("You cannot place a mark there!");
                }
            } catch (NumberFormatException ignore) { }
        }
    }

    public void displayGameStart() {
        System.out.println("TIC TAC TOE");
        System.out.println("===========\n");
    }

    public void displayMatchStart() {
        System.out.println("\nMATCH BEGIN");
        System.out.println("===========\n");
    }

    public void displayBoard(Board board) {
        System.out.print("+");
        for (int i = 0; i < board.size; i++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < board.size; i++) {
            System.out.print("|");
            for (int j = 0; j < board.size; j++) {
                Coordinate c = new Coordinate(j, i);
                Mark mark = board.getMarkAt(c);
                System.out.print(" "
                        + ((mark == null) ? " " : mark.getPlayer().symbol)
                        + " |"
                );
            }
            System.out.print("\n+");
            for (int j = 0; j < board.size; j++) {
                System.out.print("---+");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayResult(MatchResult result) {
        if (result.type == MatchResult.Type.WIN) {
            System.out.println(result.winner.name + " WON THE MATCH!");
            System.out.println("Moves: " + result.getPlayerMoveCount(result.winner));
        } else {
            System.out.println("DRAW!");
            System.out.println("Total moves: " + result.movesCount);
        }
        System.out.println();
    }
}
