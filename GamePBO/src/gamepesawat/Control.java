package gamepesawat;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Control {
// private String craft = "E:\\Document\\Semester 3\\Semester 3\\PBO\\GamePBO\\src\\gamepesawat\\plane.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    private ArrayList<Bullet> Missiles;


    public Control() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	image = toolkit.getImage(getClass().getResource("/gamepesawat/plane.jpg"));

        width = image.getWidth(null);
        height = image.getHeight(null);
        Missiles = new ArrayList<Bullet>();
        visible = true;
        x = 40;
        y = 60;
    }
    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Bullet> getMissiles() {
        return Missiles;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

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
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }

    public void fire() {
        Missiles.add(new Bullet(x + 30, y + height/2 ));
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