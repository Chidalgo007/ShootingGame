/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting.Elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import shotting.Display;

/**
 *
 * @author chg
 */
public class Enemy {

    private Display display;
    private Point point;
    private ArrayList enemies = new ArrayList();
    private Map<String, String> enemy_1 = new HashMap<>();
    private Map<String, String> enemy_2 = new HashMap<>();
    private Map<String, String> enemy_3 = new HashMap<>();
    private Map<String, String> enemy_4 = new HashMap<>();
    private HashMap<String, String> enemy;

    private int width;
    private int height;
    private int size;
    private int life;
    private String img;
    private int enemyPoint;
    private int vel;
    private Image enemyImg;

    public Enemy(Display display) {
        this.display = display;
        width = display.getPreferredSize().width;
        height = display.getPreferredSize().height;

        // create the enemies types
        enemy_1.put("size", "50");
        enemy_1.put("life", "2");
        enemy_1.put("enemyPoint", "2");
        enemy_1.put("vel", "9");
        enemy_1.put("img", "bee.png");

        enemy_2.put("size", "60");
        enemy_2.put("life", "6");
        enemy_2.put("enemyPoint", "6");
        enemy_2.put("vel", "7");
        enemy_2.put("img", "ghost.png");

        enemy_3.put("size", "80");
        enemy_3.put("life", "9");
        enemy_3.put("enemyPoint", "9");
        enemy_3.put("vel", "5");
        enemy_3.put("img", "one_eye.png");

        enemy_4.put("size", "120");
        enemy_4.put("life", "15");
        enemy_4.put("enemyPoint", "15");
        enemy_4.put("vel", "3");
        enemy_4.put("img", "ball_2.png");

        // add it to array to select random
        enemies.add(enemy_1);
        enemies.add(enemy_2);
        enemies.add(enemy_3);
        enemies.add(enemy_4);

        createEnemy();

    }

    public void createEnemy() {
        // select random enemy
        int index = (int) (Math.random() * enemies.size());
        enemy = (HashMap<String, String>) enemies.get(index);

        // assign values to variables for easy use
        size = Integer.parseInt(enemy.get("size"));
        vel = Integer.parseInt(enemy.get("vel"));
        life = Integer.parseInt(enemy.get("life"));
        enemyPoint = Integer.parseInt(enemy.get("enemyPoint"));
        img = enemy.get("img");

        Image scaleExpl = new ImageIcon(getClass().getResource("/img/" + img)).getImage();
        enemyImg = scaleExpl.getScaledInstance(size, size, Image.SCALE_DEFAULT);

        // create point to start enemies
        int x = width + (int) (Math.random() * 300);
        int y = (int) (Math.random() * (height - size) + 1);
        point = new Point((x), y);

    }

    // draw the enemy
    public void drawEnemy(Graphics g) {
        g.drawImage(enemyImg, point.x, point.y, display);
        // energy or life bar
        g.setColor(Color.BLACK);
        g.drawRect(point.x, point.y - 5, size, 5);
        float totalLife = Integer.parseInt(enemy.get("life"));
        float per = life / totalLife;

        if (per > 0.74) {
            g.setColor(Color.GREEN);
        } else if (per > 0.49) {
            g.setColor(Color.ORANGE);
        } else if (per > 0.24) {
            g.setColor(new Color(200, 60, 5));
        } else {
            g.setColor(Color.RED);
        }
        int perSize = (int) (size * per);
        g.fillRect(point.x, point.y - 5, perSize, 5);
    }

    // move enemy
    public void move() {
        this.point.x -= this.vel;
    }
    
    // ---------- Getter and Setters -------------------------------------------

    public Point getPoint() {
        return point;
    }

    public int getEnemyPoint() {
        return enemyPoint;
    }

    public int getSize() {
        return size;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int x) {
        life = x;
    }

}
