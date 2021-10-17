package bai8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");
            String str;

            Scanner sc = new Scanner(System.in);
            System.out.println("OP Operant1 Operant2\\n");
            System.out.print("Nhap khuon dang tren: ");
            str = sc.nextLine();

            DatagramPacket dp_send = new DatagramPacket(str.getBytes(), str.length(), server, 7777);
            s.send(dp_send);
            System.out.println("Gui du lieu thanh cong");

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
