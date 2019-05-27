/**
 * player of the match
 * @author Bahar Kaviani
 */
class Player {
    private String color;
    private ChessPieces[] playerPieces = new ChessPieces[16];

    Player(String color){
        this.color = color;
    }

    /**
     * first make objects of pieces then put them on the ground
     * find the pictures and put them on the buttons
     * @param ground put pieces on the ground
     */
    void putPiecesOnGround(GraphicGround ground){
        if(color.equals("white")){
            for (int i = 0; i < 8 ; i++) {
                playerPieces[i] = new Pawn(Row.G.ordinal(),i,"white");
                playerPieces[i].setImage(ground.getGround()[Row.G.ordinal()][i]);
                ground.getGround()[Row.G.ordinal()][i].setMohre(playerPieces[i]);
            }
            playerPieces[8] = new Rook(Row.H.ordinal(),0, "white");
            playerPieces[8].setImage(ground.getGround()[Row.H.ordinal()][0]);
            ground.getGround()[Row.H.ordinal()][0].setMohre(playerPieces[8]);
            playerPieces[9] = new Knight(Row.H.ordinal(),1, "white");
            playerPieces[9].setImage(ground.getGround()[Row.H.ordinal()][1]);
            ground.getGround()[Row.H.ordinal()][1].setMohre(playerPieces[9]);
            playerPieces[10] = new Bishop(Row.H.ordinal(), 2, "white");
            playerPieces[10].setImage(ground.getGround()[Row.H.ordinal()][2]);
            ground.getGround()[Row.H.ordinal()][2].setMohre(playerPieces[10]);
            playerPieces[11] = new Queen(Row.H.ordinal(), 3, "white");
            playerPieces[11].setImage(ground.getGround()[Row.H.ordinal()][3]);
            ground.getGround()[Row.H.ordinal()][3].setMohre(playerPieces[11]);
            playerPieces[12] = new King(Row.H.ordinal(), 4, "white");
            playerPieces[12].setImage(ground.getGround()[Row.H.ordinal()][4]);
            ground.getGround()[Row.H.ordinal()][4].setMohre(playerPieces[12]);
            playerPieces[13] = new Bishop(Row.H.ordinal(), 5, "white");
            playerPieces[13].setImage(ground.getGround()[Row.H.ordinal()][5]);
            ground.getGround()[Row.H.ordinal()][5].setMohre(playerPieces[13]);
            playerPieces[14] = new Knight(Row.H.ordinal(), 6, "white");
            playerPieces[14].setImage(ground.getGround()[Row.H.ordinal()][6]);
            ground.getGround()[Row.H.ordinal()][6].setMohre(playerPieces[14]);
            playerPieces[15] = new Rook(Row.H.ordinal(), 7, "white");
            playerPieces[15].setImage(ground.getGround()[Row.H.ordinal()][7]);
            ground.getGround()[Row.H.ordinal()][7].setMohre(playerPieces[15]);
        }
        else {
            for (int i = 0; i < 8 ; i++) {
                playerPieces[i] = new Pawn(Row.B.ordinal(), i, "Black");
                playerPieces[i].setImage(ground.getGround()[Row.B.ordinal()][i]);
                ground.getGround()[Row.B.ordinal()][i].setMohre(playerPieces[i]);
            }
            playerPieces[8] = new Rook(Row.A.ordinal(), 0, "Black");
            playerPieces[8].setImage(ground.getGround()[Row.A.ordinal()][0]);
            ground.getGround()[Row.A.ordinal()][0].setMohre(playerPieces[8]);
            playerPieces[9] = new Knight(Row.A.ordinal(), 1, "Black");
            playerPieces[9].setImage(ground.getGround()[Row.A.ordinal()][1]);
            ground.getGround()[Row.A.ordinal()][1].setMohre(playerPieces[9]);
            playerPieces[10] = new Bishop(Row.A.ordinal(), 2, "Black");
            playerPieces[10].setImage(ground.getGround()[Row.A.ordinal()][2]);
            ground.getGround()[Row.A.ordinal()][2].setMohre(playerPieces[10]);
            playerPieces[11] = new Queen(Row.A.ordinal(), 3, "Black");
            playerPieces[11].setImage(ground.getGround()[Row.A.ordinal()][3]);
            ground.getGround()[Row.A.ordinal()][3].setMohre(playerPieces[11]);
            playerPieces[12] = new King(Row.A.ordinal(), 4, "Black");
            playerPieces[12].setImage(ground.getGround()[Row.A.ordinal()][4]);
            ground.getGround()[Row.A.ordinal()][4].setMohre(playerPieces[12]);
            playerPieces[13] = new Bishop(Row.A.ordinal(), 5, "Black");
            playerPieces[13].setImage(ground.getGround()[Row.A.ordinal()][5]);
            ground.getGround()[Row.A.ordinal()][5].setMohre(playerPieces[13]);
            playerPieces[14] = new Knight(Row.A.ordinal(), 6, "Black");
            playerPieces[14].setImage(ground.getGround()[Row.A.ordinal()][6]);
            ground.getGround()[Row.A.ordinal()][6].setMohre(playerPieces[14]);
            playerPieces[15] = new Rook(Row.A.ordinal(), 7, "Black");
            playerPieces[15].setImage(ground.getGround()[Row.A.ordinal()][7]);
            ground.getGround()[Row.A.ordinal()][7].setMohre(playerPieces[15]);
        }
    }

    ChessPieces[] getPlayerPieces() {
        return playerPieces;
    }
}
