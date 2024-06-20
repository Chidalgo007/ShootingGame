/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting.Elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import shotting.Display;

/**
 *
 * @author chg
 */
public class EnemyExplosion {

    private boolean collision = false;
    private java.util.Timer utilTimer;
    private Point point;
    private Display display;
    private Image enemyExplosion;
    private int size;

    public EnemyExplosion(Point point, Display display, int size) {
        this.point = point;
        this.display = display;
        this.size = size;

        Image scaleExpl = new ImageIcon(getClass().getResource("/img/explosion.png")).getImage();
        enemyExplosion = scaleExpl.getScaledInstance(size*2, size*2, Image.SCALE_DEFAULT);
    }

    public void enemyExplosion() {
        utilTimer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                collision = false;
                display.getEnemyExplosion().remove();
            }
        };
        utilTimer.schedule(task, 150);
    }

    public void paintExplosion(Graphics g) {
        if (collision) {
            g.drawImage(enemyExplosion, point.x-size/2, point.y-size/2, display);
        }
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
