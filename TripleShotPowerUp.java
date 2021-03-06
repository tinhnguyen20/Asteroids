import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 * Power up moves like Asteroid
 * Moves down
 * 
 * Incentive for Player to go to different places
 */
public class TripleShotPowerUp implements Data{
    private int x,y;
    private Image image;
    private int width, height;
    private boolean visible;
    private int upper_bound = BOARD_HEIGHT/3;
    private int lower_bound = (BOARD_HEIGHT * 2)/ 3;
    private long timeLimit;
    
    public TripleShotPowerUp(int xPos, int yPos){
        ImageIcon ii = new ImageIcon(this.getClass().getResource("tripleShotPowerUp.png"));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        y = yPos;//(int)(Math.random() * (upper_bound - lower_bound)) + lower_bound;
        x = xPos;//(int)(Math.random() * BOARD_LENGTH);
        timeLimit =  System.currentTimeMillis() + 5000;

    }
    public boolean isAlive(long t){
        if(timeLimit <= t){
            visible = false;
            return false;
        }
        return true;
    }
    
    public void dies(){
        visible = false;    
    }

    public int getX(){
        return x;    
    }

    public int getY(){
        return y;    
    }
    
    

    public boolean isVisible(){
        return visible;    
    }

    public void setVisible(boolean visible){
        this.visible = visible;    
    }

    public Image getImage(){
        return image;    
    }
    
    public void move(){
        y += 1;    
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);    
    }
}
