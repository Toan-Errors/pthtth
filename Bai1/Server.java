package Bai1;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public final static int serverPort = 7; 

    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket ss = new ServerSocket(serverPort);
            System.out.println("Server da duoc tao");
            Socket s = ss.accept();
            DemSo ds = new DemSo(s);
            ds.start();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
