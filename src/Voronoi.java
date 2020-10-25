import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Voronoi extends JFrame {
    private static BufferedImage I;
    private Voronoi() {
        super("Voronoi Diagram");
        int size = 1000;
        setBounds(0, 0, size, size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int n;
        Random rand = new Random();
        I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        int cells = 10;
        int[] px = new int[cells];
        int[] py = new int[cells];
        int[] color = new int[cells];
        for (int i = 0; i < cells; i++) {
            px[i] = rand.nextInt(size);
            py[i] = rand.nextInt(size);
            color[i] = rand.nextInt(16777215);
        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                n = 0;
                for (byte i = 0; i < cells; i++) {
                    if (distance(px[i], x, py[i], y) < distance(px[n], x, py[n], y)) {
                        n = i;
                    }
                }
                I.setRGB(x, y, color[n]);
            }
        }
        Graphics2D g = I.createGraphics();
        g.setColor(Color.BLACK);
        for (int i = 0; i < cells; i++) {
            g.fill(new Ellipse2D.Double(px[i] - 2.5, py[i] - 2.5, 5, 5));
        }
        try {
            ImageIO.write(I, "png", new File("voronoi.png"));
        } catch (IOException ignored) {

        }
    }
    private static double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    public static void main(String[] args) {
        new Voronoi().setVisible(true);
    }
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
}