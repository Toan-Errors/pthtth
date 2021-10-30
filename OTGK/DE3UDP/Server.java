package OTGK.DE3UDP;

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
                        if((item.getAddress().equals(clientHost) && item.getPort() == clientPort)){
                            if(sms.contains(":")){
                                System.out.println("Hệ thông: Nhận chuỗi từ " + sms.split(": ")[0] + " với nội dung \"" + sms.split(": ")[1] + "\" tiến hành đảo ngược và lưu vào file");
                                GhiFile(sms);
                                String s = "Server: Lưu chuỗi đảo ngược thành công                               ";
                                DatagramPacket send = new DatagramPacket(s.getBytes(), s.length(), item.getAddress(), item.getPort());
                                server.send(send);
                                break;
                            } else {
                                System.out.println(sms);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }).start();    
    }

    private String DaoNguocChuoi(String sms){
        StringBuilder s = new StringBuilder(sms);
        return s.reverse().toString();
    }

    private void GhiFile(String sms){
        try {
            String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\OTGK\\DE3UDP";
            String[] str = sms.split(": ");
            String name = str[0];
            String msg = DaoNguocChuoi(str[1]);
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