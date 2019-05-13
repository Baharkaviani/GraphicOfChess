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
                playerPieces[i].setImage(ground.getGround()[Row.G.ordinal()][i]);
            }
            playerPieces[8] = new Rook(Row.H.ordinal(),0, "white");
            playerPieces[8].setImage(ground.getGround()[Row.H.ordinal()][0]);
            playerPieces[9] = new Knight(Row.H.ordinal(),1, "white");
            playerPieces[9].setImage(ground.getGround()[Row.H.ordinal()][1]);
            playerPieces[10] = new Bishop(Row.H.ordinal(), 2, "white");
            playerPieces[10].setImage(ground.getGround()[Row.H.ordinal()][2]);
            playerPieces[11] = new Queen(Row.H.ordinal(), 3, "white");
            playerPieces[11].setImage(ground.getGround()[Row.H.ordinal()][3]);
            playerPieces[12] = new King(Row.H.ordinal(), 4, "white");
            playerPieces[12].setImage(ground.getGround()[Row.H.ordinal()][4]);
            playerPieces[13] = new Bishop(Row.H.ordinal(), 5, "white");
            playerPieces[13].setImage(ground.getGround()[Row.H.ordinal()][5]);
            playerPieces[14] = new Knight(Row.H.ordinal(), 6, "white");
            playerPieces[14].setImage(ground.getGround()[Row.H.ordinal()][6]);
            playerPieces[15] = new Rook(Row.H.ordinal(), 7, "white");
            playerPieces[15].setImage(ground.getGround()[Row.H.ordinal()][7]);
        }
        else {
            for (int i = 0; i < 8 ; i++) {
                playerPieces[i] = new Pawn(Row.B.ordinal(), i, "Black");
                playerPieces[i].setImage(ground.getGround()[Row.B.ordinal()][i]);
            }
            playerPieces[8] = new Rook(Row.A.ordinal(), 0, "Black");
            playerPieces[8].setImage(ground.getGround()[Row.A.ordinal()][0]);
            playerPieces[9] = new Knight(Row.A.ordinal(), 1, "Black");
            playerPieces[9].setImage(ground.getGround()[Row.A.ordinal()][1]);
            playerPieces[10] = new Bishop(Row.A.ordinal(), 2, "Black");
            playerPieces[10].setImage(ground.getGround()[Row.A.ordinal()][2]);
            playerPieces[11] = new Queen(Row.A.ordinal(), 3, "Black");
            playerPieces[11].setImage(ground.getGround()[Row.A.ordinal()][3]);
            playerPieces[12] = new King(Row.A.ordinal(), 4, "Black");
            playerPieces[12].setImage(ground.getGround()[Row.A.ordinal()][4]);
            playerPieces[13] = new Bishop(Row.A.ordinal(), 5, "Black");
            playerPieces[13].setImage(ground.getGround()[Row.A.ordinal()][5]);
            playerPieces[14] = new Knight(Row.A.ordinal(), 6, "Black");
            playerPieces[14].setImage(ground.getGround()[Row.A.ordinal()][6]);
            playerPieces[15] = new Rook(Row.A.ordinal(), 7, "Black");
            playerPieces[15].setImage(ground.getGround()[Row.A.ordinal()][7]);
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
