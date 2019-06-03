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
    private BufferedReader in = null;
    private PrintWriter out = null;

    private GraphicGround ground;
    private boolean turn;
    private MouseClick mouseListener;

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

    @Override
    public void run() {
        System.out.println("S1");
        // takes input from the client socket
        try {
            socket = server.accept();
            System.out.println("S2");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("S3");
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("S4");
            // reads message from client
            ground.setgPlay(false);
            System.out.println("S5");
        } catch (IOException e){
            System.out.println();
        }

        while (in != null) {
            if(ground.isTurn() == turn){
                System.out.print("S");
                if(ground.isgPlay()) {
                    System.out.println("S6");
                    sendingInformation();
                }
            }
            else {
                System.out.println("S11");
                receivingInformation();
            }
        }

        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException e) {
            System.out.println();
        }
    }

    public void sendingInformation(){
        try {
            Square currentSquare = ground.getCurrentSquare();
            Square newSquare = ground.getNewSquare();
            String str = currentSquare.toString() + newSquare.toString();
            if(ground.isTurn())
                str += "true";
            else
                str += "false";
            out.println(str);
            System.out.println("S7: " + str);
            System.out.println("S8 sending: " + currentSquare.toString());
            System.out.println("S9 sending: " + newSquare.toString());
            ground.setgPlay(false);
            out.flush();
            System.out.println("S10");
        } catch (IOError e) {
            e.printStackTrace();
        }
    }

    public void receivingInformation(){
        Square currentSquare = null, newSquare = null;
        String str;
        try {
            str =  in.readLine();
            System.out.println("S12: " + str);
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
                System.out.println("S13: truuuuuuuuuuuuuuuuue");
                turn = true;
            }
            else {
                ground.setTurn(false);
                System.out.println("S14: faaaaaaaaaaaaaaaalse");
                turn = false;
            }
            ground.setTurn(turn);
            mouseListener.play(currentSquare, newSquare);
            System.out.println("S15");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public GraphicGround getGround() {
        return ground;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}