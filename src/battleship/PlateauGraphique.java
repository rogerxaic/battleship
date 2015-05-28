package battleship;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


/**
 *
 * @author Roger
 */
public final class PlateauGraphique extends jcomponent implements runnable, mouselistener {

    public PlateauGraphique() {
        this(null);
    }

    public PlateauGraphique(Partie partie) {
        frame = new jframe("Bataille Navale");
        label = new jlabel("\u2026", 0);
        frame.setDefaultCloseOperation(0);
        frame.getContentPane().add(label, "South");
        frame.getContentPane().add(this, "Center");
        addMouseListener(this);
        this.partie = partie;
    }

    public void run() {
        frame.setVisible(true);
        repaint();
    }

    public void afficher(int plateau[][], string texte) {
        width = 0;
        for (int i = 0; i < plateau.length; i++) {
            width = math.max(width, plateau[i].length);
        }
        if (board == null) {
            frame.setSize(width * 25, plateau.length * 25);
            frame.setLocationRelativeTo(null);
        }
        board = plateau;
        if ("".equals(texte)) {
            label.setText("\u2026");
        } else {
            label.setText(texte);
        }
        swingutilities.invokeLater(this);
    }

    public void paintComponent(graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        int h = getHeight() / board.length;
        int w = getWidth() / width;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] >= 0) {
                    g.setColor(colors[board[i][j]]);
                    g.fillRect(j * w, i * h, w, h);
                    g.setColor(color.BLACK);
                    g.drawRect(j * w, i * h, w, h);
                }
            }
        }
    }

    public void mouseClicked(mouseevent event) {
        if (partie == null) {
            return;
        } else {
            int x = event.getX() / (getWidth() / width);
            int y = event.getY() / (getHeight() / board.length);
            partie.coup(x, y);
            return;
        }
    }

    public void mouseEntered(mouseevent mouseevent) {
    }

    public void mouseExited(mouseevent mouseevent) {
    }

    public void mousePressed(mouseevent mouseevent) {
    }

    public void mouseReleased(mouseevent mouseevent) {
    }
    private static color colors[];
    private final Partie partie;
    private final jlabel label;
    private final jframe frame;
    private int board[][];
    private int width;

    static {
        colors = (new color[]{color.CYAN, color.WHITE, color.RED});
    }
}
