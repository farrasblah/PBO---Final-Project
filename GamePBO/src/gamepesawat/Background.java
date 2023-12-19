package gamepesawat;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Background extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Timer timer;
    private Control ourplane;
    private ArrayList<Enemy> aliens;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    Image gambar;

    private int[][] pos = { 
        {2380, 29}, {2500, 59}, {1380, 89},
//        {780, 109}, {580, 139}, {680, 239}, 
//        {790, 259}, {760, 50}, {790, 150},
//        {980, 209}, {560, 45}, {510, 70},
//        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
 //       {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
    };

    public Background() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ingame = true;

        ourplane = new Control();
        initAliens();

        timer = new Timer(20, this);
        timer.start();
    }

    public final void initAliens() {
        aliens = new ArrayList<Enemy>();
        for (int i = 0; i < pos.length; i++) {
            aliens.add(new Enemy(pos[i][0], pos[i][1]));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D) g;

            if (ourplane.isVisible()) {
                g2d.drawImage(ourplane.getImage(), ourplane.getX(), ourplane.getY(), this);
            }

            ArrayList<Bullet> ms = ourplane.getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                Bullet m = (Bullet) ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Enemy a = (Enemy) aliens.get(i);
                if (a.isVisible()) {
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
                }
            }
            g2d.setColor(Color.red);
//            g2d.drawString("Aliens left: " + aliens.size(), 5, 15);
        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 50);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (aliens.isEmpty()) {
            ingame = false;
        }

        ArrayList<Bullet> ms = ourplane.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Bullet m = (Bullet) ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
                i--;
            }
        }
        for (int i = 0; i < aliens.size(); i++) {
            Enemy a = (Enemy) aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
        
        checkCollisions();
        ourplane.move();
        repaint();
    }

    public void checkCollisions() {
        Rectangle r3 = ourplane.getBounds();

        Iterator<Enemy> iterator = aliens.iterator();
        while (iterator.hasNext()) {
            Enemy a = iterator.next();
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                ourplane.setVisible(false);
                a.setVisible(false);
                ingame = false;
                showGameOverMessage();
            }
        }

        ArrayList<Bullet> ms = ourplane.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Bullet m = ms.get(i);

            Rectangle r1 = m.getBounds();

            iterator = aliens.iterator();
            while (iterator.hasNext()) {
                Enemy a = iterator.next();
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    a.setVisible(false);
                    // Tambahkan logika atau efek khusus di sini
                    // Contoh: Penanganan skor
                    // skor += 50; // Tambah skor 50 setiap kali musuh terkena peluru
                }
            }
        }
    }
    
    public void showGameOverMessage() {
        // Menampilkan pesan "Game Over" di tengah layar
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics metr = getFontMetrics(small);

        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

        g.dispose();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            ourplane.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ourplane.keyPressed(e);
        }
    }
}
