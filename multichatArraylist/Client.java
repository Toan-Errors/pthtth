package multichatArraylist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private int port;
    private InetAddress host;
    
    public Client(int port, InetAddress host) {
        this.port = port;
        this.host = host;
    }

    public void execute() throws IOException{
        DatagramSocket s = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        String name = "Ẩn danh";
        do{
            System.out.print("Nhập tên: ");
            name = sc.nextLine();
        }while(name.contains(":"));
        
        DatagramPacket dp_send = SendData(name + " đã kết nối !!!!!");
        s.send(dp_send);

        new WriteClient(s, host, port, name).start();
        new ReadClient(s).start();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(7777, InetAddress.getByName("localhost"));
        client.execute();
    }

    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), host, port);
    }

}

class WriteClient extends Thread{
    private DatagramSocket client;
    private InetAddress host;
    private int port;
    private String name;

    public WriteClient(DatagramSocket client, InetAddress host, int port, String name){
        this.client = client;
        this.host = host;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String sms = scanner.nextLine();
                DatagramPacket dp = SendData(name + ": " + sms);
                client.send(dp);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), host, port);
    }
}


class ReadClient extends Thread {
    private DatagramSocket client;

    public ReadClient(DatagramSocket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String sms = ReceiveData();
                System.out.println(sms);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private String ReceiveData() throws IOException{
        byte data[] = new byte[6000];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        client.receive(packet);
        return new String(data, 0, data.length);
    }
}