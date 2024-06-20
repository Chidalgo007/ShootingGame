/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting.Bakgrouds;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import shotting.Display;

/**
 *
 * @author chg
 */
public class Clouds {

    private Image clouds;
    private Display display;
    private Point point;
    private int vel;
    private int width;
    private int height;
    private double size;
    private final String[] cloudArray = {"cloud2.png", "cloud3.png", "cloud4.png", "cloud5.png"};

    public Clouds(Display display) {
        this.display = display;
        createCloud();
    }

    private void createCloud() {
        //select image
        int rand = (int) (Math.random() * cloudArray.length);

        Image scale = new ImageIcon(getClass().getResource("/img/" + cloudArray[rand])).getImage();
        // choose random size
        double[] sizeOption = {0.30, 0.50, 0.70, 0.80};
        size = sizeOption[(int) (Math.random() * sizeOption.length)];

        width = (int) ((int) scale.getWidth(null) * size);
        height = (int) ((int) scale.getHeight(null) * size);
        clouds = scale.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set location
        int x = (int) (display.getWidth() + Math.random() * 400);
        int y = (int) (Math.random() * (display.getPreferredSize().height/2 - height));
        point = new Point(x, y);

        // set speed depending on the size
        vel = (size == 0.80) ? 4 : (size == 0.70) ? 3 : (size == 0.50) ? 2 : (size == 0.30) ? 1 : 1;
        // check if any other cloud has min speed and adjust 
        if (vel == 1) {
            for (Clouds cl : display.getCloudList()) {
                if (cl.vel == 1) {
                    vel = 2;
                }
            }
        }
    }

    public void moveClouds() {
        point.x -= vel;
    }

    public void drawClouds(Graphics g) {
        g.drawImage(clouds, getPoint().x, getPoint().y, display);
    }

    public Point getPoint() {
        return point;
    }

    public int getWidth() {
        return width;
    }

}
