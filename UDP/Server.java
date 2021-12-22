package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class Server {
    public static void main(String[] args) throws IOException {
        //Khởi tạo datagramsocket với port 8888
        DatagramSocket s = new DatagramSocket(8888);
        System.out.println("Server is running");
        //Tạo 1 luồng để nhận dữ liệu và báo cáo cho client biết
        new Thread(() -> {
            while (true) {
                try {
                    //Khởi tạo mảng byte để nhận dữ liệu từ client và chuyển sang String để in ra màn hình
                    byte[] buf = new byte[1024];
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    s.receive(p);
                    System.out.println(new String(buf));

                    //Gửi dữ liệu từ server về client
                    String msg = "Server da nhan: " + new String(buf);
                    buf = msg.getBytes();
                    s.send(new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
