import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * check the event
 * @author Bahar Kaviani
 */
class MouseClick implements MouseListener {
    private GraphicGround ground;
    private Player player1, player2;
    private Square player1King, player2King;
    private boolean turn, clicked;

    MouseClick(GraphicGround graphicGround, Player player1, Player player2, boolean turn){
        this.ground = graphicGround;
        this.player1 = player1;
        this.player2 = player2;
        this.turn = turn;
        clicked = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ground.makeTurnEmpty();
        if(turn) {
            //player1
            ground.setColorForTurn(Color.WHITE);
            player1King = ground.getSquare(0, 0);
            for (int i = 0; i < 16; i++) {
                if (player1.getPlayerPieces()[i] instanceof King) {
                    player1King = ground.getSquare(player1.getPlayerPieces()[i].getRow(), player1.getPlayerPieces()[i].getColumn());
                    break;
                }
            }
            Square currentSquare = ((Square) (e.getSource()));
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ground.getGround()[i][j].removeMouseListener(this);
                }
            }
            if (currentSquare.getMohre() != null) {
                if (currentSquare.getMohre().getColor().equals("white")) {
                    currentSquare.getMohre().findAllPossibleToGo(ground);
                    clicked = true;
                    for (Square key: currentSquare.getMohre().getPossibleToGo()) {
                        key.addMouseListener(new MouseMove(ground, currentSquare, player2, player1, player1King, turn));
                    }
                }
                else {
                    ground.setTextForTurn("It's white turn");
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            ground.getGround()[i][j].addMouseListener(new MouseClick(ground, player1, player2, true));
                        }
                    }
                }
            }
            else {
                if(clicked) {
                    ground.setTextForTurn("There is no piece to move! Try again.");
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            ground.getGround()[i][j].addMouseListener(new MouseClick(ground, player1, player2, true));
                        }
                    }
                }
            }
            clicked = false;
//            ground.setColorForTurn(new Color(0));
        }
        else {
            //player2
            ground.setColorForTurn(Color.BLACK);
            player2King = ground.getSquare(0, 0);
            for (int i = 0; i < 16; i++) {
                if (player2.getPlayerPieces()[i] instanceof King) {
                    player2King = ground.getSquare(player2.getPlayerPieces()[i].getRow(), player2.getPlayerPieces()[i].getColumn());
                    break;
                }
            }
            Square currentSquare = ((Square)(e.getSource()));
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ground.getGround()[i][j].removeMouseListener(this);
                }
            }
            if (currentSquare.getMohre() != null) {
                if (currentSquare.getMohre().getColor().equals("Black")) {
                    currentSquare.getMohre().findAllPossibleToGo(ground);
                    clicked = true;
                    for (Square key: currentSquare.getMohre().getPossibleToGo()) {
                        key.addMouseListener(new MouseMove(ground, currentSquare, player1, player2, player2King, turn));
                    }
                }
                else {
                    ground.setTextForTurn("It's black turn");
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            ground.getGround()[i][j].addMouseListener(new MouseClick(ground, player1, player2, false));
                        }
                    }
                }
            }
            else {
                if(!clicked) {
                    ground.setTextForTurn("There is no piece to move! Try again.");
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            ground.getGround()[i][j].addMouseListener(new MouseClick(ground, player1, player2, true));
                        }
                    }
                }
            }
            clicked = false;
//            ground.setColorForTurn(new Color(0xFFFFFF));
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
        Square currentSquare = ((Square)(e.getSource()));
        if(currentSquare.getMohre() != null) {
            if(!currentSquare.getMohre().isLose()) {
                currentSquare.getMohre().findAllPossibleToGo(ground);
                for (Square key : currentSquare.getMohre().getPossibleToGo()) {
                    key.setBorder(new LineBorder(Color.RED, 5));
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Square currentSquare = ((Square)(e.getSource()));
        if(currentSquare.getMohre() != null) {
            if(!currentSquare.getMohre().isLose()) {
                currentSquare.getMohre().findAllPossibleToGo(ground);
                for (Square key : currentSquare.getMohre().getPossibleToGo()) {
                    key.setBorder(new LineBorder(Color.BLACK, 5));
                }
            }
        }
    }

    public void play(){

    }
}