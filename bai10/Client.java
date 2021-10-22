package bai10;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ten server: ");
            String client = sc.nextLine();
            DatagramPacket dp_send_client = new DatagramPacket(client.getBytes(), client.length(), InetAddress.getByName("localhost"), 7777);
            s.send(dp_send_client);
            System.out.println("Client: " + client + " duoc tao thanh cong");
            String msg_in;  
            //Send
            do{
                System.out.println("Nhap tin nhan can gui nhap 'HET' de ket thuc");
                System.out.print("Nhap >> ");
                msg_in = sc.nextLine();
                // Send
                DatagramPacket dp_send = new DatagramPacket(msg_in.getBytes(), msg_in.length(), InetAddress.getByName("localhost"), 7777);
                s.send(dp_send);

                //Nhan

                byte msg_out[] = new byte[5000];
                DatagramPacket dp_receive = new DatagramPacket(msg_out, msg_out.length);
                s.receive(dp_receive);

                System.out.println("Server >> " + new String(dp_receive.getData(), 0, dp_receive.getLength()));
            } while(!"het".equals(msg_in));

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
