import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * Asteroid class
 * asteroid move in the down direction only
 * 
 * Implement comets later(move in a random diagonal direction)
 * 
 */
public class Asteroid implements Data{

    private boolean visible;
    private Image image;
    private int x;
    private int y;
    private boolean dying;
    private int dx;
    private int dy;
    private int width;
    private int height;
    public Asteroid(int xPos, int yPos) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ASTEROID));
        image = ii.getImage();
        visible = true;
        x = xPos;
        y = yPos;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public void dies() {
        visible = false;
    }
    public boolean isVisible() {
        return visible;
    }
    protected void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setImage(Image image){
        this.image = image;    
    }
    public Image getImage() {
        return image;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    /**
    public boolean isDying(){
        
    }
    **/
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);    
    }
    public void move()
    {
        y += ASTEROID_YSPEED;
    }
    
}
