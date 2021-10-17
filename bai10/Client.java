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
            String server = sc.nextLine();
            InetAddress address = InetAddress.getByName(server);
            String msg_in;
            //Send
            do{
                System.out.println("Nhap tin nhan can gui nhap 'HET' de ket thuc");
                System.out.print("Nhap >> ");
                msg_in = sc.nextLine();
                // Send
                DatagramPacket dp_send = new DatagramPacket(msg_in.getBytes(), msg_in.length(), address, 7777);
                s.send(dp_send);

                //Nhan

                byte msg_out[] = new byte[5000];
                DatagramPacket dp_receive = new DatagramPacket(msg_out, msg_out.length);
                s.receive(dp_receive);

                System.out.println("Server >> " + new String(dp_receive.getData(), 0, dp_receive.getLength()));
            } while(msg_in != "het");

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
