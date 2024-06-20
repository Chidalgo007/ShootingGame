/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting.Elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import shotting.Display;

/**
 *
 * @author chg
 */
public class Bullet {

    private Point bulletLocation;
    private Display display;
    private Image bullet;
    private int vel = 15;
    private int sizeX = 80;
    private int sizeY = 25;

    public Bullet(Display display, Point point) {
        this.display = display;
        bulletLocation = point;

        Image scale = new ImageIcon(getClass().getResource("/img/bullet.png")).getImage();
        bullet = scale.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);

    }

    public void drawBullets(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.drawRect(bulletLocation.x, bulletLocation.y, sizeX, sizeY);

        g.drawImage(bullet, bulletLocation.x, bulletLocation.y, display);

    }

    public void move() {
        if (this.bulletLocation.x < display.getWidth()) {
            this.bulletLocation.x += vel;
        }
    }

    public Point getBulletLocation() {
        return bulletLocation;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

}
