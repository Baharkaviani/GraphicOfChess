import javax.swing.*;

/**
 * object of type Square that is a JButton for our ground
 * @author Bahar Kaviani
 */
public class Square extends JButton {
    private int row, column;
    private ChessPieces mohre;

    Square(int row, int column, ChessPieces mohre){
        this.row = row;
        this.column = column;
        this.mohre = mohre;
    }

    ChessPieces getMohre() {
        return mohre;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    void setMohre(ChessPieces mohre) {
        this.mohre = mohre;
    }
}