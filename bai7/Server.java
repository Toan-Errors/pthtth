package bai7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("Tao thanh cong server tren port 7777");

            //Nhận file
            byte b[] = new byte[6000];
            DatagramPacket dp_receive = new DatagramPacket(b, b.length);
            s.receive(dp_receive);
            //
            String kq = new String(dp_receive.getData(), 0, dp_receive.getLength());
            System.out.println(kq);
            String file = kq.substring("READ ".length(), kq.length());
            File docfile = new File(file);
            //Name file
            System.out.println("Dang doc file: " + docfile.getName());
            byte[] filename = docfile.getName().getBytes();
            DatagramPacket dp_send_filename = new DatagramPacket(filename, filename.length, dp_receive.getAddress(), dp_receive.getPort());
            s.send(dp_send_filename);
            String thongbao = "lỗi";
            String filepath = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\bai7\\file\\" + docfile.getName();
            //Gui file
            if(docfile.exists()){
                String data= "";
                Scanner myReader = new Scanner(docfile);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }
                myReader.close();
                System.out.println("Noi dung file >> \n" + data);
                File ghifile = new File(filepath);
                if(!ghifile.exists()){
                    ghifile.createNewFile();
                }
                FileWriter fw = new FileWriter(ghifile);
                fw.write(data);

                thongbao = "Thanh cong";
                fw.close();
            } 

            DatagramPacket dp_send = new DatagramPacket(thongbao.getBytes(), thongbao.length(), dp_receive.getAddress(), dp_receive.getPort());
            s.send(dp_send);

            s.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
