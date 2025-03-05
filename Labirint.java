package Labirint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Labirint extends JPanel implements KeyListener {
    private int playerX = 30, playerY = 30; // Start position
    private final int finalX = 350, finalY = 350; // End position
    private final int playerSize = 20;
    private final int mazeSize = 400;

    private Rectangle[] walls = {
    	    // Margini exterioare
    	    new Rectangle(0, 0, mazeSize, 5),     // Top
    	    new Rectangle(0, 0, 5, mazeSize),     // Left
    	    new Rectangle(0, mazeSize - 5, mazeSize, 5),  // Bottom
    	    new Rectangle(mazeSize - 5, 0, 5, mazeSize),  // Right

    	    // Pereți interiori existenți
    	    new Rectangle(50, 0, 5, 100),
    	    new Rectangle(100, 50, 100, 5),
    	    new Rectangle(200, 0, 5, 100),
    	    new Rectangle(250, 50, 100, 5),
    	    new Rectangle(350, 0, 5, 100),

    	    new Rectangle(50, 100, 100, 5),
    	    new Rectangle(200, 100, 100, 5),
    	    new Rectangle(100, 150, 5, 100),
    	    new Rectangle(250, 150, 5, 100),

    	    new Rectangle(0, 200, 50, 5),
    	    new Rectangle(150, 200, 100, 5),
    	    new Rectangle(300, 200, 50, 5),
    	    new Rectangle(50, 250, 5, 100),
    	    new Rectangle(100, 250, 150, 5),

    	    new Rectangle(250, 300, 50, 5),
    	    new Rectangle(150, 350, 5, 50),
    	    new Rectangle(200, 350, 100, 5),

    	    // Pereți noi adăugați pentru un traseu unic
    	    new Rectangle(100, 100, 5, 50),
    	    new Rectangle(200, 150, 5, 50),
    	    new Rectangle(300, 50, 5, 50),
    	    new Rectangle(150, 250, 5, 50),
    	    new Rectangle(250, 250, 5, 50),
    	    new Rectangle(50, 300, 100, 5),
    	    new Rectangle(200, 300, 5, 50),
    	    new Rectangle(300, 300, 5, 50)
    	};
    



    private ArrayList<Confetti> confettiList = new ArrayList<>();
    private Timer confettiTimer;

    public Labirint() {
        this.setFocusable(true);
        this.addKeyListener(this);

        // Initializare confetti timer
        confettiTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConfetti();
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.PINK, getWidth(), getHeight(), Color.WHITE);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Desenează pereții labirintului
        g.setColor(Color.BLACK);
        for (Rectangle wall : walls) {
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
        }

        // Desenează inimi
        g.setColor(Color.RED);
        drawHeart(g, finalX, finalY);
        drawHeart(g, playerX, playerY);

        // Desenează confetti
        for (Confetti confetti : confettiList) {
            g.setColor(confetti.color);
            g.fillRect(confetti.x, confetti.y, confetti.size, confetti.size);
        }
    }

    private void drawHeart(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Path2D heart = new Path2D.Double();
        int size = 15;

        heart.moveTo(x, y + size * 0.3);
        heart.curveTo(
            x - size * 0.5, y - size * 0.3,
            x - size, y + size * 0.3,
            x, y + size
        );
        heart.curveTo(
            x + size, y + size * 0.3,
            x + size * 0.5, y - size * 0.3,
            x, y + size * 0.3
        );

        g2.fill(heart);
    }

    public void playWinSound() {
        try {
            File soundFile = new File("src/congrats.wav");
            if (!soundFile.exists()) {
                System.err.println("Could not find congrats.wav in src!");
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        int newX = playerX, newY = playerY;
        int step = 5;

        if (e.getKeyCode() == KeyEvent.VK_LEFT) newX -= step;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) newX += step;
        if (e.getKeyCode() == KeyEvent.VK_UP) newY -= step;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) newY += step;

        // Verificare coliziune
        Rectangle playerRect = new Rectangle(newX - playerSize/2, newY - playerSize/2, playerSize, playerSize);
        boolean canMove = true;
        for (Rectangle wall : walls) {
            if (playerRect.intersects(wall)) {
                canMove = false;
                break;
            }
        }

        // Actualizare poziție dacă nu e coliziune
        if (canMove) {
            playerX = newX;
            playerY = newY;
        }
        
        // Verificare finalizare labirint
        if (Math.abs(playerX - finalX) < playerSize && Math.abs(playerY - finalY) < playerSize) {
            playWinSound();
         // Adăugăm confetti
            createConfettiEffect();

            // Începem animația de confetti
            confettiTimer.start();
            JOptionPane.showMessageDialog(this,
                "Mamă, iubirea mea pentru tine e ca un labirint: oricât de multe obstacole ar fi, mereu găsesc drumul spre tine. ❤️",
                "Final", JOptionPane.INFORMATION_MESSAGE);
            
            
        }

        repaint();
    }

    private void createConfettiEffect() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(100); // Începe de sus
            int size = rand.nextInt(10) + 5; // Dimensiuni aleatorii
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); // Culoare aleatorie
            confettiList.add(new Confetti(x, y, size, color));
        }
    }

    private void updateConfetti() {
        ArrayList<Confetti> toRemove = new ArrayList<>();
        for (Confetti confetti : confettiList) {
            confetti.y += 5; // Căderea confetti-ului
            if (confetti.y > getHeight()) {
                toRemove.add(confetti); // Dacă confetti-ul a ieșit de pe ecran, îl eliminăm
            }
        }
        confettiList.removeAll(toRemove);
        if (confettiList.isEmpty()) {
            confettiTimer.stop(); // Oprim animația după ce toate confetti-urile au căzut
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Labirint pentru mama");
        Labirint game = new Labirint();
        frame.add(game);
        frame.setSize(420, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class Confetti {
    int x, y, size;
    Color color;

    Confetti(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
}
