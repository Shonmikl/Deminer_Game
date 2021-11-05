import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import sweeper.Box;
import sweeper.Coord;

public class JavaSweeper extends JFrame {
    private JPanel panel;
    private final int IMAGE_SIZE = 50;
    private final int COLUMNS = 15;
    private final int ROWS = 1;


    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Box box: Box.values()) {
                    Coord coord = new Coord(box.ordinal(), 0);
                    g.drawImage((Image) box.image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(COLUMNS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage (String name) {
        String fileName = "source/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(fileName);
        // required  ImageIcon icon = new ImageIcon(getClass().getResource(fileName)); ---> but doesn't work //
        return icon.getImage();
    }
}