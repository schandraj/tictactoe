public class Sign {
    private char symbol;

    private Sign() {}

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Sign(char symbol) {
        this.setSymbol(symbol);
    }
}
