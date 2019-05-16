import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * check the event
 * @author Bahar Kaviani
 */
class MouseMove implements MouseListener {
    private Square[][] ground;

    public MouseMove(Square[][] ground){
        this.ground = ground;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        boolean find = false;
        int row, column = 0;
        row = ((Square)(e.getSource())).getRow();
        column =  ((Square)(e.getSource())).getColumn();
        for (int k = row - 1; k >= 0; k--) {
            ground[k][column].setBorder(new LineBorder(Color.RED, 5));
        }
        for (int k = row + 1; k < 8; k++) {
            ground[k][column].setBorder(new LineBorder(Color.RED, 5));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        boolean find = false;
        int row, column = 0;
        row = ((Square)(e.getSource())).getRow();
        column =  ((Square)(e.getSource())).getColumn();
        for (int k = row - 1; k >= 0; k--) {
            ground[k][column].setBorder(new LineBorder(Color.BLACK, 5));
        }
        for (int k = row + 1; k < 8; k++) {
            ground[k][column].setBorder(new LineBorder(Color.BLACK, 5));
        }
    }
}