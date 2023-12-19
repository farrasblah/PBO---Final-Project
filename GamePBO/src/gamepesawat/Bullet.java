package gamepesawat;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Bullet {
    private int x, y;
    private Image image;
    private boolean visible;
    private int width, height;

    private final int BOARD_WIDTH = 600;
    private final int MISSILE_SPEED = 4;
    private final int DELAY = 10; 
    
    private Timer timer;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(getClass().getResource("/gamepesawat/laser.jpg"));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        timer.start();
    }

    public Image getImage() {
        return image;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
            timer.stop();
        }
    }
}