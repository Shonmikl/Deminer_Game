import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;
import sweeper.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class JavaSweeper extends JFrame {
    private JPanel panel;
    private JLabel label;

    private final int IMAGE_SIZE = 50;
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final Game GAME;
    private final int BOMBS = 1;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        GAME = new Game(COLUMNS, ROWS, BOMBS);
        GAME.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("It is The Game time!");
        Font font = new Font("Comics", Font.BOLD, 20);
        label.setFont(font);
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) GAME.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> GAME.pressLeftButton(coord);
                    case MouseEvent.BUTTON3 -> GAME.pressRightButton(coord);
                    case MouseEvent.BUTTON2 -> GAME.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

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

    private Image getImage(String name) {
        String fileName = "/img/" + name + ".png";
        //ImageIcon icon = new ImageIcon(fileName);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));
        return icon.getImage();
    }

    private String getMessage() {
        switch (GAME.getState()) {
            case BOMBED:
                return "!!You lose!!";
            case WINNER:
                return "!!You won!!";
            case PLAYED:
            default:
                if (GAME.getTotalFlagged() == 0) {
                    return "Welcome to The Game";
                } else return "Flagged!!! " + GAME.getTotalFlagged() + " of "
                        + GAME.getTotalBombs() + " bombs.";
        }
    }
}