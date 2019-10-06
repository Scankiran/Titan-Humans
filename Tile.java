package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */

import java.util.ArrayList;
import java.util.List;

public class Tile {


    public List<Human> humans = new ArrayList<>();
    public List<Titan> titans = new ArrayList<>();
    public List<Obstacle> obstacle = new ArrayList<>();
    public List<Human> deadHumans = new ArrayList<>();

    int humansScore = 0;
    int titansScore = 0;

    boolean isInitial = true;

    private boolean isObstacle;
    private int user = humans.size();
    private int deadUserScore;


    public int[][] coordinate = new int[1][2];

    public Tile(int[][] coordinate) {
        this.coordinate = coordinate;
    }

    int isFirst = 0;
    int round = 0;


    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }



    /**
     * On initial round, if its obstacle, all titans and humans will be die.
     * Other rounds check human and titans list checking. If conditions true the are some procces.
     * */
    public void whoAreYou() {

        int humanCounter = humans.size();
        int titanCounter = titans.size();
        int obstacleCounter = obstacle.size();

        if (round != 0){
            isInitial = false;
        }



        if (obstacleCounter != 0 && isInitial) {
            while (0 < humans.size()) {
                Main.humans.remove(humans.get(humans.size() -1 ));
                humans.remove(humans.get(humans.size() - 1));
            }

            while (0 < titans.size()) {
                Main.allTitans.remove(titans.get(titans.size() - 1));
                titans.remove(titans.get(titans.size() - 1));
            }
            this.setObstacle(true);

        } else {
            if (humans.size() != 0 && humans.size() != 1 && isInitial) {
                while (humans.size() > 1) {
                    humansScore += humans.get(humans.size() - 1).getScore();
                    Main.humans.remove(humans.get(humans.size() -1 ));
                    humans.remove(humans.get(humans.size() - 1));
                }
                humans.get(0).setScore(humans.get(0).getScore() + humansScore);
            }

            if (titans.size() != 0 && titans.size() != 1 && isInitial) {
                while (titans.size() > 1) {
                    titansScore += titans.get(titans.size() - 1).getScore();
                    Main.allTitans.remove(titans.get(titans.size() - 1));
                    titans.remove(titans.get(titans.size() - 1));
                }
                titans.get(0).setScore(titans.get(0).getScore() + titansScore);
            }

            if (titans.size() == 1 && humans.size() == 1 && isInitial) {
                if (War.conflict(titans.get(0), humans.get(0)) == 0) {
                    deadHumans.add(humans.get(0));
                    Main.humans.remove(humans.get(0));
                    humans.remove(0);



                } else if (War.conflict(titans.get(0), humans.get(0)) == 2) {
                    Main.allTitans.remove(titans.get(0));
                    titans.remove(0);
                }
            }

            if (!isInitial){
                if (humans.size() > 1){
                    for (Human h:
                         humans) {
                        h.setScore(h.getScore() + 1);
                    }
                }

                if (titans.size() > 0 && humans.size() > 0 ) {
                    for (int i = 0; i <titans.size() ; i++) {
                        for (int j = 0; j <humans.size() ; j++) {
                            int result = War.conflict(titans.get(i),humans.get(j));
                            if (result == 0) {
                                deadHumans.add(humans.get(j));
                                Main.humans.remove(humans.get(j));
                                humans.remove(j);

                            } else if (result == 2) {
                                Main.allTitans.remove(titans.get(i));
                                titans.remove(i);

                            }
                        }
                    }
                }
            }
        }
        round += 1;
        forDeadUser();
    }



    /**
     * Dead humans score will be decrease every 5 round.*/
    public void forDeadUser() {
        if (this.round % 5 == 0 && deadHumans.size() != 0) {
            deadHumans.get(0).setScore(deadHumans.get(0).getScore() - 1);
        }
    }
}
