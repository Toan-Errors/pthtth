package bai6UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            DatagramSocket s = new DatagramSocket();
            
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Nhập n: ");
            String n = br.readLine();
            byte[] b = n.getBytes();
            DatagramPacket pkt = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 7777);
            s.send(pkt);

            byte[] dang = new byte[6000];
            DatagramPacket nhandang = new DatagramPacket(dang, dang.length);
            s.receive(nhandang);
            String laydang = new String(nhandang.getData(), 0, nhandang.getLength());
            System.out.println("Server gửi đến >> \n" + laydang);

            byte[] arr = new byte[6000];
            DatagramPacket nhan = new DatagramPacket(arr, arr.length);
            s.receive(nhan);
            String fromServer = new String(nhan.getData(), 0, nhan.getLength());
            System.out.println("Server gửi đến >> \n" + fromServer.replace(",", "\n"));

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
