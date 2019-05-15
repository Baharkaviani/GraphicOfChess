import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * make pieces
 * @author Bahar Kaviani
 */
abstract class ChessPieces {
    private int row, column;
    private String type, color;
    private boolean lose;
    private ArrayList<Square> possibleToGo;
    private Image image;

    public ChessPieces(int row, int column, String color){
        this.row = row;
        this.column = column;
        possibleToGo = new ArrayList<>();
        lose = false;
        type = "";
        this.color = color;
        image = null;
    }

    /**
     * @param sq add sq to the arrayList which shows where the piece can go
     */
    public void addPossibleToGo(Square sq){
        possibleToGo.add(sq);
    }

    public void clearTheArrayList(){
        possibleToGo.clear();
    }

    /**
     * abstract function for pieces to find all positions that one piece can go
     * @param ground the ground of chess
     */
    public abstract void findAllPossibleToGo(GraphicGround ground);

    /**
     * abstract function to set image for the square
     * @param square the square of ground
     */
    public abstract void setImage(Square square);

    /**
     * move piece to new Square
     * @param newSquare the square that piece wants to go
     * @return boolean type to check if the piece moves or not
     */
    public boolean move(Square newSquare){
        //move!!!!
        for (int i = 0; i < possibleToGo.size(); i++) {
            if(possibleToGo.get(i).equals(newSquare)){
                row = newSquare.getRow();
                column = newSquare.getColumn();
                if(newSquare.getMohre() != null){
                    newSquare.getMohre().setLose(true);
                }
                return true;
            }
        }
        System.out.println("Can not move. Try again!");
        return false;
    }

    /**
     * if the king piece goes to new square that it puts it in check condition it must move back to the last square
     * @param lastSquare the lastsquare of the piece
     */
    public void moveBack(Square lastSquare){
        row = lastSquare.getRow();
        column = lastSquare.getColumn();
    }

    //getter
    public ArrayList<Square> getPossibleToGo() {
        return possibleToGo;
    }

    public String getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getColor() {
        return color;
    }

    public boolean isLose() {
        return lose;
    }

    //setter
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }
}

/**
 * make Pawn piece
 * @author Bahar Kaviani
 */
