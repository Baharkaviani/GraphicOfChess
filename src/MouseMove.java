import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @since 17th May 2019
 * @author Bahar Kaviani
 */
class MouseMove implements MouseListener {
    private GraphicGround ground;
    private Square currentSquare;
    private Square king;
    private Player competitor, player;
    private boolean turn;

    MouseMove(GraphicGround ground, Square currentSquare, Player competitor, Player player, Square king, boolean turn){
        this.ground = ground;
        this.currentSquare = currentSquare;
        this.competitor = competitor;
        this.king = king;
        this.player = player;
        this.turn = turn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Square newSquare = ((Square)(e.getSource()));
        play(currentSquare, newSquare, ground, competitor, king);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground.getGround()[i][j].removeMouseListener(this);
            }
        }
        if (player.getPlayerPieces()[0].getColor().equals("white")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ground.getGround()[i][j].addMouseListener(new MouseClick(ground, player, competitor, !turn));
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ground.getGround()[i][j].addMouseListener(new MouseClick(ground, competitor, player, !turn));
                }
            }
        }
        if(ground.getColorForTurn() == Color.WHITE){
            ground.setColorForTurn(Color.BLACK);
        }
        else {
            ground.setColorForTurn(Color.WHITE);
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

    /**
     * find the current place and try to move the piece of that square to new place.
     * also set new piece to new square if it can, so if hit sth it will lose!
     * if the movement will put player in check condition it will play back and want another movement.
     * @param ground get the ground to find the current place and new place;
     */
    public String play(Square currentSquare, Square newSquare, GraphicGround ground, Player competitor, Square king){
        if(currentSquare.getMohre() == null){
            System.out.println("There is no piece to move! Try again.");
            return "false";
        }
        currentSquare.getMohre().findAllPossibleToGo(ground);
        Square poorPiece = ground.getSquare(newSquare.getRow(), newSquare.getColumn());
        boolean move = currentSquare.getMohre().move(newSquare);
        if(move){
            if(currentSquare.getMohre() instanceof King)
                king = newSquare;
            ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setMohre(currentSquare.getMohre());
            ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setIcon(currentSquare.getIcon());
            ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setMohre(null);
            ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setIcon(null);
            //play
            if(checkCondition(ground, competitor, king).equals("normal")){
                return "true";
            }
            //play back!
            else if(checkCondition(ground, competitor, king).equals("check")){
                System.out.println("You are check. Try another move!");
                newSquare.getMohre().moveBack(currentSquare);
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setMohre(newSquare.getMohre());
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setIcon(newSquare.getIcon());
                ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setMohre(poorPiece.getMohre());
                ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setIcon(poorPiece.getIcon());
                if(poorPiece != null)
                    poorPiece.getMohre().setLose(false);
                return "check. Can't move!";
            }
            //finish
            else{
                return "Check Mate";
            }
        }
        else
            return "false";
    }

    /**
     * check that is the player in normal or check or check Mate condition
     * @param competitor
     */
    public String checkCondition(GraphicGround ground, Player competitor, Square king){
        String condition = "normal";
        for (int i = 0; i < 16; i++) {
            //if the piece didn't lose
            if(!competitor.getPlayerPieces()[i].isLose()){
                competitor.getPlayerPieces()[i].findAllPossibleToGo(ground);
                for (Square Key: competitor.getPlayerPieces()[i].getPossibleToGo()) {
                    if(Key.equals(king)) {
                        condition = "check";
                        break;
                    }
                }
            }
            if(condition.equals("check"))
                break;
        }
        king.getMohre().findAllPossibleToGo(ground);
        String newCondition = "normal";
        for (int j = 0; j < king.getMohre().getPossibleToGo().size(); j++) {
            Square nextKing = king.getMohre().getPossibleToGo().get(j);
            for (int i = 0; i < 16; i++) {
                //if the piece didn't lose
                if(!competitor.getPlayerPieces()[i].isLose()){
                    competitor.getPlayerPieces()[i].findAllPossibleToGo(ground);
                    for (Square Key: competitor.getPlayerPieces()[i].getPossibleToGo()) {
                        //if the King can move it's not in checkMate condition
                        if(Key.equals(nextKing)) {
                            newCondition = "check";
                            break;
                        }
                        newCondition = "normal";
                    }
                }
            }
            if(newCondition.equals("normal"))
                break;
        }
        if(newCondition.equals("check"))
            condition = "checkMate";
        return condition;
    }
}