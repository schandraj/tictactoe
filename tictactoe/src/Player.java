import java.util.Scanner;

public class Player {
    private String name;
    private Scanner scanner;
    private int rowIndex;
    private int colIndex;

    private Player() {}

    public Player(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
        this.rowIndex = -1;
        this.colIndex = -1;
    }

    public String getName() {
        return name;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void move(Board board, Sign sign) {
        int position;

        while (true) {
            TextConsole.placement(1, this.name, board.getSize());
            board.print();

            if (scanner.hasNextInt()) {
                position = scanner.nextInt();

                if (!board.isValid(position)) {
                    //position is not available
                    TextConsole.placement(3, this.name, board.getSize());
                    continue;
                } else {
                    //valid input
                    break;
                }
            } else {
                //input is not an integer
                scanner.next();
                TextConsole.placement(2, this.name, board.getSize());
            }
        }

        //place
        place(board, sign, position);
    }

    public void place(Board board, Sign sign, int position) {
        int count = 1, i = 0, j = 0;
        boolean flag = true;

        for (i = 0; i < board.getBoardCell().length; ++i) {
            for (j = 0; j < board.getBoardCell()[i].length; ++j) {
                if (count == position) {
                    board.getBoardCell()[i][j].setSign(sign);
                    flag = false; break;
                }
                count++;
            }

            if (!flag) {break;}
        }

        this.rowIndex = i;
        this.colIndex = j;
    }
}
