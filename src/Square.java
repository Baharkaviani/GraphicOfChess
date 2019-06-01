import javax.swing.*;
import java.io.*;

/**
 * object of type Square that is a JButton for our ground
 * @author Bahar Kaviani
 */
public class Square extends JButton implements Serializable{
    private int row, column;
    private ChessPieces mohre;

    Square(int row, int column, ChessPieces mohre){
        this.row = row;
        this.column = column;
        this.mohre = mohre;
    }

    /**
     * write row and column of Square
     * @return String which includes row and column of the Square
     */
    @Override
    public String toString(){
        return row + "," + column + ",";
    }

    private void readObject(ObjectInputStream test)throws IOException, ClassNotFoundException {
        test.defaultReadObject();
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