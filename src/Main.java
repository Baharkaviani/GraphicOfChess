public class Main {
    public static void main(String[] args) {
        GraphicGround ground = new GraphicGround();
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
    }
}