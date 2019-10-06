package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Human {
    enum Experience {
        LOW,
        MEDIUM,
        HIGH,
        DEAD
    }

    Color color;
    int size;

    private Experience level;
    private int score = 0;
    private boolean isDead;
    public int turnAfterDead;
    public int[][] coordiante = new int[1][2];
    public List<int[][]> coordinates = new ArrayList<>();

    public Human() {

    }

    public Human(int score, boolean isDead) {
        this.score = score;
        this.isDead = isDead;
    }

    public Experience getLevel() {
        return level;
    }

    public void setLevel(Experience level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 20) {
            setLevel(Experience.LOW);
            color = Color.decode("#000080");
            size = 2;
        } else if (score > 20 && score < 51) {
            setLevel(Experience.MEDIUM);
            color = Color.decode("#00FFFF");
            size = 2;

        } else if (score == 0) {
            setLevel(Experience.DEAD);
            color = Color.decode("#2A292A");
        } else {
            setLevel(Experience.HIGH);
            color = Color.decode("#FFFF00");
            size = 4;
        }
        this.score = score;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}
