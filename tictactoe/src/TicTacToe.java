import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static Scanner scanner;
    private static String nameOfPlayer1;
    private static String nameOfPlayer2;
    private static boolean shouldGenerateRandomID;
    private static int boardSize;
    private static int rowPlacement; // Row index of a single placement
    private static int colPlacement; // Column index of a single placement
    private static int numberOfPlacements; // Total number of placements during the game

    private Player player1;
    private Player player2;
    private Board board;

    private int[][] rows;
    private int[][] columns;
    private int[][] diagonals;

    public TicTacToe() {
        scanner = new Scanner(System.in);
        nameOfPlayer1 = "Player 1";
        nameOfPlayer2 = "Player 2";
        shouldGenerateRandomID = false;
        boardSize = -1;
        rowPlacement = -1;
        colPlacement = -1;
        numberOfPlacements = 0;

        this.prepare();
        this.getBoardSize();

        this.player1 = new Player(nameOfPlayer1);
        this.player2 = new Player(nameOfPlayer2);
        this.board = new Board(boardSize);

        this.rows = new int[3][boardSize];
        this.columns = new int[3][boardSize];
        this.diagonals = new int[3][2];
    }

    public void main() {
        int playerID = 0, winnerID = 0;
        shouldGenerateRandomID = true;

        while (true) {
            // Only when we start a new game should we decide which player to begin
            if (shouldGenerateRandomID) {
                playerID = this.idGenerator();
                shouldGenerateRandomID = false;
            }

            playerID = this.playerTurn(playerID); // Players take turn to play
            ++numberOfPlacements;
            winnerID = this.checkWinner(rowPlacement, colPlacement, playerID);

            if (winnerID == 1) {
                TextConsole.winner(player2.getName());
            } else if (winnerID == 2) {
                TextConsole.winner(player1.getName());
            } else if ((numberOfPlacements == boardSize * boardSize) && (winnerID == -1)) {
                TextConsole.tie();
            } else {
                continue; // IMPORTANT! If no winner generated, skip the code below.
            }

            this.board.print(); // Update and print the current game board
            this.exit(); // After a round has finished, decide to play again or exit
        }
    }

    private void prepare() {
        TextConsole.beginning();
        final String DECISION = scanner.nextLine();
        int code = TextConsole.start(DECISION);
        if (code == 0) {System.exit(0);}
    }

    private void getBoardSize() {
        while (true) {
            TextConsole.size(1);

            if (scanner.hasNextInt()) {
                boardSize = scanner.nextInt();

                if (boardSize < 3 || boardSize > 9) {
                    // The range of board size should be [3, 10]
                    TextConsole.size(2);
                    continue;
                } else {
                    break;
                }
            } else {
                // Input is invalid (i.e. not an integer)
                scanner.next(); // IMPORTANT! Free the buffer!
                TextConsole.size(3);
            }
        }
    }

    private int idGenerator() {
        Random random = new Random();
        int id;
        id = random.nextInt(2) + 1;

        return id;
    }

    private int playerTurn(int playerID) {
        if (playerID == 1) {
            Sign sign = new Sign('X');
            this.player1.move(this.board, sign);
            rowPlacement = this.player1.getRowIndex();
            colPlacement = this.player1.getColIndex();
            playerID = 2; // Player should take turns to play
        } else {
            Sign sign = new Sign('O');
            this.player2.move(this.board, sign);
            rowPlacement = this.player2.getRowIndex();
            colPlacement = this.player2.getColIndex();
            playerID = 1; // Player should take turns to play
        }

        return playerID;
    }

    private int checkWinner(int row, int col, int playerID) {
        if (++this.rows[playerID][row] == boardSize) {
            return playerID;
        }
        if (++this.columns[playerID][col] == boardSize) {
            return playerID;
        }
        if (row == col && ++this.diagonals[playerID][0] == boardSize) {
            return playerID;
        }
        if ((row + col == boardSize - 1) && ++this.diagonals[playerID][1] == boardSize) {
            return playerID;
        }

        return -1;
    }

    private void exit() {
        TextConsole.start("Exit");

        // Close all the Scanners
        scanner.close();
        this.player1.getScanner().close();
        this.player2.getScanner().close();

        System.exit(0);

    }
}
