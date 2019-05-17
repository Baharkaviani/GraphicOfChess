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
    private Player player1, player2;
    private Square player1King, player2King;

    MouseMove(GraphicGround graphicGround, Player player1, Player player2){
        this.ground = graphicGround;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        while (true) {
            String play = "true";
            //player1
            player1King = ground.getSquare(0, 0);
            for (int i = 0; i < 16; i++) {
                if (player1.getPlayerPieces()[i] instanceof King) {
                    player1King = ground.getSquare(player1.getPlayerPieces()[i].getRow(), player1.getPlayerPieces()[i].getColumn());
                    break;
                }
            }
            //player2
            player1King = ground.getSquare(0, 0);
            for (int i = 0; i < 16; i++) {
                if (player2.getPlayerPieces()[i] instanceof King) {
                    player2King = ground.getSquare(player2.getPlayerPieces()[i].getRow(), player2.getPlayerPieces()[i].getColumn());
                    break;
                }
            }
            while (true){
                //player1
                Square currentSquare = ((Square)(e.getSource()));
                if(currentSquare.getMohre().getColor().equals("white")) {
                    currentSquare.addMouseMotionListener(new MyMouseMotionAdapter());

//                    play = player1.play(currentSquare, newSquare, ground, player2, player1King);
//                    if(play.equals("true")) {
//                        ground.printGround();
//                        break;
//                    }
//                    else if(play.equals("check. Can't move!"))
//                        ground.printGround();
//                    else if(play.equals("Check Mate")){
//                        System.out.println("White player lose!!");
//                        break;
//                    }
                }
                else
                    System.out.println("It's white turn");
            }
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
        boolean find = false;
        int row, column = 0;
        row = ((Square)(e.getSource())).getRow();
        column =  ((Square)(e.getSource())).getColumn();
        Square currentSquare = ((Square)(e.getSource()));
        if(currentSquare.getMohre() != null) {
            currentSquare.getMohre().findAllPossibleToGo(ground);
            for (Square key: currentSquare.getMohre().getPossibleToGo()) {
                key.setBorder(new LineBorder(Color.BLACK, 5));
            }
        }
    }

    public void play(){

    }
}