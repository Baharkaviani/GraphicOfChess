// A Java program for a Server
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bahar Kaviani
 */
public class Server {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    // constructor with port
    Server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException e) {
                    System.out.println();
                }
            }

            // Receiving object over network
            Main obj = null;
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            try {
                obj = (Main) objectInputStream.readObject();
                System.out.println("received:\n" + obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}

