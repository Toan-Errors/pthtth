package SendMenu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private InetAddress address;
    private int port;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public void execute() throws IOException {
        DatagramSocket client = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        if(name.contains(":")){
            System.out.println("Lỗi");
        } else {
            
            client.send(SendData(name + " da ket noi"));

            new ReadClient(client).start();
            new WriteClient(client, address, port, name).start();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(InetAddress.getLocalHost(), 7777);
        client.execute();
    }

    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), address, port);
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
            while(true){
                String sms = receiveData();
                System.out.print(sms);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        super.run();
    }

    private String receiveData() throws IOException{
        byte[] buf = new byte[6000];
        DatagramPacket receive = new DatagramPacket(buf, buf.length);
        client.receive(receive);
        return new String(buf);
    }
}

class WriteClient extends Thread {
    private DatagramSocket client;
    private InetAddress address;
    private int port;
    private String name;

    public WriteClient(DatagramSocket client, InetAddress address, int port, String name) {
        this.client = client;
        this.address = address;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {
        while(true){
            try {
                Scanner scanner = new Scanner(System.in);
                String sms = scanner.nextLine();
                DatagramPacket dp = SendData(name + ": " + sms);
                client.send(dp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private DatagramPacket SendData(String sms){
        return new DatagramPacket(sms.getBytes(), sms.length(), address, port);
    }
}