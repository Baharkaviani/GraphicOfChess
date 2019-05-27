// A Java program for a Client
import java.net.*;
import java.io.*;

/**
 * @author Bahar Kaviani
 */
public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Main main;

    // constructor to put ip address and port
    public Client(String address, int port) {
        main = new Main();

        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException e) {
            System.out.println();
        }

        // string to read message from input
        String line = "";
        // takes input from the Server socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        main.getGround().setgPlay(false);
//        while (!line.equals("Over")) {
//            // keep reading until "Over" is input
//            if(main.getGround().isTurn()) {
//                if(main.getGround().isgPlay()) {
//                    //Sending object over network
//                    //print
//                    System.out.println("sending object over network");
//                    //
//                    Square currentSquare = main.getGround().getCurrentSquare();
//                    Square newSquare = main.getGround().getNewSquare();
//                    try {
//                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
//                        objectOutputStream.writeObject(currentSquare);
//                        System.out.println("sending:\n " + currentSquare);
//                        objectOutputStream.writeObject(newSquare);
//                        System.out.println("sending:\n " + newSquare);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        line = input.readLine();
//                        out.writeUTF(line);
//                    } catch (IOException e) {
//                        System.out.println();
//                    }
//                }
//            }
//            // reads message from client until "Over" is sent
//            else {
//                if (main.getGround().isgPlay()) {
//                    try {
//                        line = in.readUTF();
//                        System.out.println(line);
//                    } catch (IOException e) {
//                        System.out.println();
//                    }
//                }
//            }
//            main.getGround().setgPlay(false);
//        }

//        //Sending object over network
//        Square currentSquare = main.getGround().getCurrentSquare();
//        Square newSquare = main.getGround().getNewSquare();
//        try {
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
//            objectOutputStream.writeObject(currentSquare);
//            System.out.println("sending:\n " + currentSquare);
//            objectOutputStream.writeObject(newSquare);
//            System.out.println("sending:\n " + newSquare);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // close the connection
//        try {
//            input.close();
//            out.close();
//            socket.close();
//        }
//        catch(IOException e) {
//            System.out.println(e);
//        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }
}

