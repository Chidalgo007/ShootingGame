/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting.Elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
//import javax.swing.Timer;
import shotting.Display;

/**
 *
 * @author chg
 */
public class Ship implements ActionListener {

    private final int size = 60;
    private final int heartSize = 15;
    private final int vel = 10;
    private Point location;
    private Image shipImg;
    private Image emptyHeart;
    private Image fullHeart;
    private int lifes = 3;
    private int current_Lifes = 3;
    private int numOfBullets = 20;
    private int bullets;
    private final Display display;
    private boolean dead = false;
    private javax.swing.Timer swingTimer;
    private java.util.Timer utilTimer;

    public Ship(Display display) {
        this.display = display;
        createShip();
        swingTimer = new javax.swing.Timer(300, this);
        startTimer();
    }

    public void startTimer() {
        swingTimer.start();
    }

    private void createShip() {
        location = new Point(50, (display.getPreferredSize().height / 2 - size / 2));
        bullets = numOfBullets;
        Image scale = new ImageIcon(getClass().getResource("/img/ship_9.png")).getImage();
        shipImg = scale.getScaledInstance(size + 10, size, Image.SCALE_DEFAULT);

        Image scale3 = new ImageIcon(getClass().getResource("/img/heart_full.png")).getImage();
        fullHeart = scale3.getScaledInstance(heartSize, heartSize, Image.SCALE_SMOOTH);
    }

// ---------------- DRAWING ----------------------------------------------------    
    public void drawShip(Graphics g) {
        if (dead) {
            g.setColor(new Color(255, 255, 255, 70));
            g.fillOval(location.x, location.y, getSize() + 10, getSize() + 10);
        }
        g.drawImage(shipImg, location.x, location.y, display);
    }

    public void drawLisfes(Graphics g) {

        g.setColor(Color.RED);
        for (int i = 0; i < lifes; i++) {
            int space = (20 * i) + 11;
            g.drawString("X", space, 15);
        }
        g.setColor(Color.RED);
        for (int i = 0; i < current_Lifes; i++) {
            int space = (20 * i) + 7;
            g.drawImage(fullHeart, space, 5, display);
        }
    }

    public void drawBulletsCount(Graphics g) {
        g.setColor(Color.ORANGE);

        for (int i = 1; i <= bullets; i++) {
            int space = 7 * i;
            g.fillRect(space, 25, 3, 10);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bullets < numOfBullets) {
            bullets++;
        }
        if (display.getGameOver()) {
            swingTimer.stop();
        }
    }

    public void checkIsDead() {
        if (dead) {
            utilTimer = new java.util.Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    dead = false;
                }
            };
            utilTimer.schedule(task, 3000);
        }
    }

// ----------- GETTER AND SETTERS ----------------------------------------------
    public int getBullets() {
        return bullets;
    }

    public void setBullets(int x) {
        bullets = x;
    }

    public int getLifes() {
        return current_Lifes;
    }

    public void setLifes(int x) {
        current_Lifes = x;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean getIsDead() {
        return dead;
    }

    public void setIsDead(boolean x) {
        this.dead = x;
    }

    public int getSize() {
        return size;
    }

    public int getVel() {
        return vel;
    }

}
