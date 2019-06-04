import java.awt.*;
import java.net.*;
import java.io.*;

/**
 * A Java program for a Client
 * @author Bahar Kaviani
 */
public class Client implements Runnable {
    // initialize socket and input output streams
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    private GraphicGround ground;
    private boolean turn, closed;
    private MouseClick mouseListener;
    private Server server;

    public Client(String address, int port) {
        ground = new GraphicGround("Client");
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mouseListener = new MouseClick(ground, player1, player2, this);
                ground.getGround()[i][j].addMouseListener(mouseListener);
            }
        }

        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * run the client.
     */
    @Override
    public void run() {
        System.out.println("C1: client's turn: " + this.isTurn());
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * close the client and server.
     */
    void close() {
        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
            closed = true;
        } catch (IOException e) {
            System.out.println();
        }
        if(!server.isClosed())
            server.close();
    }

    /**
     * send the changes to the server and want server to receive them.
     * @param currentSquare the first clicked button
     * @param newSquare the second clicked button
     */
    void sendingInformation(Square currentSquare, Square newSquare){
        try {
            String str = currentSquare.toString() + newSquare.toString();
            if(!ground.isTurn())
                str += "true";
            else
                str += "false";
            System.out.println("C2: " + str);
            out.println(str);
            out.flush();
            System.out.println("C3");
        } catch (IOError e) {
            e.printStackTrace();
        }
        server.receivingInformation();
    }

    /**
     * receive the changes from server.
     */
    void receivingInformation(){
        Square currentSquare = null, newSquare = null;
        String str;
        try {
            str =  in.readLine();
            System.out.println("C4: " + str);
            // play
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (ground.getGround()[i][j].getRow() == (str.charAt(0) - '0'))
                        if (ground.getGround()[i][j].getColumn() == (str.charAt(2) - '0'))
                            currentSquare = ground.getGround()[i][j];
                    if (ground.getGround()[i][j].getRow() == (str.charAt(4) - '0'))
                        if (ground.getGround()[i][j].getColumn() == (str.charAt(6) - '0'))
                            newSquare = ground.getGround()[i][j];
                }
            }
            boolean turn;
            if(str.substring(str.lastIndexOf(",") + 1).equals("true")) {
                ground.setTurn(true);
                ground.setColorForTurn(Color.WHITE);
                System.out.println("C5");
                turn = true;
            }
            else {
                ground.setTurn(false);
                ground.setColorForTurn(Color.BLACK);
                System.out.println("C6");
                turn = false;
            }
            ground.setTurn(turn);
            mouseListener.play(currentSquare, newSquare);
            System.out.println("C7: play");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setServer(Server server) {
        this.server = server;
    }

    void setTurn(boolean turn) {
        this.turn = turn;
    }

    boolean isTurn() {
        return turn;
    }

    boolean isClosed() {
        return closed;
    }
}

