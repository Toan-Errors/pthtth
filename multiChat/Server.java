package multiChat;

import java.net.DatagramSocket;

public class Server {
    private DatagramSocket s;

    public Server(DatagramSocket s) {
        this.s = s;
    }

    public void startServer(){
        try {
            while(!s.isClosed()){
                s = new DatagramSocket(7777);
                
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public static void main(String[] args) {
    }
}
