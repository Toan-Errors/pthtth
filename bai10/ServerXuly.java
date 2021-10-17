package bai10;

import java.net.DatagramSocket;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.DatagramPacket;

public class ServerXuly extends Thread{
    DatagramSocket s;
    public ServerXuly(DatagramSocket s){
        this.s = s;
    }

    @Override
    public void run() {
        try {
            byte bclient[] = new byte[6000];
            DatagramPacket dp_receive_client = new DatagramPacket(bclient, bclient.length);
            s.receive(dp_receive_client);
            String nameserver = new String(dp_receive_client.getData(), 0, dp_receive_client.getLength());
            do{
                byte msg[] = new byte[6000];
                DatagramPacket dp_receive = new DatagramPacket(msg, msg.length);
                s.receive(dp_receive);
                System.out.println("Nhan tin nhan tu " + nameserver + " Cho xu ly");
                String msg_in = new String(dp_receive.getData(), 0, dp_receive.getLength());
                //Xuly
                String filepath = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\bai10\\file\\" + nameserver + ".txt";
                File file = new File(filepath);
                String notification;
                if(file.exists()) {
                    System.out.println("File da ton tai tien hanh ghi de cho " + nameserver);
                    FileWriter fw = new FileWriter(file, true);
                    fw.append(msg_in + "\n");
                    notification = "ghi de noi dung thanh cong";
                    fw.close();
                } else {
                    if(file.createNewFile()){
                        System.out.println("Tao file va ghi file thanh cong cho " + nameserver);
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(msg_in.getBytes());
                        notification = "tao va ghi file thanh cong";
                        fos.close();
                    } else {
                        notification = "tao file that bai";
                    }
                }
                DatagramPacket dp_send = new DatagramPacket(notification.getBytes(), notification.length(), dp_receive.getAddress(), dp_receive.getPort());
                s.send(dp_send);
            } while(true);
        } catch (Exception e) {
            //TODO: handle exception
        }
        super.run();
    }
}
