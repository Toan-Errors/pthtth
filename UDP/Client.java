package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //Khởi tạo datagramsocket
        DatagramSocket s = new DatagramSocket();
        //Khởi tạo đối tượng nhập vào
        Scanner scanner = new Scanner(System.in);
        //Tạo 1 luồng để gửi dữ liệu từ bàn phím
        new Thread(() -> {
            while (true) {
                try {
                    //Nhập dữ liệu từ bàn phím và chuyển sang byte[] để gửi
                    String msg = scanner.nextLine();
                    byte[] buf = msg.getBytes();
                    s.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 8888));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        //Tạo 1 luồng để nhận dữ liệu từ server
        new Thread(() -> {
            while(true){
                try {
                    //Khởi tạo mảng byte để nhận dữ liệu từ server và chuyển sang String để in ra màn hình
                    byte[] buf = new byte[1024];
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    s.receive(p);
                    System.out.println(new String(buf));
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        }).start();
    }
}
