import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(5000);
        Client client = new Client("127.0.0.1", 5000);
        chooseYourTurn(client, server);
        client.setServer(server);
        server.setClient(client);
    }

    /**
     * the client choose the color of it's pieces.
     */
    public static void chooseYourTurn(Client client, Server server) {
        JFrame choice = new JFrame("Which color do you want?");
        choice.setAlwaysOnTop(true);
        choice.setLayout(new GridLayout());
        choice.setSize(350, 200);
        JButton White = new JButton("White");
        JButton Black = new JButton("Black");
        choice.add(White);
        White.addActionListener(new ChooseTurn(choice, client, server));
        choice.add(Black);
        Black.addActionListener(new ChooseTurn(choice, client, server));
        choice.setVisible(true);
    }
}

        /**
 *
 * @author Bahar Kaviani
 */
class ChooseTurn implements java.awt.event.ActionListener {
    private JFrame frame;
    private Client client;
    private Server server;

    ChooseTurn(JFrame frame, Client client, Server server) {
        this.frame = frame;
        this.client = client;
        this.server = server;
    }

    /**
     * setTurn for ground to show who's turn is it.
     * @param e information of e which actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) (e.getSource())).getText().equals("White")) {
            client.setTurn(true);
//            client.getGround().setTurn(true);
            server.setTurn(false);
//            server.getGround().setTurn(true);
            new Thread(server).start();
            new Thread(client).start();
        } else if ((((JButton) (e.getSource())).getText().equals("Black"))) {
            client.setTurn(false);
//            client.getGround().setTurn(true);
            server.setTurn(true);
//            server.getGround().setTurn(true);
            new Thread(server).start();
            new Thread(client).start();
        }
        frame.dispose();
    }
}