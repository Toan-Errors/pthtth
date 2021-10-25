package bai9;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("Server da duoc khoi  tao");
            
            new ServerXuly(s).start();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
