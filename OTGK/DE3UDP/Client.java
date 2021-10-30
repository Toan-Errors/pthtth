package OTGK.DE3UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
        String sms = name + " đã kết nối !!!!!";
        
        DatagramPacket dp_send = new DatagramPacket(sms.getBytes(), sms.length(), host, port);
        s.send(dp_send);

        new WriteClient(s, host, port, name).start();
        new ReadClient(s).start();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(7777, InetAddress.getByName("localhost"));
        client.execute();
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
            String sms = "";
            while (!sms.equals("het")) {
                sms = scanner.nextLine();
                String sms1 = name + ": " + sms;
                DatagramPacket dp = new DatagramPacket(sms1.getBytes(), sms1.length(), host, port);
                client.send(dp);
            }
            client.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
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
            String sms = "";
            while (!sms.equals("het")) {
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                client.receive(packet);
                sms = new String(packet.getData(), 0, packet.getData().length);
                System.out.println(sms);
            }
            client.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
