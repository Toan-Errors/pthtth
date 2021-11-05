package TCP;

import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8888);
            System.out.println("Client connected to server");
            System.out.println("Client is sending message to server");
            socket.getOutputStream().write("Hello from client".getBytes());
            System.out.println("Client sent message to server");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
