public class Board {
    private int size;
    private Cell[][] boardCell;

    private Board() {}

    public void setSize(int size) {
        this.size = size;
    }

    public void setBoardCell(int size) {
        this.boardCell = new Cell[size][size];

        //empty board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < boardCell[0].length; j++) {
                boardCell[i][j] = new Cell(new Sign(' '));
            }
        }
    }

    public Board(int size) {
        this.setSize(size);
        this.setBoardCell(size);
    }

    public int getSize() {
        return this.size;
    }

    public Cell[][] getBoardCell() {
        return this.boardCell;
    }

    //print board
    public void print() {
        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < this.size; i++) {
            stringBuilder.append("+---");
        }
        stringBuilder.append("+");

        for (Cell[] row : this.boardCell) {
            System.out.println(stringBuilder);

            for (Cell cell : row) {
                System.out.print("| " + cell.getSign() + " ");
            }
            System.out.println("|");
        }
        System.out.println(stringBuilder);
        System.out.println();
    }

    //check placement
    public boolean isValid(int position) {
        if (position < 1 || position > (this.size * this.size)) {
            return false;
        }

        int count = 1, i = 0, j = 0;
        boolean flag = true;

        for (i = 0; i < this.boardCell.length; i++) {
            for (j = 0; j < boardCell[0].length; j++) {
                if (count == position) {
                    flag = false; break;
                }
                count++;
            }
            if (!flag) {break;}
        }

        if (boardCell[i][j].getSign() != ' ') {
            return false;
        }

        return true;
    }
}
