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
    private boolean gTurn, gClicked;
    private Square currentSquare, newSquare, player1King, player2King;

    //constructor
    GraphicGround (){
        frame = new JFrame();
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
        currentSquare = null;
        newSquare = null;
        player1King = null;
        player2King = null;

        frame.setLayout(new BorderLayout());

        line.setBackground(new Color(0xF0C373));
        chess.setBackground(new Color(0xC67D10));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0)
                    ground[i][j].setBackground(new Color(0x000000));
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

        white.setBackground(new Color(0xF0C373));
        black.setBackground(new Color(0xF0C373));
        turn.setBackground(new Color(0xFFFFFF));
        turn.setBorder(new LineBorder(Color.BLACK, 5));

        line.setPreferredSize(new Dimension(400, 100));
        chess.setPreferredSize(new Dimension(100, 100));
        white.setPreferredSize(new Dimension(100, 100));
        black.setPreferredSize(new Dimension(100, 100));
        turn.setPreferredSize(new Dimension(100, 100));

        chess.setLayout(new GridLayout(8, 8, 8, 8));
        line.setLayout(new GridLayout(3, 0, 8 , 70));
        white.setLayout(new GridLayout(2, 8, 5, 10));
        black.setLayout(new GridLayout(2, 8, 5, 10));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess.add(ground[i][j]);
            }
        }
        for (int i = 0; i < 16; i++) {
            white.add(player1[i]);
        }
        for (int i = 0; i < 16; i++) {
            black.add(player2[i]);
        }
        line.add(white);
        line.add(turn);
        line.add(black);

        frame.getContentPane().add(line,BorderLayout.WEST);
        frame.getContentPane().add(chess, BorderLayout.CENTER);
        frame.setSize(1500,700);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * write sth on turn JPanel
     * @param str the string that is going to be written on turn JPanel
     */
    void setTextForTurn(String str) {
        JLabel jLabel = new JLabel(str);
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

    void setLosePieces(ChessPieces piece){
        if(piece.getColor().equals("white")){
            piece.setImage(player1[player1Losers]);
            player1Losers++;
            System.out.println(player1Losers);
        }
        else {
            piece.setImage(player2[player2Losers]);
            player2Losers++;
            System.out.println(player2Losers);
        }
    }

    /**
     * get the color of the turn panel
     */
    Color getColorForTurn(){
        return turn.getBackground();
    }

    /**
     * remove the text from turn panel
     */
    void makeTurnEmpty(){
        turn.removeAll();
        JLabel jLabel = new JLabel("");
        turn.add(jLabel);
        frame.setVisible(true);
    }

    Square getSquare(int row, int column){
        return ground[row][column];
    }

    Square[][] getGround() {
        return ground;
    }

    public boolean isTurn() {
        return gTurn;
    }

    void setTurn(boolean turn){
        this.gTurn = turn;
    }

    public boolean isgClicked() {
        return gClicked;
    }

    void setgClicked(boolean clicked){
        gClicked = clicked;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    public Square getNewSquare(){
        return newSquare;
    }

    public void setNewSquare(Square newSquare) {
        this.newSquare = newSquare;
    }

    public Square getPlayer1King(){
        return player1King;
    }

    public void setPlayer1King(Square player1King) {
        this.player1King = player1King;
    }

    public Square getPlayer2King(){
        return player2King;
    }

    public void setPlayer2King(Square player2King) {
        this.player2King = player2King;
    }
}