package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */


import java.awt.*;
import java.util.Random;

public class Titan {
    enum Experience {
        LOW,
        MEDIUM,
        HIGH,
        DEAD
    }

    private Experience Level;
    private int score;
    public int[][] coordinate = new int[1][2];
    private boolean isDead;
    int diameter;
    Color color;


    public Titan(int score) {
        setScore(score);

    }

    public Titan() {
        int[] health = {21, 19, 51, 49, 99, 101};
        Random rand = new Random();
        setScore(health[rand.nextInt(health.length - 1)]);
    }

    public Experience getLevel() {
        return Level;
    }

    public void setLevel(Experience level) {
        Level = level;
        if (level == Experience.LOW) {
            this.score = Config.initialScoreOf3mTitan;
        } else if (level == Experience.MEDIUM) {
            this.score = Config.initialScoreOf5mTitan;
        } else if (level == Experience.HIGH) {
            this.score = Config.initialScoreOf15mTitan;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 21) {
            setLevel(Experience.LOW);
            color = Color.decode("#FF00FF");
            diameter = 2;
        } else if (score > 22 && score < 51) {
            setLevel(Experience.MEDIUM);
            color = Color.decode("#800000");
            diameter = 2;
        } else {
            setLevel(Experience.HIGH);
            color = Color.decode("#008000");
            diameter = 4;
        }
        this.score = score;
    }

    public int getDiameter() {
        return diameter;
    }

    public Color getColor() {
        return color;
    }

    public int[][] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[][] coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
