import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missile implements Data{
    
    private int x;
    private int y;
    private Image image;
    private int width, height;
    private boolean visible;
    
    public Missile(int x, int y)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("missile1.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }
    
    public Image getImage()
    {
        return image;    
    }
    
    public int getX(){
        return x;    
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;    
    }
    public boolean isVisible(){
        return visible;    
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
    public void move(){
        y -= MISSILE_SPEED;
        if(y < 0){
            visible = false;
        }
    }







}
