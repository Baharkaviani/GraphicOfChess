import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Player {
    private String color;
    private String condition;
    private ChessPieces[] playerPieces = new ChessPieces[16];

    //constructor
    public Player(String color){
        this.color = color;
        this.condition = "normal";
    }

    /**
     * first make objects of pieces then put them on the ground
     * find the pictures and put them on the buttons
     * @param ground put pieces on the ground
     */
    public void putPiecesOnGround(GraphicGround ground){
        //make objects of pieces
        if(color.equals("white")){
            for (int i = 0; i < 8 ; i++) {
                playerPieces[i] = new Pawn(Row.G.ordinal(),i,"white");
                try {
                    Image img = ImageIO.read(getClass().getResource("./Images/Pawn1.jpg"));
                    Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                    ground.getGround()[Row.G.ordinal()][i].setIcon(new ImageIcon(newImage));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            playerPieces[8] = new Rook(Row.H.ordinal(),0, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][0].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[9] = new Knight(Row.H.ordinal(),1, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][1].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[10] = new Bishop(Row.H.ordinal(), 2, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][2].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[11] = new Queen(Row.H.ordinal(), 3, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Queen1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][3].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[12] = new King(Row.H.ordinal(), 4, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/King1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][4].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[13] = new Bishop(Row.H.ordinal(), 5, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][5].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[14] = new Knight(Row.H.ordinal(), 6, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][6].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[15] = new Rook(Row.H.ordinal(), 7, "white");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook1.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.H.ordinal()][7].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            for (int i = 0; i < 8 ; i++) {
                playerPieces[i] = new Pawn(Row.B.ordinal(), i, "Black");
                try {
                    Image img = ImageIO.read(getClass().getResource("./Images/Pawn2.jpg"));
                    Image newImage = img.getScaledInstance(40, 80, Image.SCALE_DEFAULT);
                    ground.getGround()[Row.B.ordinal()][i].setIcon(new ImageIcon(newImage));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            playerPieces[8] = new Rook(Row.A.ordinal(), 0, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][0].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[9] = new Knight(Row.A.ordinal(), 1, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][1].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[10] = new Bishop(Row.A.ordinal(), 2, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][2].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[11] = new Queen(Row.A.ordinal(), 3, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Queen2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][3].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[12] = new King(Row.A.ordinal(), 4, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/King2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][4].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[13] = new Bishop(Row.A.ordinal(), 5, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Bishop2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][5].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[14] = new Knight(Row.A.ordinal(), 6, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Knight2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][6].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            playerPieces[15] = new Rook(Row.A.ordinal(), 7, "Black");
            try {
                Image img = ImageIO.read(getClass().getResource("./Images/Rook2.jpg"));
                Image newImage = img.getScaledInstance(45, 80, Image.SCALE_DEFAULT);
                ground.getGround()[Row.A.ordinal()][7].setIcon(new ImageIcon(newImage));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        //put pieces on the ground
        for (int i = 0; i < 16; i++) {
            ground.setSquare(playerPieces[i].getRow(), playerPieces[i].getColumn(), playerPieces[i]);
        }
    }

    //getter
    public ChessPieces[] getPlayerPieces() {
        return playerPieces;
    }
}
