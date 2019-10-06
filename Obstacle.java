package HomeWork2;

import java.awt.*;

/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */
public class Obstacle {
    public int[][] coordinate = new int[1][2];
    private boolean isThere;
    private Color color = Color.decode("#FFFFFF");

    public Obstacle(){
        this.isThere = true;
    }

    public boolean isThere() {
        return isThere;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

    public Color getColor() {
        return color;
    }
}

