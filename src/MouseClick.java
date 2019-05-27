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

    /**
     * check whose turn is it?
     * main of play
     * @param e information of e which clicked
     */
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
                        ground.setTextForTurn("It's White turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    ground.setTextForTurn("There is no piece");
                    ground.setTextForTurn("to move!");
                    ground.setTextForTurn("Try again.");
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
                    else
                        ground.setTextForTurn("Try again");
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
                        else
                            ground.setTextForTurn("Try again");
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
                        ground.setTextForTurn("It's Black turn!");
                        ground.setgClicked(false);
                        ground.setCurrentSquare(null);
                    }
                }
                else {
                    ground.setTextForTurn("There is no piece");
                    ground.setTextForTurn("to move!");
                    ground.setTextForTurn("Try again.");
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
                    else
                        ground.setTextForTurn("Try again");
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
                        else
                            ground.setTextForTurn("Try again");
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

    /**
     * draw a lineBorder for the Squares that e's piece can go.
     * @param e information of e which mouseEntered
     */
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

    /**
     * remove the lineBorder for the Squares that e's piece can go.
     * @param e information of e which mouseExited
     */
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
     * @param currentSquare the first clicked button
     * @param newSquare the second clicked button
     * @param competitor the other player that will play in next turn
     * @param king the place of king for this player
     * @return String 'false' if it did't do anything. 'true' if it can play and move the piece.
     * 'check. Can't move!' if the piece can't move. 'Check Mate' if the player lost.
     */
    private String play(Square currentSquare, Square newSquare, Player competitor, Square king){
        currentSquare.getMohre().findAllPossibleToGo(ground);
        ChessPieces poorPiece = newSquare.getMohre();
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
                ground.setTextForTurn("check");
                ground.setTextForTurn("Try another move!");
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
            //finish
            else if(checkCondition(ground, competitor, king).equals("checkMate")){
                ground.setTextForTurn("Check Mate");
                return "Check Mate";
            }
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
    private String checkCondition(GraphicGround ground, Player competitor, Square king){
        String condition = "normal";
        boolean condition1 = checkCheck(ground, competitor, king);
        boolean condition2 = false;
        if(condition1)
            condition = "check";
        else
            return condition;
        king.getMohre().findAllPossibleToGo(ground);
        for (int j = 0; j < king.getMohre().getPossibleToGo().size(); j++) {
            Square nextKing = king.getMohre().getPossibleToGo().get(j);
            condition2 = checkCheck(ground, competitor, nextKing);
            if(!condition2){
                break;
            }
        }
        if(condition2) {
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
    private boolean checkCheck(GraphicGround ground, Player competitor, Square king){
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

    /**
     * if a pawn piece reaches to the end line it can chang to queen, bishop, rook or knight.
     * make a new frame to choose which one does the player need.
     * @param pawn the pawn who reach to the end line
     */
    private void askChangePawn(Square pawn){
        JFrame choice = new JFrame("Which one do you need?");
        choice.setAlwaysOnTop(true);
        choice.setLayout(new GridLayout());
        choice.setSize(350, 200);
        JButton Queen = new JButton("Queen");
        JButton Bishop = new JButton("Bishop");
        JButton Rook = new JButton("Rook");
        JButton Knight = new JButton("Knight");
        if(pawn.getMohre().getColor().equals("white")){
            try {
                Image img, newImage;
                img = ImageIO.read(getClass().getResource("./Images/Chess_qlt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Queen.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_blt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Bishop.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_rlt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Rook.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_nlt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Knight.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println();
            }
        }
        else {
            try {
                Image img, newImage;
                img = ImageIO.read(getClass().getResource("./Images/Chess_qdt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Queen.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_bdt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Bishop.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_rdt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Rook.setIcon(new ImageIcon(newImage));
                img = ImageIO.read(getClass().getResource("./Images/Chess_ndt60.png"));
                newImage = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                Knight.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println();
            }
        }
        choice.add(Queen);
        Queen.addActionListener(new MyActionListener(choice, pawn, player1, player2));
        choice.add(Bishop);
        Bishop.addActionListener(new MyActionListener(choice, pawn, player1, player2));
        choice.add(Rook);
        Rook.addActionListener(new MyActionListener(choice, pawn, player1, player2));
        choice.add(Knight);
        Knight.addActionListener(new MyActionListener(choice, pawn, player1, player2));
        choice.setVisible(true);
    }
}

/**
 * make actionListener for the JButtons on choice frame.
 * @author Bahar Kaviani
 */
class MyActionListener implements java.awt.event.ActionListener{
    private JFrame frame;
    private Square pawn;
    private Player player1, player2;

    MyActionListener(JFrame frame, Square pawn, Player player1, Player player2){
        this.frame = frame;
        this.pawn = pawn;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * let you chose a piece and change your pawn. Then put new piece and it's icon in pawn's place.
     * also change the player's array of pieces.
     * @param e information of e which actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)(e.getSource())).getText().equals("Queen")){
            Queen queen = new Queen(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(queen);
            pawn.getMohre().setImage(pawn);
            if(pawn.getMohre().getColor().equals("white")){
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player1.getPlayerPieces()[i]){
                        player1.getPlayerPieces()[i] = queen;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player2.getPlayerPieces()[i]){
                        player2.getPlayerPieces()[i] = queen;
                        break;
                    }
                }
            }
        }
        else if((((JButton)(e.getSource())).getText().equals("Bishop"))){
            Bishop bishop = new Bishop(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(bishop);
            pawn.getMohre().setImage(pawn);
            if(pawn.getMohre().getColor().equals("white")){
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player1.getPlayerPieces()[i]){
                        player1.getPlayerPieces()[i] = bishop;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player2.getPlayerPieces()[i]){
                        player2.getPlayerPieces()[i] = bishop;
                        break;
                    }
                }
            }
        }
        else if((((JButton)(e.getSource())).getText().equals("Rook"))){
            Rook rook = new Rook(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(rook);
            pawn.getMohre().setImage(pawn);
            if(pawn.getMohre().getColor().equals("white")){
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player1.getPlayerPieces()[i]){
                        player1.getPlayerPieces()[i] = rook;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player2.getPlayerPieces()[i]){
                        player2.getPlayerPieces()[i] = rook;
                        break;
                    }
                }
            }
        }
        else if((((JButton)(e.getSource())).getText().equals("Knight"))){
            Knight knight = new Knight(pawn.getRow(), pawn.getColumn(), pawn.getMohre().getColor());
            pawn.setMohre(knight);
            pawn.getMohre().setImage(pawn);
            if(pawn.getMohre().getColor().equals("white")){
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player1.getPlayerPieces()[i]){
                        player1.getPlayerPieces()[i] = knight;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < 16; i++) {
                    if(pawn.getMohre() == player2.getPlayerPieces()[i]){
                        player2.getPlayerPieces()[i] = knight;
                        break;
                    }
                }
            }
        }
        frame.dispose();
    }
}