class Pawn extends ChessPieces{
    public Pawn(int row, int column, String color){
        super(row, column, color);
        super.setType("Pawn");
    }

    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Pawn1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Pawn2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void findAllPossibleToGo(GraphicGround ground){
        super.clearTheArrayList();
        //comment examples are for white pieces
        if(super.getColor().equals("white")){
            //check if the piece can go up or not. And how many steps it can go
            //if it's first move, it can go 1 or 2 steps
            if(super.getRow() == Row.G.ordinal()){
                if(ground.getGround()[super.getRow() - 1][super.getColumn()].getMohre() == null){
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                if(ground.getGround()[super.getRow() - 2][super.getColumn()].getMohre() == null){
                    Square sq = ground.getSquare(super.getRow() - 2, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
            }
            //if not, it can go just 1 step
            else {
                //normal!
                if(super.getRow() - 1 > 1) {
                    if (ground.getGround()[super.getRow() - 1][super.getColumn()].getMohre() == null) {
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn());
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
                //if it reach to the A-Row it can change to another piece
                /*I didn't write how to change the piece yet
                 */
                if(super.getRow() - 1 == 1) {
                    if (ground.getGround()[super.getRow() - 1][super.getColumn()].getMohre() == null) {
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn());
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            //check if the piece can hit any pieces or not
            if(super.getColumn() == 0){
                if(ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre().getColor().equals("Black")){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            else if(super.getColumn() == 7){
                if(ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre().getColor().equals("Black")){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            else{
                if(ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre().getColor().equals("Black")){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
                if(ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre().getColor().equals("Black")){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
        }
        else if(super.getColor().equals("Black")){
            //check if the piece can go up or not. And how many steps it can go
            //if it's first move, it can go 1 or 2 steps
            if(super.getRow() == Row.B.ordinal()){
                if(ground.getGround()[super.getRow() + 1][super.getColumn()].getMohre() == null){
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                if(ground.getGround()[super.getRow() + 2][super.getColumn()].getMohre() == null){
                    Square sq = ground.getSquare(super.getRow() + 2, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
            }
            //if not, it can go just 1 step
            else {
                //normal!
                if(super.getRow() - 1 > 1) {
                    if (ground.getGround()[super.getRow() + 1][super.getColumn()].getMohre() == null) {
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn());
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
                //if it reach to the A-Row it can change to another piece
                /*I didn't write how to change the piece yet
                 */
                if(super.getRow() - 1 == 1) {
                    if (ground.getGround()[super.getRow() + 1][super.getColumn()].getMohre() == null) {
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn());
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            //check if the piece can hit any pieces or not
            if(super.getColumn() == 0){
                if(ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre().getColor().equals("white")){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            else if(super.getColumn() == 7){
                if(ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre().getColor().equals("white")){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
            else{
                if(ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre().getColor().equals("white")){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
                if(ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre() != null){
                    if(ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre().getColor().equals("white")){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                }
            }
        }
    }
}

/**
 * make Rook piece
 * @author Bahar Kaviani
 */
class Rook extends ChessPieces{
    public Rook(int row, int column, String color){
        super(row, column, color);
        super.setType("Rook");
    }

    /**
     * set image for the square
     * @param square the square of ground
     */
    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * find all squares that the piece can go and add them to possibleToGo arrayList
     * @param ground the ground of chess
     */
    @Override
    public void findAllPossibleToGo(GraphicGround ground){
        super.clearTheArrayList();
        //if it can go up
        for (int i = 1; i <= super.getRow(); i++) {
            if(ground.getGround()[super.getRow() - i][super.getColumn()].getMohre() == null){
                Square sq = ground.getSquare(super.getRow() - i, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            //if the piece reach to mohre it can't go up more
            else{
                if(!(ground.getGround()[super.getRow() - i][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go down
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(ground.getGround()[super.getRow() + i][super.getColumn()].getMohre() == null){
                Square sq = ground.getSquare(super.getRow() + i, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow() + i][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go left
        for (int i = 1; i <= super.getColumn(); i++) {
            if(ground.getGround()[super.getRow()][super.getColumn() - i].getMohre() == null){
                Square sq = ground.getSquare(super.getRow(), super.getColumn() - i);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow()][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go right
        for (int i = 1; i <= 7 - super.getColumn(); i++) {
            if(ground.getGround()[super.getRow()][super.getColumn() + i].getMohre() == null){
                Square sq = ground.getSquare(super.getRow(), super.getColumn() + i);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow()][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
    }
}

/**
 * make Knight piece
 * @author Bahar Kaviani
 */
class Knight extends ChessPieces{
    public Knight(int row, int column,  String color){
        super(row, column, color);
        super.setType("Knight");
    }

    /**
     * set image for the square
     * @param square the square of ground
     */
    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * find all squares that the piece can go and add them to possibleToGo arrayList
     * @param ground the ground of chess
     */
    @Override
    public void findAllPossibleToGo(GraphicGround ground) {
        super.clearTheArrayList();
        if(super.getColumn() - 2 >= 0) {
            //c - 2, r - 1
            if(super.getRow() - 1 >= 0) {
                if (ground.getGround()[super.getRow() - 1][super.getColumn() - 2].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 2);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 1][super.getColumn() - 2].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 2);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c - 2, r + 1
            if(super.getRow() + 1 < 8) {
                if (ground.getGround()[super.getRow() + 1][super.getColumn() - 2].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 2);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 1][super.getColumn() - 2].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 2);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
        if(super.getColumn() - 1 >= 0) {
            //c - 1, r - 2
            if(super.getRow() - 2 >= 0) {
                if (ground.getGround()[super.getRow() - 2][super.getColumn() - 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 2, super.getColumn() - 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 2][super.getColumn() - 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 2, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c - 1, r + 2
            if(super.getRow() + 2 < 8) {
                if (ground.getGround()[super.getRow() + 2][super.getColumn() - 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 2, super.getColumn() - 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 2][super.getColumn() - 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 2, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
        if(super.getColumn() + 1 < 8) {
            //c + 1, r - 2
            if(super.getRow() - 2 >= 0) {
                if (ground.getGround()[super.getRow() - 2][super.getColumn() + 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 2, super.getColumn() + 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 2][super.getColumn() + 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 2, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c + 1, r + 2
            if(super.getRow() + 2 < 8) {
                if (ground.getGround()[super.getRow() + 2][super.getColumn() + 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 2, super.getColumn() + 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 2][super.getColumn() + 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 2, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
        if(super.getColumn() + 2 < 8) {
            //c + 2, r - 1
            if(super.getRow() - 1 >= 0) {
                if (ground.getGround()[super.getRow() - 1][super.getColumn() + 2].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 2);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 1][super.getColumn() + 2].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 2);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c + 2, r + 1
            if(super.getRow() + 1 < 8) {
                if (ground.getGround()[super.getRow() + 1][super.getColumn() + 2].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 2);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 1][super.getColumn() + 2].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 2);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
    }
}

/**
 * make Bishop piece
 * @author Bahar Kaviani
 */
class Bishop extends ChessPieces{
    public Bishop(int row, int column, String color){
        super(row, column, color);
        super.setType("Bishop");
    }

    /**
     * set image for the square
     * @param square the square of ground
     */
    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * find all squares that the piece can go and add them to possibleToGo arrayList
     * @param ground the ground of chess
     */
    @Override
    public void findAllPossibleToGo(GraphicGround ground) {
        super.clearTheArrayList();
        //if it can go up and right
        for (int i = 1; i <= super.getRow(); i++) {
            if(super.getColumn() + i < 8) {
                if (ground.getGround()[super.getRow() - i][super.getColumn() + i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if the piece reach to mohre it can't go up and right more
                else {
                    if (!(ground.getGround()[super.getRow() - i][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() - i, super.getColumn() + i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go up and left
        for (int i = 1; i <= super.getRow(); i++) {
            if(super.getColumn() - i >= 0) {
                if (ground.getGround()[super.getRow() - i][super.getColumn() - i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if the piece reach to mohre it can't go up and right more
                else {
                    if (!(ground.getGround()[super.getRow() - i][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() - i, super.getColumn() - i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go down and right
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(super.getColumn() + i < 8) {
                if (ground.getGround()[super.getRow() + i][super.getColumn() + i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                } else {
                    if (!(ground.getGround()[super.getRow() + i][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() + i, super.getColumn() + i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go down and left
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(super.getColumn() - i >= 0) {
                if (ground.getGround()[super.getRow() + i][super.getColumn() - i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                } else {
                    if (!(ground.getGround()[super.getRow() + i][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() + i, super.getColumn() - i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
    }
}

/**
 * make Queen piece
 * @author Bahar Kaviani
 */
class Queen extends ChessPieces{
    public Queen(int row, int column, String color){
        super(row, column, color);
        super.setType("Queen");
    }

    /**
     * set image for the square
     * @param square the square of ground
     */
    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Queen1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Queen2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * find all squares that the piece can go and add them to possibleToGo arrayList
     * @param ground the ground of chess
     */
    @Override
    public void findAllPossibleToGo(GraphicGround ground){
        super.clearTheArrayList();
        //if it can go up
        for (int i = 1; i <= super.getRow(); i++) {
            if(ground.getGround()[super.getRow() - i][super.getColumn()].getMohre() == null){
                Square sq = ground.getSquare(super.getRow() - i, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            //if the piece reach to mohre it can't go up more
            else{
                if(!(ground.getGround()[super.getRow() - i][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go down
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(ground.getGround()[super.getRow() + i][super.getColumn()].getMohre() == null){
                Square sq = ground.getSquare(super.getRow() + i, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow() + i][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go left
        for (int i = 1; i <= super.getColumn(); i++) {
            if(ground.getGround()[super.getRow()][super.getColumn() - i].getMohre() == null){
                Square sq = ground.getSquare(super.getRow(), super.getColumn() - i);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow()][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go right
        for (int i = 1; i <= 7 - super.getColumn(); i++) {
            if(ground.getGround()[super.getRow()][super.getColumn() + i].getMohre() == null){
                Square sq = ground.getSquare(super.getRow(), super.getColumn() + i);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else{
                if(!(ground.getGround()[super.getRow()][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                    break;
                }
                //if that piece is with same color that square must not add to PossibleToGo
                else
                    break;
            }
        }
        //if it can go up and right
        for (int i = 1; i <= super.getRow(); i++) {
            if(super.getColumn() + i < 8) {
                if (ground.getGround()[super.getRow() - i][super.getColumn() + i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if the piece reach to mohre it can't go up and right more
                else {
                    if (!(ground.getGround()[super.getRow() - i][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() - i, super.getColumn() + i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go up and left
        for (int i = 1; i <= super.getRow(); i++) {
            if(super.getColumn() - i >= 0) {
                if (ground.getGround()[super.getRow() - i][super.getColumn() - i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - i, super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if the piece reach to mohre it can't go up and right more
                else {
                    if (!(ground.getGround()[super.getRow() - i][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() - i, super.getColumn() - i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go down and right
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(super.getColumn() + i < 8) {
                if (ground.getGround()[super.getRow() + i][super.getColumn() + i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn() + i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                } else {
                    if (!(ground.getGround()[super.getRow() + i][super.getColumn() + i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() + i, super.getColumn() + i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
        //if it can go down and left
        for (int i = 1; i <= 7 - super.getRow(); i++) {
            if(super.getColumn() - i >= 0) {
                if (ground.getGround()[super.getRow() + i][super.getColumn() - i].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + i, super.getColumn() - i);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char) (sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                } else {
                    if (!(ground.getGround()[super.getRow() + i][super.getColumn() - i].getMohre().getColor().equals(super.getColor()))) {
                        Square sq = ground.getSquare(super.getRow() + i, super.getColumn() - i);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char) (sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                        break;
                    }
                    //if that piece is with same color that square must not add to PossibleToGo
                    else
                        break;
                }
            }
        }
    }
}

/**
 * make King piece
 * @author Bahar Kaviani
 */
class King extends ChessPieces{
    public King(int row, int column, String color){
        super(row, column, color);
        super.setType("King");
    }

    /**
     * set image for the square
     * @param square the square of ground
     */
    @Override
    public void setImage(Square square){
        if(super.getColor().equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/King1.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/King2.jpg"));
                Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                square.setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * find all squares that the piece can go and add them to possibleToGo arrayList
     * @param ground the ground of chess
     */
    @Override
    public void findAllPossibleToGo(GraphicGround ground){
        super.clearTheArrayList();
        if(super.getColumn() - 1 >= 0) {
            //c - 1, r - 1
            if(super.getRow() - 1 >= 0) {
                if (ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 1][super.getColumn() - 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c - 1, r
            if (ground.getGround()[super.getRow()][super.getColumn() - 1].getMohre() == null) {
                Square sq = ground.getSquare(super.getRow(), super.getColumn() - 1);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else {
                if(!(ground.getGround()[super.getRow()][super.getColumn() - 1].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() - 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
            }
            //c - 1, r + 1
            if(super.getRow() + 1 < 8) {
                if (ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 1][super.getColumn() - 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() - 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
        //c, r - 1
        if(super.getRow() - 1 >= 0) {
            if (ground.getGround()[super.getRow() - 1][super.getColumn()].getMohre() == null) {
                Square sq = ground.getSquare(super.getRow() - 1, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else {
                if(!(ground.getGround()[super.getRow() - 1][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
            }
        }
        //c, r + 1
        if(super.getRow() + 1 < 8) {
            if (ground.getGround()[super.getRow() + 1][super.getColumn()].getMohre() == null) {
                Square sq = ground.getSquare(super.getRow() + 1, super.getColumn());
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else {
                if(!(ground.getGround()[super.getRow() + 1][super.getColumn()].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn());
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
            }
        }
        if(super.getColumn() + 1 < 8) {
            //c + 1, r - 1
            if(super.getRow() - 1 >= 0) {
                if (ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() - 1][super.getColumn() + 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() - 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
            //c + 1, r
            if (ground.getGround()[super.getRow()][super.getColumn() + 1].getMohre() == null) {
                Square sq = ground.getSquare(super.getRow(), super.getColumn() + 1);
                super.addPossibleToGo(sq);
//                //print
//                char row = (char)(sq.getRow() + 65);
//                System.out.println("" + row + (sq.getColumn() + 1));
//                //
            }
            else {
                if(!(ground.getGround()[super.getRow()][super.getColumn() + 1].getMohre().getColor().equals(super.getColor()))){
                    Square sq = ground.getSquare(super.getRow(), super.getColumn() + 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
            }
            //c + 1, r + 1
            if(super.getRow() + 1 < 8) {
                if (ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre() == null) {
                    Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 1);
                    super.addPossibleToGo(sq);
//                    //print
//                    char row = (char)(sq.getRow() + 65);
//                    System.out.println("" + row + (sq.getColumn() + 1));
//                    //
                }
                else {
                    if(!(ground.getGround()[super.getRow() + 1][super.getColumn() + 1].getMohre().getColor().equals(super.getColor()))){
                        Square sq = ground.getSquare(super.getRow() + 1, super.getColumn() + 1);
                        super.addPossibleToGo(sq);
//                        //print
//                        char row = (char)(sq.getRow() + 65);
//                        System.out.println("" + row + (sq.getColumn() + 1));
//                        //
                    }
                    //if that piece is with same color that square must not add to PossibleToGo -> do nothing!
                }
            }
        }
    }
}