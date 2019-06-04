import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A Java program for a Server
 * @author Bahar Kaviani
 */
public class Server implements Runnable{
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    private GraphicGround ground;
    private boolean turn, closed;
    private MouseClick mouseListener;
    private Client client;

    public Server(int port) {
        ground = new GraphicGround("Server");
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

        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            System.out.println("Client accepted");
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * run the server.
     */
    @Override
    public void run() {
        System.out.println("S1: server's turn: " + this.isTurn());
        // takes input from the client socket
        try {
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            // reads message from client
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * close the server and client.
     */
    void close() {
        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
            closed = true;
        }
        catch(IOException e) {
            System.out.println();
        }
        if (!client.isClosed())
            client.close();
    }

    /**
     * send the changes to the client and want client to receive them.
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
            out.println(str);
            System.out.println("S2: " + str);
            out.flush();
            System.out.println("S3");
        } catch (IOError e) {
            e.printStackTrace();
        }
        client.receivingInformation();
    }

    /**
     * receive the changes from client.
     */
    void receivingInformation(){
        Square currentSquare = null, newSquare = null;
        String str;
        try {
            str =  in.readLine();
            System.out.println("S4: " + str);
            // play
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(ground.getGround()[i][j].getRow() == (str.charAt(0) - '0'))
                        if (ground.getGround()[i][j].getColumn() == (str.charAt(2) - '0'))
                            currentSquare = ground.getGround()[i][j];
                    if(ground.getGround()[i][j].getRow() == (str.charAt(4) - '0'))
                        if (ground.getGround()[i][j].getColumn() == (str.charAt(6) - '0'))
                            newSquare = ground.getGround()[i][j];
                }
            }
            boolean turn;
            if(str.substring(str.lastIndexOf(",") + 1).equals("true")) {
                ground.setTurn(true);
                ground.setColorForTurn(Color.WHITE);
                System.out.println("S5");
                turn = true;
            }
            else {
                ground.setTurn(false);
                ground.setColorForTurn(Color.BLACK);
                System.out.println("S6");
                turn = false;
            }
            ground.setTurn(turn);
            mouseListener.play(currentSquare, newSquare);
            System.out.println("S7: play");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setClient(Client client) {
        this.client = client;
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