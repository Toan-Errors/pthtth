package bai07;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhap dia chi server: ");
            String address = br.readLine();
            InetAddress add_server = InetAddress.getByName(address);
            System.out.print("File can gui: ");
            String file = br.readLine();
            String yeucau = "READ " + file;
            
            //Gui file
            DatagramPacket dp_send = new DatagramPacket(yeucau.getBytes(), yeucau.length(), add_server, 7777);
            s.send(dp_send);


            //Xu ly doc ten file
            byte filename[] = new byte[6000];
            DatagramPacket dp_recevie_filename = new DatagramPacket(filename, filename.length);
            s.receive(dp_recevie_filename);
            String name = new String(dp_recevie_filename.getData(), 0, dp_recevie_filename.getLength());
            System.out.println("Dang doc file: " + name);

            //Xu ly gui file thanh cong
            byte b[] = new byte[60000];
            
            DatagramPacket dp_recevie = new DatagramPacket(b, b.length);
            s.receive(dp_recevie);
            FileOutputStream fo = new FileOutputStream("C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\file\\" + name);
            String str = new String(b, 0, b.length);
            fo.write(str.getBytes());
            System.out.println("Ghi file thanh cong!!!");

        } catch (Exception e) {
            //  : handle exception
        }
    }
}
