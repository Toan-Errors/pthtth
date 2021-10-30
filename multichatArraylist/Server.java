package multichatArraylist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    private int port;
    private int clientPort;
    private InetAddress clientHost;
    public static ArrayList<DatagramPacket> ListSK;

    public Server(int port){
        this.port = port;
        ListSK = new ArrayList<DatagramPacket>();
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server(7777);
        server.execute();
    }

    public void execute() throws SocketException{
        DatagramSocket server = new DatagramSocket(port);
        System.out.println("Server started");
        
        new WriteServer(server).start();
        new Thread(() -> {
            try {
                while (true) {
                    String sms = ReceiveData(server);
                    for(DatagramPacket item : ListSK) {
                        if(!(item.getAddress().equals(clientHost) && item.getPort() == clientPort)){
                            DatagramPacket send = SendData(sms, item.getAddress(), item.getPort());
                            server.send(send);
                        }
                    }
                    System.out.println(sms);
                    
                    if(!sms.contains("đã kết nối")){
                        GhiFile(sms);
                    }
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }).start();    
    }

    private void GhiFile(String sms){
        try {
            String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\multichatArraylist";
            String[] str = sms.split(": ");
            String name = str[0];
            String msg = str[1];
            String filepath = path + "\\" + name + ".txt";
            File file = new File(filepath);
            
            FileWriter writer = new FileWriter(file, true);
            writer.append(msg + "\n");
            writer.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private String ReceiveData(DatagramSocket server) throws IOException{
        byte data[] = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        server.receive(packet);
        clientHost = packet.getAddress();
        clientPort = packet.getPort();
        CheckArraysList(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    private void CheckArraysList(DatagramPacket packet){
        for (DatagramPacket data : ListSK) {
            if(data.getAddress().equals(packet.getAddress()) && data.getPort() == packet.getPort()) {
                ListSK.remove(data);
                break;
            }
        }
        ListSK.add(packet);
    }

    private DatagramPacket SendData(String sms, InetAddress address, int port){
        return new DatagramPacket(sms.getBytes(), sms.length(), address, port);
    }
}

class WriteServer extends Thread {
    private DatagramSocket server;

    public WriteServer(DatagramSocket server){
        this.server = server;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String sms = scanner.nextLine();
            try {
                for(DatagramPacket item : Server.ListSK){
                    DatagramPacket send = SendPark("Server: " + sms, item.getAddress(), item.getPort());
                    server.send(send);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

    private DatagramPacket SendPark(String sms, InetAddress address, int port){
        return new DatagramPacket(sms.getBytes(), sms.length(), address, port);
    }
}

