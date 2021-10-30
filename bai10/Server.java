package bai10;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("UDP server da khoi tao");
            
            
            while(true) {
                Thread t = new ServerXuly(s);
                t.start();
                System.out.println(t.getName());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
