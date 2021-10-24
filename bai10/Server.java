package bai10;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        DatagramSocket s = null;
        try {
            s = new DatagramSocket(7777);
            System.out.println("UDP server da khoi tao");
            for(int i = 0; i <2; i++) {
                Thread t = new ServerXuly(s);
                t.start();
                System.out.println(t.getName());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
