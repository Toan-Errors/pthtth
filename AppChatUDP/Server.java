package AppChatUDP;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server{
    private int port;
    private int clientPort;
    private InetAddress clientHost;
    public static ListData listData;
    public static Map<String, String> map; 

    public Server(int port){
        this.port = port;
    }

    public static void main(String[] args) throws SocketException {
        map = new HashMap<String, String>();
        Server server = new Server(7777);
        server.execute();
    }

    //Hàm thực thi các chức năng của server
    public void execute() throws SocketException{
        DatagramSocket server = new DatagramSocket(7777);
        System.out.println("Server started");
        //Khởi tạo class ListData
        listData = new ListData(server);
        //Luồng để gửi dữ liệu của server
        new Thread(() -> {
            //Khởi tạo Scanner để nhận dữ liệu từ bàn phím
            Scanner scanner = new Scanner(System.in);
            while(true) {
                //Nhận dữ liệu từ bàn phím
                String sms = scanner.nextLine();
                try {
                    //Gửi dữ liệu từ bàn phím cho tất cả các client
                    listData.SendMsgByServer(sms);
                } catch (Exception e) {
                    
                }
            }
        }).start();
        //Luồng lắng nghe các yêu cầu của client
        new Thread(() -> {
            try {
                while (true) {
                    //Hàm nhận dữ liệu từ client
                    ReceiveData(server);
                }
            } catch (Exception e) {
            }
        }).start();    
    }
    
    private void ReceiveData(DatagramSocket server) throws IOException, InterruptedException{
        //Khởi tạo một mảng byte để nhận dữ liệu
        byte data[] = new byte[1024];
        //Khởi tạo một đối tượng để nhận dữ liệu
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //Nhận dữ liệu từ client
        server.receive(packet);
        //Chuyển dữ liệu từ byte sang String
        String msg = new String(packet.getData(), 0, packet.getLength());
        //Kiểm tra client đã kết nối hay chưa
        if(msg.contains(" da ket noi !!")){
            //Cắt chuỗi để lấy ra tên client
            String[] arr = msg.split(": ");
            //Lấy tên của client
            String name = arr[0];
            //Thêm client vào listData
            listData.addData(new DataClient(packet.getAddress(), packet.getPort(), name));
            //In ra thông báo khi client đã kết nối
            System.out.println(msg);
            //Gửi thông báo cho các client khác đã kết nối
            listData.SendMsgByServer(msg);
            //Kiểm tra lịch sử chat của client
            String listsu = listData.LoadLichSu(name);;
            if(!listsu.equals("")){
                String str = "Lich su chat: \n| "+ listsu +" | ket thuc";
                //Gửi lịch sử chat cho client
                DatagramPacket p = new DatagramPacket(str.getBytes(), str.length(), packet.getAddress(), packet.getPort());
                server.send(p);
            }
            return;
        }
        //Gán clientHost và clientPort
        clientHost = packet.getAddress();
        clientPort = packet.getPort();
        //Gửi dữ liệu từ client cho tất cả các client
        listData.SendMsg(msg ,clientHost, clientPort);
    }
}

