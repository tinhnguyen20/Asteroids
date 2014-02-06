import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
/**
 * This class implements the key stroke functions
 * Up, down, left, right
 * To move the ship (craft)
 * 
 * 
 */
public class Player  implements Data{
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width, height;
    private Image image;
    private ArrayList<Missile> missiles;
    private boolean visible;
    private int tripleShots;
    

    public Player() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(SHIP));
        image = ii.getImage();
        x = BOARD_LENGTH/2;
        y = BOARD_HEIGHT - 100;
        missiles = new ArrayList<Missile>();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible =  true;
        tripleShots = 10; 
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDX(){
        return dx;    
    }

    public ArrayList getMissiles(){
        return missiles;    
    }

    public void shoot(){
        missiles.add(new Missile(x + width/2, y));    
    }

    public void tripleShot(){
        missiles.add(new Missile(x + width/2, y));
        missiles.add(new Missile(x + width/2 + 30, y));
        missiles.add(new Missile(x + width/2 - 30, y));

    }

    public int getTripleShots(){
        return tripleShots;   
    }

    public void setTripleShots(int x){
        tripleShots += x; 
    }

    public boolean isVisible(){
        return visible;    
    }

    public void setVisible(boolean x){
        visible =  x;    
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(visible){
            if(key == KeyEvent.VK_SPACE){
                shoot();
            }

            if(key == KeyEvent.VK_C && tripleShots > 0){
                tripleShot();  
                tripleShots--;
            }

            if (key == KeyEvent.VK_LEFT) {
                dx = -1;
            }

            if (key == KeyEvent.VK_RIGHT) {
                dx = 1;
            }

            if (key == KeyEvent.VK_UP) {
                dy = -1;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy = 1;
            }
        }
        else{
            if(key == KeyEvent.VK_T){
                
                new Game();
                
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

}
