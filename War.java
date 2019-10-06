package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */


import java.util.Random;

public class War {

    /**
     * If titans and humans get same tile, they will be war. And this func for war and blood!*/
    public static int conflict(Titan titan, Human human) {
        if (human.getScore() < titan.getScore()) {
            if (titan.getLevel() == Titan.Experience.HIGH) {
                human.setDead(true);
                human.setLevel(Human.Experience.DEAD);
                return 0;
            } else if (titan.getLevel() == Titan.Experience.MEDIUM) {
                human.setScore(human.getScore() / 2);

                if (checkUserHealth(human)) {
                    return 0;
                } else {
                    return 1;
                }

            } else if (titan.getLevel() == Titan.Experience.LOW) {
                human.setScore(human.getScore() - 1);
                if (checkUserHealth(human)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        } else if (human.getScore() > titan.getScore() && titan.getLevel() != Titan.Experience.HIGH) {
            titan.setScore(0);
            titan.setDead(true);
            titan.setLevel(Titan.Experience.DEAD);
            return 2;
        } else if (human.getScore() > titan.getScore()) {
            titan.setScore(titan.getScore() / 2);
            return 3;
        }
        return 99;
    }

    /**
     * Kod kısaltma için kullandım aslında pek bir işlevselliği yok.
     * */
    public static boolean checkUserHealth(Human human) {
        if (human.getScore() == 0) {
            human.setLevel(Human.Experience.DEAD);
            return true;
        }
        return false;
    }

    /**
     * Give me random directions for move
     * After procces we will check directions*/
    public static int move() {

        /*
         * 1 -> Up
         * 2 -> Down
         * 3 -> Rigth
         * 4 -> Left*/
        int[] directions = {1, 2, 3, 4};

        Random rand = new Random();

        int direction = directions[rand.nextInt(4)];

        /*switch (direction){
            case 1: return "up";
            case 2: return "down";
            case 3: return "rigth";
            case 4: return "left";
            default: return "none";
        }*/
        return direction;
    }


}
