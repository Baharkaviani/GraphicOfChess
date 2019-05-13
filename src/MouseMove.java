import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseMove implements MouseListener {
    private Square[][] ground;

    public MouseMove(Square[][] ground){
        this.ground = ground;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean find = false;
        int row, column = 0;
        row = ((Square)(e.getSource())).getRow();
        column =  ((Square)(e.getSource())).getColumn();
        System.out.println("i = " + row + " j = " + column);
        for (int k = row - 1; k >= 0; k--) {
            ground[k][column].setBackground(new Color(0xF04624));
        }
        for (int k = row + 1; k < 8; k++) {
            ground[k][column].setBackground(new Color(0xF04624));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}