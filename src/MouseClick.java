import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
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
            //player1
            for (int i = 0; i < 16; i++) {
                if (player1.getPlayerPieces()[i] instanceof King) {
                    ground.setPlayer1King(ground.getSquare(player1.getPlayerPieces()[i].getRow(), player1.getPlayerPieces()[i].getColumn()));
                    break;
                }
            }
            if(!ground.isgClicked()){
                ground.setCurrentSquare((Square)(e.getSource()));
                if(ground.getCurrentSquare().getMohre() != null){
                    if(ground.getCurrentSquare().getMohre().getColor().equals("white")){
                        ground.setgClicked(true);
                    }
                    else {
                        ground.setTextForTurn("it's White turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    ground.setgClicked(false);
                    ground.setCurrentSquare(null);
                }
            }
            else {
                ground.setNewSquare((Square)(e.getSource()));
                if(ground.getNewSquare().getMohre() == null) {
                    String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player2, ground.getPlayer1King());
                    if (play.equals("true")) {
                        if(ground.getNewSquare().getMohre() instanceof Pawn){
                            if(ground.getNewSquare().getRow() == Row.A.ordinal())
                                askChangePawn(ground.getNewSquare());
                        }
                        ground.setColorForTurn(Color.BLACK);
                        ground.setTurn(false);
                    }
                    ground.setgClicked(false);
                }
                else{
                    if(!ground.getNewSquare().getMohre().getColor().equals(ground.getCurrentSquare().getMohre().getColor())) {
                        String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player2, ground.getPlayer1King());
                        if (play.equals("true")) {
                            if(ground.getNewSquare().getMohre() instanceof Pawn){
                                if(ground.getNewSquare().getRow() == Row.A.ordinal())
                                    askChangePawn(ground.getNewSquare());
                            }
                            ground.setColorForTurn(Color.BLACK);
                            ground.setTurn(false);
                        }
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
            //player2
            ground.setColorForTurn(Color.BLACK);
            for (int i = 0; i < 16; i++) {
                if (player2.getPlayerPieces()[i] instanceof King) {
                    ground.setPlayer2King(ground.getSquare(player2.getPlayerPieces()[i].getRow(), player2.getPlayerPieces()[i].getColumn()));
                    break;
                }
            }
            if(!ground.isgClicked()){
                ground.setCurrentSquare((Square)(e.getSource()));
                if(ground.getCurrentSquare().getMohre() != null){
                    if(ground.getCurrentSquare().getMohre().getColor().equals("Black")){
                        ground.setgClicked(true);
                    }
                    else {
                        ground.setTextForTurn("it's Black turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    ground.setgClicked(false);
                    ground.setCurrentSquare(null);
                }
            }
            else {
                ground.setNewSquare((Square)(e.getSource()));
                if (ground.getNewSquare().getMohre() == null) {
                    String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player1, ground.getPlayer2King());
                    if (play.equals("true")) {
                        if(ground.getNewSquare().getMohre() instanceof Pawn) {
                            if (ground.getNewSquare().getRow() == Row.H.ordinal())
                                askChangePawn(ground.getNewSquare());
                        }
                        ground.setColorForTurn(Color.WHITE);
                        ground.setTurn(true);
                    }
                    ground.setgClicked(false);
                }
                else {
                    if(!ground.getNewSquare().getMohre().getColor().equals(ground.getCurrentSquare().getMohre().getColor())) {
                        String play = play(ground.getCurrentSquare(), ground.getNewSquare(), player1, ground.getPlayer2King());
                        if (play.equals("true")) {
                            if(ground.getNewSquare().getMohre() instanceof Pawn) {
                                if (ground.getNewSquare().getRow() == Row.H.ordinal())
                                    askChangePawn(ground.getNewSquare());
                            }
                            ground.setColorForTurn(Color.WHITE);
                            ground.setTurn(true);
                        }
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
        ChessPieces poorPiece = newSquare.getMohre();
        if(checkCondition(ground, competitor, king).equals("checkMate")){
                ground.setTextForTurn("Check Mate");
                return "Check Mate";
        }
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
                if(poorPiece != null) {
                    ground.setLosePieces(poorPiece);
                    for (Square key : poorPiece.getPossibleToGo()) {
                        key.setBorder(new LineBorder(Color.BLACK, 5));
                    }
                }
                return "true";
            }
            //play back!
            else if(checkCondition(ground, competitor, king).equals("check")){
                ground.setTextForTurn("check. Try another move!");
                newSquare.getMohre().moveBack(currentSquare);
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setMohre(newSquare.getMohre());
                ground.getSquare(currentSquare.getRow(), currentSquare.getColumn()).setIcon(newSquare.getIcon());
                ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setMohre(poorPiece);
                if(poorPiece != null)
                    poorPiece.setImage(ground.getSquare(newSquare.getRow(), newSquare.getColumn()));
                else
                    ground.getSquare(newSquare.getRow(), newSquare.getColumn()).setIcon(null);
                if(poorPiece != null) {
                    poorPiece.setLose(false);
                }
                return "check. Can't move!";
            }
//            //finish
//            else if(checkCondition(ground, competitor, king).equals("checkMate")){
//                ground.setTextForTurn("Check Mate");
//                return "Check Mate";
//            }
            else
                return "false";
        }
        else
            return "false";
    }

    /**
     * check that is the player in normal or check or check Mate condition
     * @param ground the buttons
     * @param competitor player who will play next term
     * @param king the place of king piece
     * @return true if king is in check condition
     */
    String checkCondition(GraphicGround ground, Player competitor, Square king){
        String condition = "normal";
        boolean condition1 = checkCheck(ground, competitor, king);
        boolean condition2 = false;
        if(condition1)
            condition = "check";
        king.getMohre().findAllPossibleToGo(ground);
        for (int j = 0; j < king.getMohre().getPossibleToGo().size(); j++) {
            Square nextKing = king.getMohre().getPossibleToGo().get(j);
            condition2 = checkCheck(ground, competitor, nextKing);
            if(!condition2){
                System.out.println("next king is not check " + nextKing.getRow() + " " + nextKing.getColumn());
                break;
            }
        }
        if(condition2) {
            System.out.println("checkMate");
            condition = "checkMate";
        }
        return condition;
    }

    /**
     * return true if king is in check condition
     * @param ground the buttons
     * @param competitor player who will play next term
     * @param king the place of king piece
     * @return true if king is in check condition
     */
    boolean checkCheck(GraphicGround ground, Player competitor, Square king){
        for (int i = 0; i < 16; i++) {
            //if the piece didn't lose
            if(!competitor.getPlayerPieces()[i].isLose()){
                competitor.getPlayerPieces()[i].findAllPossibleToGo(ground);
                for (Square Key: competitor.getPlayerPieces()[i].getPossibleToGo()) {
                    if(Key.equals(king)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void askChangePawn(Square pawn){
        JFrame choice = new JFrame("Which one do you need?");
        choice.setAlwaysOnTop(true);
        choice.setLayout(new GridLayout());
        choice.setSize(500, 500);
        JButton Queen = new JButton("Queen");
        JButton Bishop = new JButton("Bishop");
        JButton Rook = new JButton("Rook");
        JButton Knight = new JButton("Knight");
        choice.add(Queen);
        Queen.addActionListener(new MyActionListener(choice, pawn, Queen, Bishop, Rook, Knight));
        choice.add(Bishop);
        Bishop.addActionListener(new MyActionListener(choice, pawn, Queen, Bishop, Rook, Knight));
        choice.add(Rook);
        Rook.addActionListener(new MyActionListener(choice, pawn, Queen, Bishop, Rook, Knight));
        choice.add(Knight);
        Knight.addActionListener(new MyActionListener(choice, pawn, Queen, Bishop, Rook, Knight));
        choice.setVisible(true);
    }
}

class MyActionListener implements java.awt.event.ActionListener{
    JFrame frame;
    Square pawn;
    JButton Queen, Bishop, Rook, Knight;

    MyActionListener(JFrame frame, Square pawn, JButton Queen, JButton Bishop, JButton Rook, JButton Knight){
        this.frame = frame;
        this.pawn = pawn;
        this.Queen = Queen;
        this.Bishop = Bishop;
        this.Rook = Rook;
        this.Knight = Knight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)(e.getSource())).getText().equals("Queen")){
            Queen queen = new Queen(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(queen);
            pawn.getMohre().setImage(pawn);
        }
        else if((((JButton)(e.getSource())).getText().equals("Bishop"))){
            Bishop bishop = new Bishop(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(bishop);
            pawn.getMohre().setImage(pawn);
        }
        else if((((JButton)(e.getSource())).getText().equals("Rook"))){
            Rook rook = new Rook(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(rook);
            pawn.getMohre().setImage(pawn);
        }
        else if((((JButton)(e.getSource())).getText().equals("Knight "))){
            Knight knight = new Knight(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(knight);
            pawn.getMohre().setImage(pawn);
        }
        frame.dispose();
    }
}