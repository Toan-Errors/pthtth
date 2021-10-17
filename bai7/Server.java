package bai7;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

            //Gui file
            if(docfile.exists()){
                FileInputStream fo = new FileInputStream(docfile);
                int n = fo.available();
                byte fileread[] = new byte[60000];
                fo.read(fileread, 0, n);
                DatagramPacket dp_send = new DatagramPacket(fileread, fileread.length, dp_receive.getAddress(), dp_receive.getPort());
                s.send(dp_send);
                System.out.println(new String(dp_send.getData(), 0, dp_send.getLength()));
                System.out.println("Gui file thanh cong");
                fo.close();
            } else {
                String err_notfound = "Khong tim thay file";
                System.out.println(err_notfound);
                DatagramPacket dp_send_err = new DatagramPacket(err_notfound.getBytes(), err_notfound.length(), dp_receive.getAddress(), dp_receive.getPort());
                s.send(dp_send_err);
            }
            s.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
