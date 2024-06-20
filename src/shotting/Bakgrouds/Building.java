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
public class Building {

    private Image buildings;
    private Display display;
    private Point point;
    private int vel = 2;
    private int imgWidth;
    private int imgHeight;
    private double per = 0.6;

    public Building(Display display) {
        this.display = display;
        createBuilding();
    }

    private void createBuilding() {
        Image scale = new ImageIcon(getClass().getResource("/img/city.png")).getImage();
        imgWidth = (int) (scale.getWidth(null) * per);
        imgHeight = (int) (scale.getHeight(null) * per);
        buildings = scale.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);

        point = new Point(0, display.getPreferredSize().height - (imgHeight + 78));// is from street and grass

        if (!display.getBuildingList().isEmpty()) {
            point.x = display.getBuildingList().getLast().getPoint().x + imgWidth - 2;
        }
    }

    public void moveBuilding() {
        point.x -= vel;
    }

    public void drawBuildings(Graphics g) {

        g.drawImage(buildings, point.x, point.y, display);
    }

    // ------------------------ Getter ---------------------------------------------
    public Point getPoint() {
        return point;
    }

    public int getImgWidth() {
        return imgWidth;
    }

}
