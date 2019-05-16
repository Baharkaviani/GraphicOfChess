import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * make frame and ground
 * @author Bahar Kaviani
 */
public class GraphicGround {
    private JFrame frame;
    private JPanel line, chess, white, black, turn;
    private Square[][] ground;

    //constructor
    public GraphicGround (){
        frame = new JFrame();
        line = new JPanel();
        chess = new JPanel();
        white = new JPanel();
        black = new JPanel();
        turn = new JPanel();
        ground = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground[i][j] = new Square(i, j, null);
            }
        }

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

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ground[i][j].setBorder(new LineBorder(Color.BLACK, 5));
            }
        }

        white.setBackground(new Color(0x6BF0E5));
        black.setBackground(new Color(0x6BF0E5));
        turn.setBackground(new Color(0xC67D10));

        line.setPreferredSize(new Dimension(400, 100));
        chess.setPreferredSize(new Dimension(100, 100));
        white.setPreferredSize(new Dimension(100, 100));
        black.setPreferredSize(new Dimension(100, 100));
        turn.setPreferredSize(new Dimension(100, 100));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                MouseMove mouseListener = new MouseMove(this);
                ground[i][j].addMouseListener(mouseListener);
            }
        }

        chess.setLayout(new GridLayout(8, 8, 10, 4));
        line.setLayout(new GridLayout(3, 0, 10 , 10));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess.add(ground[i][j]);
            }
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

    //getter
    public Square getSquare(int row, int column){
        return ground[row][column];
    }

    public Square[][] getGround() {
        return ground;
    }
}