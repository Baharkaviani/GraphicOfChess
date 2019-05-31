// A Java program for a Server
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bahar Kaviani
 */
public class Server implements Runnable{
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private ObjectInputStream in = null;

    private GraphicGround ground;
    private Player player1;
    private Player player2;
    private MouseClick mouseListener;

    private Server(int port) {
        ground = new GraphicGround("Server");
        player1 = new Player("white");
        player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mouseListener = new MouseClick(ground, player1, player2);
                ground.getGround()[i][j].addMouseListener(mouseListener);
            }
        }

        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        // takes input from the client socket
        try {
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            // reads message from client until "Over" is sent
            while (in != null) {
                // keep reading until "Over" is input
                if (ground.isTurn()) {
//                    //print
//                    System.out.println("isTurn" + !ground.isTurn());
//                    //
                    if (!ground.isgPlay()) {
                        //Sending object over network
                        //print
                        System.out.println("isNotPlay");
                        System.out.println("receiving object over network");
                        //
                        Square currentSquare, newSquare;
                        try {
                            currentSquare = (Square) in.readObject();
                            System.out.println("received:\n" + currentSquare);
                            newSquare = (Square) in.readObject();
                            System.out.println("received:\n" + newSquare);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
//                    System.out.println("iii");
                }
            }
        } catch (IOException e){
            System.out.println();
        }

        // close the connection
        try {
            in.close();
            socket.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        new Thread(server).start();
    }
}

