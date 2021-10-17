package bai9;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket();
            
            InetAddress address = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            //Gui
            System.out.print("Nhap ten file can doc: ");
            String filename = sc.nextLine();
            DatagramPacket dp_send = new DatagramPacket(filename.getBytes(), filename.length(), address, 7777);
            s.send(dp_send);
            
            //Nhan
            byte nhan[] = new byte[50000];
            DatagramPacket dp_receive = new DatagramPacket(nhan, nhan.length);
            s.receive(dp_receive);

            System.out.println("Server >> Noi dung file");
            System.out.println(new String(dp_receive.getData(), 0, dp_receive.getLength()));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
