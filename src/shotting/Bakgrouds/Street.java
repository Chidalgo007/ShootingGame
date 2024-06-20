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
public class Street {

    private Image street;
    private Display display;
    private Point point;
    private int vel = 2;
    private int imgWidth;
    private int imgHeight;
    private double per = 0.6;

    public Street(Display display) {
        this.display = display;
        createStreet();
    }

    private void createStreet() {

        Image scale = new ImageIcon(getClass().getResource("/img/street.png")).getImage();
        imgWidth = (int) (scale.getWidth(null) * per);
        imgHeight = (int) (scale.getHeight(null) * per);
        System.out.println("width and height "+imgWidth+" "+imgHeight);
        street = scale.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);

        point = new Point(0, display.getPreferredSize().height - (imgHeight + 28));// 50 from grass

        if (!display.getStreetList().isEmpty()) {
            point.x = display.getStreetList().getLast().getPoint().x + imgWidth - 2;
        }
    }

    public void moveStreet() {
        point.x -= vel;
    }

    public void drawStreet(Graphics g) {
        g.drawImage(street, point.x, point.y, display);
    }

// ------------------------ Getter ---------------------------------------------
    public Point getPoint() {
        return point;
    }

    public int getImgWidth() {
        return imgWidth;
    }
}
