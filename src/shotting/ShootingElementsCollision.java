/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting;

import java.awt.Point;
import java.util.Iterator;
import shotting.Elements.Bullet;
import shotting.Elements.BulletCollision;
import shotting.Elements.Enemy;
import shotting.Elements.EnemyExplosion;
import shotting.Elements.Ship;

/**
 *
 * @author chg
 */
public class ShootingElementsCollision {

    private Display display;

    public ShootingElementsCollision(Display display) {
        this.display = display;
    }

    public void actionClasses() {
        Iterator<Bullet> itB = display.getBullets().iterator();
        while (itB.hasNext()) {
            Bullet b = itB.next();
            b.move();
            if (b.getBulletLocation().x + b.getSizeX() > display.getWidth()) {
                itB.remove();
            }
        }

        Iterator<Enemy> it = display.getEnemy().iterator();
        while (it.hasNext()) {
            Enemy en = it.next();
            en.move();
            if ((en.getPoint().x + en.getSize()) < 0) {
                // reduce score for each enemy that goes throgh
                if (display.getScore() > 0) {
                    display.setScore(display.getScore() - en.getLife() / 2);
                } else {
                    display.setScore(0);
                }
                // remove enemy
                it.remove();
            }
        }
        // check for empty enemy list
        if (display.getEnemy().isEmpty()) {
            display.createEnemy();
        }

        // update animation, check for collision and repaint
        checkCollision();
        display.getKeyH().Update();
    }

    private void checkCollision() {

        Iterator<Enemy> it = display.getEnemy().iterator();
        while (it.hasNext()) {
            Enemy ene = it.next();
            // check collision with bullet
            Iterator<Bullet> itB = display.getBullets().iterator();
            while (itB.hasNext()) {
                Bullet b = itB.next();
                Point bulletMiddle = new Point(b.getBulletLocation().x + b.getSizeX(), b.getBulletLocation().y + b.getSizeY() / 2);
                if (bulletMiddle.x > ene.getPoint().x + 30 && bulletMiddle.x < ene.getPoint().x + ene.getSize()
                        && bulletMiddle.y > ene.getPoint().y && bulletMiddle.y < ene.getPoint().y + ene.getSize()) {
                    // reduce life of enemy
                    if (ene.getLife() > 0) {
                        BulletCollision bc = new BulletCollision(new Point(b.getBulletLocation()), display);
                        bc.setCollision(true);
                        bc.shootCollisionExplosion(); // call timer to set collision to false
                        display.getBCol().add(bc);

                        ene.setLife(ene.getLife() - 1);
                        // remove enemy & add point to score
                        if (ene.getLife() == 0) {
                            // draw enemy explosion
                            EnemyExplosion enemyExpl = new EnemyExplosion(new Point(ene.getPoint()), display, ene.getSize());
                            enemyExpl.setCollision(true);
                            enemyExpl.enemyExplosion(); // call timer to set collision to false
                            display.getEnemyExplosion().add(enemyExpl);

                            // add point to score
                            display.setScore(display.getScore() + ene.getEnemyPoint());
                            // remove enemy from list
                            it.remove();
                        }
                    }
                    // remove bullet
                    itB.remove();
                }

            }
            // check collision with ship
            Ship ship = display.getShip();
            Point front = new Point(ship.getLocation().x + ship.getSize(), ship.getLocation().y + ship.getSize() / 2);
            if (!ship.getIsDead()) {
                if (front.x > ene.getPoint().x + 10 && front.x < ene.getPoint().x + ene.getSize()
                        && front.y > ene.getPoint().y && front.y < ene.getPoint().y + ene.getSize()) {
                    if (ship.getLifes() > 0) {
                        ship.setLifes(ship.getLifes() - 1);
                        ship.getLocation().x = 50;
                        ship.getLocation().y = display.getPreferredSize().height / 2 - ship.getSize() / 2;
                        ship.setIsDead(true);
                        ship.checkIsDead();
                    } else {
                        display.setGameOver(true);
                    }
                }
            }
        }

    }

    void resetGame() {
        display.setScore(0);
        display.getShip().setLifes(3);
        display.getEnemy().clear();
        display.setGameOver(false);
        display.startTimer();
        display.getShip().startTimer();
        display.getBtn().setVisible(false);
    }

}
