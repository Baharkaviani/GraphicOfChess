public class Main {
    public static void main(String[] args) {
        GraphicGround ground = new GraphicGround();
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                MouseClick mouseListener = new MouseClick(ground, player1, player2, true);
                ground.getGround()[i][j].addMouseListener(mouseListener);
            }
        }
    }
}