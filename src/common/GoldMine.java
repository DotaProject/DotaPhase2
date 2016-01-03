package common;

public class GoldMine extends GameComponents {

    private boolean hasOwner = false;

    //constructor
    public GoldMine(Cell cell){
        this.isAlive = true;
        this.cells.add(cell);
    }


    //getter and setter
    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }
}
