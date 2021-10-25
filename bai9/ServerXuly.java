package bai9;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerXuly extends Thread{
    DatagramSocket s;
    public ServerXuly(DatagramSocket s){
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Nhan
                byte n[] = new byte[6000];
                DatagramPacket dp_receice = new DatagramPacket(n, n.length);
                s.receive(dp_receice);
                String file = new String(dp_receice.getData(), 0, dp_receice.getLength());
                //Xu ly
                File docfile = new File(file);
                System.out.println("Dang doc file: " + docfile.getName());
                byte files[];
                if(docfile.exists()){
                    FileInputStream fis = new FileInputStream(docfile);
                    byte readfile[] = new byte[50000];
                    fis.read(readfile);
                    files = readfile;
                } else {
                    files = "Khong tim thay file".getBytes();
                    System.out.println("Khong tim thay file");
                }

                //Gui
                DatagramPacket dp_send = new DatagramPacket(files, files.length, dp_receice.getAddress(), dp_receice.getPort());
                s.send(dp_send);
                System.out.println("Gui noi dung file thanh cong");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        super.run();
    }
}
