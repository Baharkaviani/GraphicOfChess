// A Java program for a Client
import java.net.*;
import java.io.*;

/**
 * @author Bahar Kaviani
 */
public class Client implements Runnable{
    // initialize socket and input output streams
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private GraphicGround ground;

    // constructor to put ip address and port
    public Client(String address, int port) {
        ground = new GraphicGround("Client");
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                MouseClick mouseListener = new MouseClick(ground, player1, player2);
                ground.getGround()[i][j].addMouseListener(mouseListener);
            }
        }

        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            this.run();
        }
        catch(IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        try {
//            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            System.out.println();
        }

        ground.setgPlay(false);
        while (out != null) {
            // keep reading until "Over" is input
            if(ground.isTurn()) {
//                //print
                System.out.println("isTurn" + ground.isTurn());
//                //
                if(ground.isgPlay()) {
                    //Sending object over network
                    Square currentSquare = ground.getCurrentSquare();
                    Square newSquare = ground.getNewSquare();
                    try {
                        out.writeObject(currentSquare);
                        System.out.println("sending:\n" + currentSquare);
                        out.flush();
                        out.writeObject(newSquare);
                        System.out.println("sending:\n" + newSquare);
                        ground.setgPlay(false);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // reads message from client until "Over" is sent
            else {
//            System.out.println("iii");
            }
        }

        // close the connection
        try {
//            in.close();
            out.close();
            socket.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
//        new Thread(client).start();
    }
}

