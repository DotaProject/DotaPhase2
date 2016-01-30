package logic.common;

/**
 * Created by nasim on 16/01/24.
 */
public class Forces extends GameComponents {
    protected int range;
    protected int reloadTime;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }
}
