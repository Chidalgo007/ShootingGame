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
public class Grass {

    private Image grass;
    private Display display;
    private Point point;
    private int vel = 4;
    private int imgWidth;
    private int imgHeight;
    private double per = 0.6;

    public Grass(Display display) {
        this.display = display;
        createGrass();
    }

    private void createGrass() {

        Image scale = new ImageIcon(getClass().getResource("/img/grass.png")).getImage();
        imgWidth = (int) (scale.getWidth(null) * per);
        imgHeight = (int) (scale.getHeight(null) * per);
        System.out.println("width and height " + imgWidth + " " + imgHeight);
        grass = scale.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);

        point = new Point(0, display.getPreferredSize().height - imgHeight);

        if (!display.getGrassList().isEmpty()) {
            point.x = display.getGrassList().getLast().getPoint().x + imgWidth - 2;
        }
    }

    public void moveGrass() {
        point.x -= vel;
    }

    public void drawGrass(Graphics g) {
        g.drawImage(grass, point.x, point.y, display);
    }

// ------------------------ Getter ---------------------------------------------
    public Point getPoint() {
        return point;
    }

    public int getImgWidth() {
        return imgWidth;
    }
}
