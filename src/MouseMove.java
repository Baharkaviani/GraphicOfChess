import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * check the event
 * @author Bahar Kaviani
 */
class MouseMove implements MouseListener {
    private GraphicGround ground;

    MouseMove(GraphicGround graphicGround){
        this.ground = graphicGround;
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
        Square currentSquare = ((Square)(e.getSource()));
        if(currentSquare.getMohre() != null) {
            currentSquare.getMohre().findAllPossibleToGo(ground);
            for (Square key: currentSquare.getMohre().getPossibleToGo()) {
                key.setBorder(new LineBorder(Color.RED, 5));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground.getGround()[i][j].setBorder(new LineBorder(Color.BLACK, 5));
            }
        }
    }
}