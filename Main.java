

package HomeWork2;

/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static  Tile[][] map;
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    static ArrayList<Human> humans = new ArrayList<>();
    static ArrayList<Titan> titans3 = new ArrayList<>();
    static Titan[] titans15 = new Titan[Config.numberOf15mTitan];
    static Titan[] titans5 = new Titan[Config.numberOf5mTitan];
    static ArrayList<Obstacle> obstacles = new ArrayList<>();
    static ArrayList<Titan> allTitans = new ArrayList<>();

    static int round = 0;


    public static void main(String[] args) throws IOException {
        new Config();
        startingProcces();
    }




    /**
     * In this func, create map and adding tiles.
     * Also Object signed as a randomly on Tile with their coordinates.
     * And GUI panel features set and panel created and show.
     * */
    public static void startingProcces() {
        map = createMap(Config.width, Config.height);



        Random rand = new Random();



        setArrays(map, humans, titans3, titans5, titans15, obstacles);



        for (Tile[] s :
                map) {
            for (Tile a :
                    s) {
                a.whoAreYou();
            }
        }
        System.err.println("*************");

        JFrame frame = new JFrame("hum&Tit V1.0");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
//        Display display = new Display();
        frame.add(new GUI(humans,allTitans,obstacles));
        frame.setVisible(true);

    }


    /**
     * Get human and titan count and give me result of game, humans win or titan win
     * Give me round
     * Pace on Tile and if there are conflict, there will be create a war. after war they are move with their result. If dead no one can not move.
     * */
    public static void afterStartingProcces() throws InterruptedException {
        int humanCount = Config.numberOfPeople;
        int titanCount = Config.numberOf3mTitan + Config.numberOf5mTitan + Config.numberOf15mTitan;

        /*while (humanCount > 0 && titanCount > 0) {*/
        Thread.sleep(200);
        int hm = 0;
        int tn = 0;
        for (Tile[] s :
                map) {
            for (Tile a :
                    s) {
                a.whoAreYou();
                int[][] oldcoordinate = a.coordinate;
                int direkction = War.move();
                MOVE(a, direkction, map);

            }
        }

        //After move check titans and humans
        for (Tile[] s :
                map) {
            for (Tile a :
                    s) {

                if (!a.isObstacle()) {
                    if (a.titans.size() != 0 && a.humans.size() != 0) {
                        War.conflict(a.titans.get(0), a.humans.get(0));
                    }
                }

                hm += a.humans.size();
                tn += a.titans.size();
            }
        }

        System.err.println("**");
        System.out.println(hm);
        System.out.println(tn);
        humanCount = hm;
        titanCount = tn;

        round += 1;
        /*}*/

        if (humanCount == 0) {
            System.out.println("Titanlar kazandı");
            System.out.println("Oynana El: " + round);
            Thread.currentThread().stop();
        } else if (titanCount == 0) {
            System.out.println("İnsanlar kazandı");
            System.out.println("Oynana El: " + round);
            Thread.currentThread().stop();

        } else {
            System.out.println("Human count = " + humanCount);
            System.out.println("Titan count = " + titanCount);
            /*System.err.println("Bişier oldu ama ne oldu bilmiyoruz");*/
        }


    }


    /**
     * Creating map and adding tiles
     * */
    public static Tile[][] createMap(int row, int column) {
        Tile[][] map = new Tile[row][column];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int[][] coordinate = new int[1][2];
                coordinate[0][0] = i;
                coordinate[0][1] = j;
                map[i][j] = new Tile(coordinate);
            }
        }
        return map;
    }

    /**
     * Set titans and humans on tiles with random coordinate and they added to general titan or humans list.
     * */
    public static void setArrays(Tile[][] map, ArrayList<Human> human, ArrayList<Titan> titans1, Titan[] titans2, Titan[] titans3, ArrayList<Obstacle> obstacless) {
        Random rand = new Random();


        for (int i = 0; i < Config.numberOfPeople; i++) {
            Human humans = new Human();
            humans.coordiante[0][0] = rand.nextInt(Config.height - 1);
            humans.coordiante[0][1] = rand.nextInt(Config.width - 1);
            map[humans.coordiante[0][0]][humans.coordiante[0][1]].humans.add(humans);
            human.add(humans);
        }

        for (int i = 0; i < Config.numberOf3mTitan; i++) {
            Titan titans = new Titan(Config.initialScoreOf3mTitan);
            titans.coordinate[0][0] = rand.nextInt(Config.width - 1);
            titans.coordinate[0][1] = rand.nextInt(Config.height - 1);
            map[titans.coordinate[0][0]][titans.coordinate[0][1]].titans.add(titans);
            titans1.add(titans);
            allTitans.add(titans);
        }

        for (int i = 0; i < Config.numberOf5mTitan; i++) {
            titans2[i] = new Titan(Config.initialScoreOf5mTitan);
            titans2[i].coordinate[0][0] = rand.nextInt(Config.width - 1);
            titans2[i].coordinate[0][1] = rand.nextInt(Config.height - 1);
            map[titans2[i].coordinate[0][0]][titans2[i].coordinate[0][1]].titans.add(titans2[i]);
            titans1.add(titans2[i]);
            allTitans.add(titans2[i]);

        }

        for (int i = 0; i < Config.numberOf15mTitan; i++) {
            titans3[i] = new Titan(Config.initialScoreOf15mTitan);
            titans3[i].coordinate[0][0] = rand.nextInt(Config.width - 1);
            titans3[i].coordinate[0][1] = rand.nextInt(Config.height - 1);
            map[titans3[i].coordinate[0][0]][titans3[i].coordinate[0][1]].titans.add(titans3[i]);
            titans1.add(titans3[i]);
            allTitans.add(titans3[i]);
        }

        for (int i = 0; i < Config.numberOfObstacle; i++) {
            Obstacle obstacles = new Obstacle();
            obstacles.coordinate[0][0] = rand.nextInt(Config.width - 1);
            obstacles.coordinate[0][1] = rand.nextInt(Config.height - 1);
            map[obstacles.coordinate[0][0]][obstacles.coordinate[0][1]].obstacle.add(obstacles);
            obstacless.add(obstacles);
        }
    }

    /**
     * Checking move directions and cheking conditions for move. Is this obstacle or is there a wall and others.
     * */
    public static void MOVE(Tile a, int direkction, Tile[][] map) {
        switch (direkction) {
            case 1:
                if (!a.titans.isEmpty()) {

                    if (a.titans.get(0).coordinate[0][0] != 0) {

                        if (!map[a.titans.get(0).coordinate[0][0] - 1][a.titans.get(0).coordinate[0][1]].isObstacle()) {

                            a.titans.get(0).coordinate[0][0] = a.titans.get(0).coordinate[0][0] - 1;
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);

                        } else if (map[a.titans.get(0).coordinate[0][0] - 1][a.titans.get(0).coordinate[0][1]].isObstacle() && a.titans.get(0).getLevel() == Titan.Experience.HIGH) {
                            a.titans.get(0).coordinate[0][0] = a.titans.get(0).coordinate[0][0] - 1;

                            obstacles.remove(map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.get(0));
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.remove(0);
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].setObstacle(false);

                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);



                        }
                    }

                }
                if (!a.humans.isEmpty()) {

                    if (a.humans.get(0).coordiante[0][0] != 0) {

                        if (!map[a.humans.get(0).coordiante[0][0] - 1][a.humans.get(0).coordiante[0][1]].isObstacle()) {
                            a.humans.get(0).coordiante[0][0] = a.humans.get(0).coordiante[0][0] - 1;

                            if (map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.size() != 0) {
                                a.humans.get(0).setScore(a.humans.get(0).getScore() + map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.get(0).getScore());
                                map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.remove(0);
                            }

                            map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].humans.add(a.humans.get(0));
                            a.humans.remove(0);
                        }

                    }

                }
                break;


            case 2:
                if (!a.titans.isEmpty()) {

                    if (a.titans.get(0).coordinate[0][0] != (map.length - 1)) {

                        if (map[a.titans.get(0).coordinate[0][0] + 1][a.titans.get(0).coordinate[0][1]].isObstacle() && a.titans.get(0).getLevel() == Titan.Experience.HIGH) {
                            a.titans.get(0).coordinate[0][0] = a.titans.get(0).coordinate[0][0] + 1;

                            obstacles.remove(map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.get(0));
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.remove(0);
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].setObstacle(false);

                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);




                        } else if (!map[a.titans.get(0).coordinate[0][0] + 1][a.titans.get(0).coordinate[0][1]].isObstacle()) {

                            a.titans.get(0).coordinate[0][0] = a.titans.get(0).coordinate[0][0] + 1;
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);

                        }

                    }

                }
                if (!a.humans.isEmpty()) {

                    if (a.humans.get(0).coordiante[0][0] != (map.length - 1)) {

                        if (!map[a.humans.get(0).coordiante[0][0] + 1][a.humans.get(0).coordiante[0][1]].isObstacle()) {
                            a.humans.get(0).coordiante[0][0] = a.humans.get(0).coordiante[0][0] + 1;

                            if (map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.size() != 0) {
                                a.humans.get(0).setScore(a.humans.get(0).getScore() + map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.get(0).getScore());
                                map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.remove(0);
                            }

                            map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].humans.add(a.humans.get(0));
                            a.humans.remove(0);
                        }
                    }
                }
                break;


            case 3:
                if (!a.titans.isEmpty()) {

                    if (a.titans.get(0).coordinate[0][1] != (map.length - 1)) {

                        if (map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1] + 1].isObstacle() && a.titans.get(0).getLevel() == Titan.Experience.HIGH) {
                            a.titans.get(0).coordinate[0][1] = a.titans.get(0).coordinate[0][1] + 1;

                            obstacles.remove(map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.get(0));
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.remove(0);
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].setObstacle(false);

                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);

                        } else if (!map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1] + 1].isObstacle()) {

                            a.titans.get(0).coordinate[0][1] = a.titans.get(0).coordinate[0][1] + 1;
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);

                        }

                    }

                }
                if (!a.humans.isEmpty()) {

                    if (a.humans.get(0).coordiante[0][1] != (map.length - 1)) {

                        if (!map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1] + 1].isObstacle()) {
                            a.humans.get(0).coordiante[0][1] = a.humans.get(0).coordiante[0][1] + 1;

                            if (map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.size() != 0) {
                                a.humans.get(0).setScore(a.humans.get(0).getScore() + map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.get(0).getScore());
                                map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.remove(0);
                            }

                            map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].humans.add(a.humans.get(0));
                            a.humans.remove(0);
                        }
                    }
                }
                break;


            case 4:
                if (!a.titans.isEmpty()) {

                    if (a.titans.get(0).coordinate[0][1] != 0) {

                        if (map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1] - 1].isObstacle() && a.titans.get(0).getLevel() == Titan.Experience.HIGH) {
                            a.titans.get(0).coordinate[0][1] = a.titans.get(0).coordinate[0][1] - 1;

                            obstacles.remove(map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.get(0));
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].obstacle.remove(0);
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].setObstacle(false);



                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);



                        } else if (!map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1] - 1].isObstacle()){

                            a.titans.get(0).coordinate[0][1] = a.titans.get(0).coordinate[0][1] - 1;
                            map[a.titans.get(0).coordinate[0][0]][a.titans.get(0).coordinate[0][1]].titans.add(a.titans.get(0));
                            a.titans.remove(0);
                        }

                    }

                }
                if (!a.humans.isEmpty()) {
                    if (a.humans.get(0).coordiante[0][1] != 0) {

                        if (!map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1] - 1].isObstacle()) {
                            a.humans.get(0).coordiante[0][1] = a.humans.get(0).coordiante[0][1] - 1;

                            if (map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.size() != 0) {
                                a.humans.get(0).setScore(a.humans.get(0).getScore() + map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.get(0).getScore());
                                map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].deadHumans.remove(0);
                            }

                            map[a.humans.get(0).coordiante[0][0]][a.humans.get(0).coordiante[0][1]].humans.add(a.humans.get(0));
                            a.humans.remove(0);
                        }
                    }
                }
                break;
        }
    }


}

