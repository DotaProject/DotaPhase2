package common;

public class MovingForces extends Forces {
    protected int row;
    protected int column;
    protected double value;
    protected int damage;
    protected int speed;

    public double getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
