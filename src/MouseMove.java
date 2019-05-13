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

    }

    @Override
    public void mousePressed(MouseEvent e) {
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
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0)
                    ground[i][j].setBackground(new Color(0x000000));
                else
                    ground[i][j].setBackground(new Color(0xFFFFFF));
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}