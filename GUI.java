package HomeWork2;
/**
 * @Author Said Ebrar Çankıran 20170808018
 *
 * @Since 03.09.2019
 *
 * @Version 1.0
 * */

import HomeWork2.Config;
import HomeWork2.Human;
import HomeWork2.Obstacle;
import HomeWork2.Titan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GUI extends JPanel{
        public int width;
        public int height;
        private int step = 0;
        private boolean run = false;
        private Config config;
        private Random random = new Random();
        private int dotSize = 4;
        private int speed = 1000;
        Thread animator;
        ArrayList<Titan> titans;
        private ArrayList[][] board;
         ArrayList<Human> peoples ;
         ArrayList<Obstacle> obstacles;

        /**
         * Constructor method and general procces for view method.*/
        public GUI(ArrayList<Human> people, ArrayList<Titan> Titans, ArrayList<Obstacle> obstacles) {
        setLayout(null); //Null makes all of the components will be at absolute position
        final int[] width = {100};
        final int[] height = {100};
        titans = Titans;
        peoples = people;
        this.obstacles = obstacles;


        JButton runButton = new JButton("Run");
        JButton pauseButton = new JButton("Stop");
        JButton stepButton = new JButton("Step");
        JButton upSpeedButton = new JButton("Fast");
        JButton downSpeedButton = new JButton("Slw");
        JButton magnifyButton = new JButton("+");/*getDiameter*/
        JButton shrinkButton = new JButton("-");


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButton.setEnabled(false);
                width[0] = 200;
                height[0] = 200;
                run = true;
                animator = new Thread(runnable);
                animator.start();
            }
        });
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (run) {
                    run = false;
                    runButton.setEnabled(true);
                } else {
                    /*doStep();*/
                    step++;
                    repaint();
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButton.setEnabled(true);
                if (run) {
                    run = false;
                }
            }
        });
        magnifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dotSize++;
            }
        });
        shrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dotSize > 1) {
                    dotSize--;
                }
            }
        });
        upSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed /= 2;
            }
        });
        downSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = speed == 0 ? 1 : speed * 2;
            }
        });

        runButton.setBounds(15, 20, 32, 32);
        stepButton.setBounds(15, 53, 32, 32);
        upSpeedButton.setBounds(15, 86, 32, 32);
        downSpeedButton.setBounds(15, 119, 32, 32);
        magnifyButton.setBounds(15, 152, 32, 32);
        shrinkButton.setBounds(15, 185, 32, 32);

        add(runButton);
        add(stepButton);
        add(upSpeedButton);
        add(downSpeedButton);
        add(magnifyButton);
        add(shrinkButton);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (run) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException ie) {
                    System.err.println("interrupt: " + ie.getMessage());
                }
                try {
                    Main.afterStartingProcces();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                step++;
                // same as update() method in C#
                repaint();
            }
        }
    };



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        for (Human person : peoples) {
            if (person.getLevel() != Human.Experience.DEAD) {
                g.setColor(person.getColor());
                g.fillRect(52 + person.coordiante[0][0] * dotSize, person.coordiante[0][1] * dotSize, person.getSize() * dotSize, person.getSize() * dotSize);
            } else {
                g.setColor(Color.BLACK);
            }
        }

        for (Titan titan : titans) {
            g.setColor(titan.getColor());
            g.fillRect(52 + titan.coordinate[0][0] * dotSize, titan.coordinate[0][1] * dotSize, titan.getDiameter() * dotSize, titan.getDiameter() * dotSize);
        }

        for (Obstacle obstacle : obstacles) {
            g.setColor(obstacle.getColor());
            g.fillRect(52 + obstacle.coordinate[0][0] * dotSize, obstacle.coordinate[0][1] * dotSize, dotSize, dotSize);
        }
    }


}