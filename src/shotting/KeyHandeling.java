/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import shotting.Elements.Bullet;
import shotting.Elements.Ship;

/**
 *
 * @author chg
 */
public class KeyHandeling implements KeyListener {

    boolean up, down, left, right, shoot;
    Ship ship;
    Display display;

    public KeyHandeling(Display display, Ship ship) {
        this.ship = ship;
        this.display = display;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            shoot = true;
            if (ship.getBullets() != 0) {
                int x = (int) ship.getLocation().x + ship.getSize();
                int y = (int) ship.getLocation().y + ship.getSize() / 2;

                display.getBullets().add(new Bullet(display, new Point(x, y)));
                ship.setBullets(ship.getBullets() - 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
//        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            shoot = false;
//        }
    }

    public void Update() {
        if (up) {
            if (ship.getLocation().y > 0) {
                ship.getLocation().y -= ship.getVel();
            }
        } else if (down) {
            if (ship.getLocation().y + ship.getSize() < display.getHeight()) {
                ship.getLocation().y += ship.getVel();
            }
        }
        if (right) {
            if (ship.getLocation().x + ship.getSize() < display.getWidth()) {
                ship.getLocation().x += ship.getVel();
            }
        } else if (left) {
            if (ship.getLocation().x > 0) {
                ship.getLocation().x -= ship.getVel();
            }
        }
//        if (shoot) {
//            if (ship.getBullets() != 0) {
//                int x = (int) ship.getLocation().x + ship.getSize();
//                int y = (int) ship.getLocation().y + ship.getSize() / 2;
//
//                display.getBullets().add(new Bullet(display, new Point(x, y)));
//                ship.setBullets(ship.getBullets() - 1);
//            }
//        }
    }
}
