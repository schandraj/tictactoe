public class TextConsole {
    private TextConsole() {}

    public static void beginning() {
        System.out.println("Tic Tac Toe with 2 player.");
        System.out.println("Start the game? Y/N");
    }

    public static int start(String string) {
        if (string.equalsIgnoreCase("Y")) {
            System.out.println("Game Start");
            return 1;
        } else {
            System.out.println("Exit");
            return 0;
        }
    }

    public static void size(int size) {
        switch (size) {
            case 1 :
                System.out.println("Enter size the board! (3-9)");
                break;
            case 2 :
                System.out.println("Board Size must between 3 and 9");
                break;
            case 3 :
                System.out.println("Input must be Integer");
                break;
            default:
                System.out.println("");
        }
    }

    public static void placement(int index, String playerName, int size) {
        switch (index) {
            case 1 :
                System.out.println(playerName + " move, Enter the number of cell you want to take! (from 1 to " + size*size + ")");
                break;
            case 2 :
                System.out.println("Input must be Integer!");
                break;
            case 3 :
                System.out.println("Input out of range or position has been taken already");
                break;
            default:
                System.out.println("Index Placement 1 tp 3");
        }
    }

    public static void winner(String name) {
        System.out.println("The winner is " + name);
    }

    public static void tie() {
        System.out.println("Match is Tie");
    }
}
