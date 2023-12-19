package gamepesawat;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
//import java.net.URL;
//import java.util.Objects;

public class Enemy {
    //private URL control;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public Enemy(int x, int y) {
    	try {
    		Toolkit toolkit = Toolkit.getDefaultToolkit();
    		image = toolkit.getImage(getClass().getResource("/gamepesawat/musuh.jpg"));

    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (x < 0) {
            x = 600;
        }
        x -= 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}