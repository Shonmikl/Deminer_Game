import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
    private JPanel panel;
    private final int IMAGE_SIZE = 50;
    private final int COLUMNS = 9;
    private final int ROWS = 9;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        Ranges.setSize(COLUMNS, ROWS);
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCoords()) {
                    g.drawImage((Image) Box.BOMB.image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
            setIconImage(getImage("icon"));
        }
    }

    private Image getImage (String name) {
        String fileName = "source/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(fileName);
        // required  ImageIcon icon = new ImageIcon(getClass().getResource(fileName)); ---> but doesn't work //
        return icon.getImage();
    }
}