package bai10;

import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("UDP server da khoi tao");
            
            new ServerXuly(s).start();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
