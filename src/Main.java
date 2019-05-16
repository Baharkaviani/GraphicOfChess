public class Main {
    public static void main(String[] args) {
        GraphicGround ground = new GraphicGround();
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        while (true){
            String play = "true";
            //player1
            Square player1King = ground.getSquare(0, 0);
            for (int i = 0; i < 16; i++) {
                if(player1.getPlayerPieces()[i] instanceof King) {
                    player1King = ground.getSquare(player1.getPlayerPieces()[i].getRow(), player1.getPlayerPieces()[i].getColumn());
                    break;
                }
            }
        }
    }
}