public class Cell {
    private Sign sign;

    private Cell() {}

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign.getSymbol();
    }

    public Cell(Sign sign){
        this.setSign(sign);
    }
}
