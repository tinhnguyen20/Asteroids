import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Board extends JPanel implements ActionListener, Data {
    private Random rand;
    private Timer timer;
    private Player craft;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<TripleShotPowerUp> tPower;
    private boolean ingame;
    private int shot_count;
    private int score;
    private int life;
    private int powerUpSpawnTime;
    private int asteroidsDestroyed;
    private long time;

    public Board() {
        asteroids = new ArrayList<Asteroid>();
        tPower = new ArrayList<TripleShotPowerUp>();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        craft = new Player();
        //spawnAsteroid();
        timer = new Timer(5, this);
        timer.start();
        ingame = true;
        rand =  new Random();
        score = 0;
        time = 0;
        life = 2;
        powerUpSpawnTime = 5000;
        
    }

    public void spawnTripleShotPowerUp(){
        int xPos = (int)(Math.random() * BOARD_LENGTH);
        
        
        tPower.add(new TripleShotPowerUp(xPos, 0));
    }

    public void spawnAsteroidFollow()
    {
        int craft_xPos = craft.getX();
        double r =  Math.random();
        int a = 0;
        if(r < .5){
            a =  (int)((-1) * r * 600);
        }
        else{
            a =  (int)(r * 600);    
        }

        asteroids.add(new Asteroid(craft.getX() + a, 0));
        //asteroids.add(new Asteroid(craft.getX(), 0));
        //asteroids.add(new Asteroid(craft.getX() - 30, 0));
    }

    public void spawnAsteroids()
    {
        //checks if asteroid hits the player
        for(int i = 0; i <= BOARD_HEIGHT; i+=200){
            if(i == 0){
                double r =  Math.random();
                asteroids.add(new Asteroid(i + (int)(300 * r), 0));
            }
            else{
                double r = Math.random();
                double alpha = Math.random();
                int a = 1;
                if(alpha < .5){
                    alpha *= -1;    
                }
                int shift = (int)(a * 300 * r);

                asteroids.add(new Asteroid(i + shift, 0));
            }
        }

    }

    public void checkCollisions(){
        //checks if astroid hits player
        Rectangle c1 = craft.getBounds();

        for(int i = 0; i < asteroids.size(); i++){
            Asteroid a = (Asteroid) asteroids.get(i);
            Rectangle r1 =  a.getBounds();
            if(c1.intersects(r1)){
                if(life == 0){
                    craft.setVisible(false);
                    ingame = false;

                }
                else{
                    life--;
                    a.setVisible(false);
                    
                }
            }
        }

        //checks if missiles hit the asteroid
        ArrayList missiles = craft.getMissiles();
        for(int j = 0; j < missiles.size(); j++){
            Missile m = (Missile) missiles.get(j);
            Rectangle r2 = m.getBounds();
            for(int x = 0; x < asteroids.size(); x++){
                Asteroid as = (Asteroid) asteroids.get(x);
                Rectangle r3 =  as.getBounds();
                if(r2.intersects(r3)){
                    m.setVisible(false);
                    as.setVisible(false);
                    if(craft.isVisible()){
                        score += 100;
                        asteroidsDestroyed++;
                    }
                }
            }
        }

        // checks if player hit the powerUp
        for(int p = 0; p < tPower.size(); p++){
            TripleShotPowerUp powerUp = (TripleShotPowerUp) tPower.get(p);
            Rectangle r4 = powerUp.getBounds();
            if(c1.intersects(r4)){
                powerUp.setVisible(false);
                int ts = craft.getTripleShots();
                craft.setTripleShots(ts + 10);

            }
        }
    }

    public void drawAsteroids(Graphics g){
        Iterator it = asteroids.iterator();
        while(it.hasNext()){
            Asteroid asteroid = (Asteroid) it.next();
            g.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(),this); 
        }
    }

    public void drawMissiles(Graphics g){

        ArrayList missiles = craft.getMissiles();

        Iterator it = missiles.iterator();
        while(it.hasNext()){
            Missile missile = (Missile) it.next();
            g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
        }

    }

    public void drawPowerUps(Graphics g){
        Iterator it = tPower.iterator();
        while(it.hasNext()){
            TripleShotPowerUp tpower = (TripleShotPowerUp) it.next();
            g.drawImage(tpower.getImage(), tpower.getX(), tpower.getY(),this);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        long t = System.currentTimeMillis();
        if(ingame){

            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
            drawAsteroids(g);
            drawMissiles(g);
            drawPowerUps(g);
            //spawnTripleShotPowerUp();
            if( t% 50 == 0){
                spawnAsteroidFollow();
                spawnAsteroids();
            }
            if(t % 100 == 0){
                score += 1;    
            }
            
            if(t % powerUpSpawnTime == 0){
                spawnTripleShotPowerUp();
                //drawPowerUps(g);
                time =  System.currentTimeMillis();
            }
            /**
            if(!tPower.get(0).isAlive(time)){
                tPower.remove(0);
            }
            **/

            if(craft.isVisible()){
                String sc = "Player Score: " + score;
                String ad = "Asteroids Destroyed: " + asteroidsDestroyed;
                String lf = "Live(s) Left: " + life;
                Font small = new Font("Helvetica", Font.BOLD, 20);
                FontMetrics metr =  this.getFontMetrics(small);

                g.setColor(Color.white);
                g.setFont(small);
                g.drawString(sc, 20, 20);
                g.drawString(ad, 20, 40);
                g.drawString(lf, BOARD_LENGTH - 130 - lf.length(), 20);

            }
            if(craft.isVisible()){
                String sc = "TripleShots Left:  " + craft.getTripleShots();
                Font small = new Font("Helvetica", Font.BOLD, 20);
                FontMetrics metr =  this.getFontMetrics(small);

                g.setColor(Color.white);
                g.setFont(small);
                g.drawString(sc, 20, 60);
            }
        }
        else{
            String msg = "Game Over";
            String sc = ("Player Score: " + score);
            String ad = ("Asteroids Destroyed: " + asteroidsDestroyed);
            String retry = ("Press t to restart");
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics metr =  this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (BOARD_LENGTH - metr.stringWidth(msg)) / 2,
                BOARD_HEIGHT /2);
            g.drawString(sc, 20, 20);
            g.drawString(ad, 20, 40);
            g.drawString(retry, (BOARD_LENGTH - metr.stringWidth(retry)) / 2,
                BOARD_HEIGHT /2 + 60);

        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        //Iterator itA = asteroids.iterator();
        //Iterator itM = craft.getMissiles().iterator();

        craft.move();
        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) 
                m.move();
            else ms.remove(i);
        }

        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = (Asteroid) asteroids.get(i);
            if (a.isVisible()) 
                a.move();
            else asteroids.remove(i);
        }

        for (int i = 0; i < tPower.size(); i++) {
            TripleShotPowerUp t = (TripleShotPowerUp) tPower.get(i);
            if (t.isVisible()) 
                t.move();
            else
                tPower.remove(i);
        }

        checkCollisions();
        repaint();  
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
            if((e.equals(KeyEvent.VK_T)) && (!ingame)){
                //System.exit(0);
                new Game();
            }
        }
    }

}
