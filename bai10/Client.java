package bai10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

import bai9.client;

public class Client {

    public static int clientport = 7777;
    public static String host = "localhost";

    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("Usage: UDPClient " + "Now using host = " + host + ", Port# = " + clientport);
         } 
         // Get the port number to use from the command line
         else {      
            //host = args[0];
            clientport = Integer.valueOf(args[0]).intValue();
            System.out.println("Usage: UDPClient " + "Now using host = " + host + ", Port# = " + clientport);
         } 
        
        try {
            DatagramSocket s = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ten client: ");
            String client = sc.nextLine();
            SenderMsg(s,client);
            System.out.println(client  + " duoc tao thanh cong");
            String msg_in = "";  

            do{
                System.out.println("Nhap tin nhan can gui nhap 'HET' de ket thuc");
                System.out.print("Nhap >> ");
                msg_in = sc.nextLine();
                // Send
                SenderMsg(s,msg_in);     
            } while(!"het".equals(msg_in));

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public static void SenderMsg(DatagramSocket s, String msg) throws IOException{
        DatagramPacket dp_send = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName(host), clientport);
        s.send(dp_send);
    }
}
