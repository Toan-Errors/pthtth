package bai5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        
        try {
            DatagramSocket s = new DatagramSocket();
            String str;
            do{
                System.out.println("-----------Menu------------");
                System.out.println("Chon chuc nang");
                System.out.println("1. Time");
                System.out.println("2. Date");
                System.out.println("3. Date time");
                System.out.println("0. exit");
                System.out.println("---------------------------");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.print("Lua chon: ");
                str = br.readLine();
                byte[] data = str.getBytes();
                DatagramPacket pkt = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 7777);
                s.send(pkt);

                byte[] arr = new byte[6000];
                DatagramPacket nhan = new DatagramPacket(arr, arr.length);
                s.receive(nhan);
                String fromServer = new String(nhan.getData(), 0, nhan.getLength());
                System.out.println("Server tra ve >> " + fromServer);
            }while(str != "0");
        } catch (Exception e) {
        }
    }
}
