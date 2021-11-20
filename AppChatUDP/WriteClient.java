package AppChatUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Tạo một lớp DataClient để gửi dữ liệu từ client đến server
class WriteClient extends Thread{
    private DatagramSocket client;
    private InetAddress host;
    private int port;
    private String name;
    Map<String, String> map;

    //Khởi tạo constructor cho WriteClient
    public WriteClient(DatagramSocket client, InetAddress host, int port, String name){
        this.client = client;
        this.host = host;
        this.port = port;
        this.name = name;
        map = new HashMap<>();
    }
    @Override
    public void run() {
        try {
            //Tạo một luồng Scanner để nhận dữ liệu từ bàn phím
            Scanner scanner = new Scanner(System.in);
            //Tạo một luồng while để chạy vô tận
            while (true) {
                //Nhận dữ liệu từ bàn phím
                String sms = scanner.nextLine();
                //Dùng Map để lưu dữ liệu thành JSON
                map.put("name", name);
                map.put("sms", sms);
                //Chuyển dữ liệu thành String và gửi lên server
                DatagramPacket dp = SendData(map.toString());
                client.send(dp);
                //Kiểm tra tin nhắn có từ exit hay không
                if(sms.equals("exit")){
                    //Thoát
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            //TODO: handle exceptiona
        }
    }

    //Hàm Gửi dữ liệu
    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), host, port);
    }
}