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
public class BulletCollision {

    private boolean collision = false;
    private java.util.Timer utilTimer;
    private Point point;
    private Display display;
    private Image bCol;

    public BulletCollision(Point point, Display display) {
        this.point = point;
        this.display = display;

        Image scaleCol = new ImageIcon(getClass().getResource("/img/bulletCollision.png")).getImage();
        bCol = scaleCol.getScaledInstance(70, 50, Image.SCALE_DEFAULT);
    }

    public void shootCollisionExplosion() {
        utilTimer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    setCollision(false);
                if (!display.getBCol().isEmpty()) {
                    display.getBCol().remove(this);
                }
            }
        };
        utilTimer.schedule(task, 30);
    }

    public void paintBCollision(Graphics g) {
        if (collision) {
            g.drawImage(bCol, point.x, point.y, display);
        }
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
