/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting;

import shotting.Elements.Bullet;
import shotting.Elements.Ship;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import shotting.Bakgrouds.Building;
import shotting.Bakgrouds.Clouds;
import shotting.Bakgrouds.Grass;
import shotting.Bakgrouds.Street;
import shotting.Elements.BulletCollision;
import shotting.Elements.Enemy;
import shotting.Elements.EnemyExplosion;

/**
 *
 * @author chg
 */
public final class Display extends JPanel implements ActionListener {

// ship, bullet and enemies
    private Ship ship;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemy;
// game effecs
    private LinkedList<EnemyExplosion> enemyExplosion;
    private LinkedList<BulletCollision> bCollision;
// game Over
    private boolean gameOver = false;
    private JButton btn;
// background items
    private int numOfEnemies = 10;
    private int numOfClouds = 3;
    private int numOfBuilding = 3;
    private int numOfStreet = 3;
    private int numOfGrass = 3;
    private Image sky;
    private LinkedList<Clouds> cloudList;
    private LinkedList<Building> buildingList;
    private LinkedList<Street> streetList;
    private LinkedList<Grass> grassList;
// game handelers
    private KeyHandeling keyH;
    private ShootingElementsCollision checkCollision;
    private ShootingBackgroundElements bgElements;
// others
    private Timer timer;
    private int score;

    public Display() {
        setPreferredSize(new Dimension(1200, 600));
        setBackground(Color.red);
        setDoubleBuffered(true);
        setFocusable(true);

        // create  ship treat
        ship = new Ship(this);
        // handeling keys
        keyH = new KeyHandeling(this, ship);
        addKeyListener(keyH);

        // init shooting elements array
        enemy = new LinkedList();
        bullets = new LinkedList();
        enemyExplosion = new LinkedList();
        bCollision = new LinkedList();
        createEnemy();

        checkCollision = new ShootingElementsCollision(this);

        //--------- background ------------
        sky = new ImageIcon(getClass().getResource("/img/sky.png")).getImage();
        cloudList = new LinkedList();
        buildingList = new LinkedList();
        streetList = new LinkedList();
        grassList = new LinkedList();
        createCloud();
        createBuilding();
        createStreet();
        createGrass();
        bgElements = new ShootingBackgroundElements(this);

        timer = new Timer(1000 / 60, this);
        startTimer();
    }

    public void startTimer() {
        timer.start();
    }

    public void createEnemy() {
        // create x num of enemies to start the game
        // add it to the arrray to interact with them
        for (int i = 0; i < numOfEnemies; i++) {
            enemy.add(new Enemy(this));
        }
    }

    public void createCloud() {
        // create x num of clouds at the beggining of the game
        // add it to the arrray to interact with them
        for (int i = 0; i < numOfClouds; i++) {
            cloudList.add(new Clouds(this));
        }
    }

    public void createBuilding() {
        // create x num of buildings at the beggining of the game
        // add it to the arrray to interact with them
        for (int i = 0; i < numOfBuilding; i++) {
            buildingList.add(new Building(this));
        }
    }

    public void createStreet() {
        // create x num of street at the beggining of the game
        // add it to the arrray to interact with them
        for (int i = 0; i < numOfStreet; i++) {
            streetList.add(new Street(this));
        }
    }

    public void createGrass() {
        // create x num of grass at the beggining of the game
        // add it to the arrray to interact with them
        for (int i = 0; i < numOfGrass; i++) {
            grassList.add(new Grass(this));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(sky, 0, 0, this); // sky background

        for (Clouds cloud : cloudList) { // draw clouds
            cloud.drawClouds(g);
        }

        for (Building buil : buildingList) { // draw buuilding
            buil.drawBuildings(g);
        }
        for (Street st : streetList) { // draw Street
            st.drawStreet(g);
        }

        for (Enemy ene : enemy) { // draw enemy
            ene.drawEnemy(g);
        }

        for (EnemyExplosion ee : enemyExplosion) { // draw enemy explosion
            ee.paintExplosion(g);
        }

        for (Bullet b : bullets) { // draw bullets
            b.drawBullets(g);
        }

        for (BulletCollision bc : bCollision) {
            bc.paintBCollision(g);
        }

        ship.drawShip(g);
        ship.drawLisfes(g);
        ship.drawBulletsCount(g);

        for (Grass gr : grassList) { // draw grass
            gr.drawGrass(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Current Score: " + score, getWidth() - 150, 15);

        if (gameOver) {
            checkGameOver(g);
        }
    }

    public void checkGameOver(Graphics g) {

        timer.stop();
        g.setColor(new Color(0, 0, 0, 70));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2 - 20);

        btn = new JButton("Play Again!");
        int btnWidth = 150;
        int btnHeight = 50;
        btn.setBounds(getWidth() / 2 - btnWidth / 2, getHeight() - getHeight() / 3 - btnHeight / 2, btnWidth, btnHeight);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        btn.setFont(new Font("Console", Font.BOLD, 18));
        btn.setBackground(new Color(20, 110, 250));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        btn.setFocusable(false);
        btn.setVisible(true);
        add(btn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollision.actionClasses();
        bgElements.checkElements();
        if (e.getSource() == btn) {
            checkCollision.resetGame();
        }
        repaint();
    }
// -----------------  GETTERS --------------------------------------------------

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public LinkedList<BulletCollision> getBCol() {
        return bCollision;
    }

    public LinkedList<Enemy> getEnemy() {
        return enemy;
    }

    public LinkedList<Clouds> getCloudList() {
        return cloudList;
    }

    public LinkedList<Building> getBuildingList() {
        return buildingList;
    }

    public LinkedList<Street> getStreetList() {
        return streetList;
    }

    public LinkedList<Grass> getGrassList() {
        return grassList;
    }

    public LinkedList<EnemyExplosion> getEnemyExplosion() {
        return enemyExplosion;
    }

    public KeyHandeling getKeyH() {
        return keyH;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean x) {
        gameOver = x;
    }

    public int getScore() {
        return score;
    }

    public Ship getShip() {
        return ship;
    }

    public JButton getBtn() {
        return btn;
    }

    public void setScore(int Score) {
        this.score = Score;
    }

}
