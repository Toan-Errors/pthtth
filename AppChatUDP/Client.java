package AppChatUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private int port;
    private InetAddress host;
    private static String name;
    
    //Khởi tạo giá trị cho các biến
    public Client(int port, InetAddress host, String name) {
        this.port = port;
        this.host = host;
        this.name = name;
    }

    //Hàm thực thi các chức năng của client
    public void execute() throws IOException{
        //Tạo socket để gửi và nhận dữ liệu
        DatagramSocket s = new DatagramSocket();
        //Tạo một đối tượng để gửi dữ liệu
        DatagramPacket dp_send = SendData(name + ": da ket noi !!!!!");
        //Gửi dữ liệu
        s.send(dp_send);

        //Tạo 1 luồng gửi dữ liệu
        new WriteClient(s, host, port, name).start();
        //Tạo 1 luông nhận dữ liệu
        new ReadClient(s).start();
        //2 Luồng cùng chạy song song sẽ không bị ngắt
    }

    //Hàm main của client
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //Tên mặc định của client là Anonymous nếu người dùng không nhập tên
        name = "Anonymous";
        do{
            System.out.print("Nhập tên: ");
            name = sc.nextLine();
        //Không cho phép nhập tên có dấu ":" vì sẽ làm sai các chức năng của client
        }while(name.contains(":"));
        //Khởi tạo client với các giá trị đã nhập
        Client client = new Client(7777, InetAddress.getByName("localhost"), name);
        //Chạy hàm thực thi của client
        client.execute();
    }

    //Hàm gửi dữ liệu đi
    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), host, port);
    }
}


