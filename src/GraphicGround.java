import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * make frame and ground
 * @author Bahar Kaviani
 */
class GraphicGround {
    private JFrame frame;
    private JPanel line, chess, white, black, turn;
    private Square[][] ground;
    private JButton[] player1, player2;
    private int player1Losers, player2Losers;
    private boolean gTurn, gClicked, gPlay;
    private Square currentSquare, newSquare, player1King, player2King;

    GraphicGround (String name){
        frame = new JFrame(name);
        line = new JPanel();
        chess = new JPanel();
        white = new JPanel();
        black = new JPanel();
        turn = new JPanel();
        ground = new Square[8][8];
        player1 = new JButton[16];
        player2 = new JButton[16];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground[i][j] = new Square(i, j, null);
            }
        }
        for (int i = 0; i < 16; i++) {
            player1[i] = new JButton();
        }
        for (int i = 0; i < 16; i++) {
            player2[i] = new JButton();
        }
        player1Losers = 0;
        player2Losers = 0;
        gTurn = true;
        gClicked = false;
        gPlay = false;
        currentSquare = null;
        newSquare = null;
        player1King = null;
        player2King = null;

        frame.setLayout(new BorderLayout());

        line.setBackground(new Color(0x1DD3FF));
        chess.setBackground(new Color(0x16ACE1));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0)
                    ground[i][j].setBackground(new Color(0x101374));
                else
                    ground[i][j].setBackground(new Color(0xFFFFFF));
            }
        }
        for (int i = 0; i < 16; i++) {
            player1[i].setBackground(Color.WHITE);
            player1[i].setBorder(new LineBorder(Color.BLACK));
        }
        for (int i = 0; i < 16; i++) {
            player2[i].setBackground(Color.WHITE);
            player2[i].setBorder(new LineBorder(Color.BLACK));
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground[i][j].setBorder(new LineBorder(Color.BLACK, 5));
            }
        }

        white.setBackground(new Color(0x1DD3FF));
        black.setBackground(new Color(0x1DD3FF));
        turn.setBackground(new Color(0xFFFFFF));
        turn.setBorder(new LineBorder(Color.BLACK, 5));
        line.setBorder(new LineBorder(Color.BLACK, 7));

        line.setPreferredSize(new Dimension(440, 100));
        chess.setPreferredSize(new Dimension(100, 100));
        white.setPreferredSize(new Dimension(100, 100));
        black.setPreferredSize(new Dimension(100, 100));
        turn.setPreferredSize(new Dimension(100, 100));

        chess.setLayout(new GridLayout(8, 8, 0, 0));
        line.setLayout(new GridLayout(3, 0, 8 , 50));
        white.setLayout(new GridLayout(2, 8, 5, 5));
        black.setLayout(new GridLayout(2, 8, 5, 5));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess.add(ground[i][j]);
            }
        }
        for (int i = 0; i < 16; i++) {
            player1[i].setBackground(new Color(0x16ACE1));
            white.add(player1[i]);
        }
        for (int i = 0; i < 16; i++) {
            player2[i].setBackground(new Color(0x16ACE1));
            black.add(player2[i]);
        }
        line.add(black);
        line.add(turn);
        line.add(white);

        frame.getContentPane().add(line,BorderLayout.WEST);
        frame.getContentPane().add(chess, BorderLayout.CENTER);
        frame.setSize(1280,690);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * write sth on turn JPanel
     * @param str the string that is going to be written on turn JPanel
     */
    void setTextForTurn(String str) {
        Font font = new Font("Verdana", Font.BOLD, 40);
        JLabel jLabel = new JLabel(str);
        jLabel.setFont(font);
        turn.add(jLabel);
        frame.setVisible(true);
    }

    /**
     * set new color for the turn panel
     * @param color color for the turn panel
     */
    void setColorForTurn(Color color){
        turn.setBackground(color);
        frame.setVisible(true);
    }

    /**
     * put the piece in lise lone for the player
     * @param piece the piece who lose
     */
    void setLosePieces(ChessPieces piece){
        if(piece.getColor().equals("white")){
            piece.setImage(player1[player1Losers]);
            player1Losers++;
        }
        else {
            piece.setImage(player2[player2Losers]);
            player2Losers++;
        }
    }

    /**
     * remove the text from turn panel
     */
    void makeTurnEmpty(){
        turn.removeAll();
        JLabel jLabel = new JLabel("");
        turn.add(jLabel);
        turn.add(jLabel);
        turn.add(jLabel);
        frame.repaint();
        frame.setVisible(true);
    }

    Square getSquare(int row, int column){
        return ground[row][column];
    }

    Square[][] getGround() {
        return ground;
    }

    boolean isTurn() {
        return gTurn;
    }

    void setTurn(boolean turn){
        this.gTurn = turn;
    }

    boolean isgClicked() {
        return gClicked;
    }

    void setgClicked(boolean clicked){
        gClicked = clicked;
    }

    boolean isgPlay(){
        return gPlay;
    }

    public void setgPlay(boolean gPlay) {
        this.gPlay = gPlay;
    }

    Square getCurrentSquare() {
        return currentSquare;
    }

    void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    Square getNewSquare(){
        return newSquare;
    }

    void setNewSquare(Square newSquare) {
        this.newSquare = newSquare;
    }

    Square getPlayer1King(){
        return player1King;
    }

    void setPlayer1King(Square player1King) {
        this.player1King = player1King;
    }

    Square getPlayer2King(){
        return player2King;
    }

    void setPlayer2King(Square player2King) {
        this.player2King = player2King;
    }
}