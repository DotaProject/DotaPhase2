package common;

public class MovingForces extends Forces {
    protected int row;
    protected int column;
    protected double value;
    protected int damage;
    protected int speed;
    private int flag = 0;
    //0 = morde
    //1 = zende
    //2 = zende o move
    //3 = zende o attack

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}