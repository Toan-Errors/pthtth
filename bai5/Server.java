package bai5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("UDP server da dc tao");
            byte[] arr = new byte[6000];
            while(true){
                DatagramPacket nhan = new DatagramPacket(arr, arr.length);
                s.receive(nhan);

                String str = new String(nhan.getData(), 0, nhan.getLength());
                System.out.println("Client >> " + str);

                String str1 = Xuly(Integer.parseInt(str));

                DatagramPacket gui = new DatagramPacket(str1.getBytes(), str1.length(), nhan.getAddress(), nhan.getPort());
                s.send(gui);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public static String Xuly(int s){
        java.util.Date date = new java.util.Date();
        switch(s){
            case 1:
                return LocalTime.now().toString();
            case 2:
                return LocalDate.now().toString();
            case 3:
                return date.toString();
            default :
                return "Chon sai";
        }
    }
}
