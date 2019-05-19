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

    MouseClick(GraphicGround graphicGround, Player player1, Player player2){
        this.ground = graphicGround;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ground.makeTurnEmpty();
        if(ground.isTurn()) {
            System.out.println("..ground.isTurn is true.");
            //player1
            for (int i = 0; i < 16; i++) {
                if (player1.getPlayerPieces()[i] instanceof King) {
                    ground.setPlayer1King(ground.getSquare(player1.getPlayerPieces()[i].getRow(), player1.getPlayerPieces()[i].getColumn()));
                    break;
                }
            }
            if(!ground.isgClicked()){
                System.out.println("..ground.isgClicked is false.");
                ground.setCurrentSquare((Square)(e.getSource()));
                if(ground.getCurrentSquare().getMohre() != null){
                    System.out.println("..mohre isn't null.");
                    if(ground.getCurrentSquare().getMohre().getColor().equals("white")){
                        System.out.println("..color is white.");
                        ground.setgClicked(true);
                    }
                    else {
                        System.out.println("..color isn't white.");
                        ground.setTextForTurn("it's White turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    System.out.println("..mohre is null.");
                    ground.setgClicked(false);
                    ground.setCurrentSquare(null);
                }
            }
            else {
                System.out.println("..ground.isgClicked is true.");
                ground.setNewSquare((Square)(e.getSource()));
                if(ground.getNewSquare().getMohre() == null) {
                    String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player2, ground.getPlayer1King());
                    if (play.equals("true"))
                        ground.setColorForTurn(Color.BLACK);
                    ground.setTurn(false);
                    ground.setgClicked(false);
                }
                else{
                    if(!ground.getNewSquare().getMohre().getColor().equals(ground.getCurrentSquare().getMohre().getColor())) {
                        String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player2, ground.getPlayer1King());
                        if (play.equals("true")) {
                            ground.setColorForTurn(Color.BLACK);
                        }
                        ground.setTurn(false);
                        ground.setgClicked(false);
                    }
                    else {
                        ground.setCurrentSquare(ground.getNewSquare());
                        ground.setgClicked(true);
                    }
                }
            }
        }
        else {
            System.out.println("..ground.isTurn is false.");
            //player2
            ground.setColorForTurn(Color.BLACK);
            for (int i = 0; i < 16; i++) {
                if (player2.getPlayerPieces()[i] instanceof King) {
                    ground.setPlayer2King(ground.getSquare(player2.getPlayerPieces()[i].getRow(), player2.getPlayerPieces()[i].getColumn()));
                    break;
                }
            }
            if(!ground.isgClicked()){
                System.out.println("..ground.isgClicked is false.");
                ground.setCurrentSquare((Square)(e.getSource()));
                if(ground.getCurrentSquare().getMohre() != null){
                    System.out.println("..mohre isn't null.");
                    if(ground.getCurrentSquare().getMohre().getColor().equals("Black")){
                        System.out.println("..color is Black.");
                        ground.setgClicked(true);
                    }
                    else {
                        System.out.println("..color isn't Black.");
                        ground.setTextForTurn("it's Black turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    System.out.println("..mohre is null.");
                    ground.setgClicked(false);
                    ground.setCurrentSquare(null);
                }
            }
            else {
                System.out.println("..ground.isgClicked is true.");
                ground.setNewSquare((Square)(e.getSource()));
                if (ground.getNewSquare().getMohre() == null) {
                    String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player1, ground.getPlayer2King());
                    if (play.equals("true"))
                        ground.setColorForTurn(Color.WHITE);
                    ground.setTurn(true);
                    ground.setgClicked(false);
                }
                else {
                    if(!ground.getNewSquare().getMohre().getColor().equals(ground.getCurrentSquare().getMohre().getColor())) {
                        String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player1, ground.getPlayer2King());
                        if (play.equals("true")) {
                            ground.setColorForTurn(Color.WHITE);
                        }
                        ground.setTurn(true);
                        ground.setgClicked(false);
                    }
                    else {
                        ground.setgClicked(true);
                        ground.setCurrentSquare(ground.getNewSquare());
                    }
                }
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
    /**
     * find the current place and try to move the piece of that square to new place.
     * also set new piece to new square if it can, so if hit sth it will lose!
     * if the movement will put player in check condition it will play back and want another movement.
     */
    String play(Square currentSquare, Square newSquare, Player competitor, Square king){
        if(currentSquare.getMohre() == null){
            ground.setTextForTurn("There is no piece to move! Try again.");
            return "false";
        }
        currentSquare.getMohre().findAllPossibleToGo(ground);
        Square poorPiece = new Square(newSquare.getRow(), newSquare.getColumn(), newSquare.getMohre());
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
                if(poorPiece.getMohre() != null) {
                    ground.setLosePieces(poorPiece.getMohre());
                    for (Square key : poorPiece.getMohre().getPossibleToGo()) {
                        key.setBorder(new LineBorder(Color.BLACK, 5));
                    }
                }
                return "true";
            }
            //play back!
            else if(checkCondition(ground, competitor, king).equals("check")){
                ground.setTextForTurn("You are check. Try another move!");
                newSquare.getMohre().moveBack(currentSquare);
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setMohre(newSquare.getMohre());
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setIcon(newSquare.getIcon());
                ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setMohre(poorPiece.getMohre());
                ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setIcon(poorPiece.getIcon());
                if(poorPiece.getMohre() != null) {
                    poorPiece.getMohre().setLose(false);
                }
                return "check. Can't move!";
            }
            //finish
            else{
                ground.setTextForTurn("Check Mate");
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