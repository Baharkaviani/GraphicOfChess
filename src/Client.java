// A Java program for a Client
import java.net.*;
import java.io.*;

/**
 * @author Bahar Kaviani
 */
public class Client implements Runnable {
    // initialize socket and input output streams
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    private GraphicGround ground;
    private boolean turn;
    private MouseClick mouseListener;


    // constructor to put ip address and port
    public Client(String address, int port) {
        ground = new GraphicGround("Client");
        Player player1 = new Player("white");
        Player player2 = new Player("Black");
        player1.putPiecesOnGround(ground);
        player2.putPiecesOnGround(ground);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mouseListener = new MouseClick(ground, player1, player2);
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

    @Override
    public void run() {
        System.out.println("C1");
        System.out.println("C2");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("C3");
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("C4");
        } catch (IOException e) {
            System.out.println();
        }
        ground.setgPlay(false);
        System.out.println("C5");
        while (out != null) {
            if (ground.isTurn() == turn) {
                System.out.println("isTurn");
                if (ground.isgPlay()) {
                    System.out.println("C6");
                    //Sending object over network
                    Square currentSquare = ground.getCurrentSquare();
                    Square newSquare = ground.getNewSquare();
                    try {
                        String str = currentSquare.toString() + newSquare.toString();
                        if(ground.isTurn())
                            str += "true";
                        else
                            str += "false";
                        System.out.println("C7: " + str);
                        out.println(str);
                        System.out.println("C8: sending: " + currentSquare.toString());
                        System.out.println("C9: sending: " + newSquare.toString());
                        ground.setgPlay(false);
                        out.flush();
                        System.out.println("C10");
                    } catch (IOError e) {
                        e.printStackTrace();
                    }
                }
            } else if (!ground.isgPlay()) {
                System.out.println("C11");
                //Sending object over network
                Square currentSquare = null, newSquare = null;
                String str;
                try {
                    str =  in.readLine();
                    System.out.println("C12: " + str);
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
                        System.out.println("C13: truuuuuuuuuuuuuuuuue");
                        turn = true;
                    }
                    else {
                        System.out.println("C14: faaaaaaaaaaaaaaaalse");
                        turn = false;
                    }
                    ground.setTurn(turn);
                    mouseListener.play(currentSquare, newSquare);
                    System.out.println("C15");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    public GraphicGround getGround() {
        return ground;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}

