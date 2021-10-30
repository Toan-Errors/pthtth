package SendMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    private int port;
    private InetAddress clientIP;
    private int clientPort;
    public static Map<DatagramPacket, Integer> ListSK; 

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        Server.ListSK = new HashMap<DatagramPacket, Integer>();
        Server server = new Server(7777);
        server.execute();
    }

    private void execute() throws IOException {
        DatagramSocket server = new DatagramSocket(port);
        System.out.println("Server started");
        
        new WriteServer(server).start();
        new Thread(() -> {
            try {
                while(true){
                    
                    String sms = receiveData(server);
                    for(DatagramPacket item : ListSK.keySet()) {
                        if((item.getAddress().equals(clientIP) && item.getPort() == clientPort)){
                            if(sms.contains(":")){
                                System.out.println((sms));
                                DatagramPacket send = SendData("Server: " + Handle(sms), item.getAddress(), item.getPort());
                                server.send(send);
                            } else {
                                System.out.println(sms);
                            }
                        }
                    }

                    DatagramPacket packet = SendData(Menu(), clientIP, clientPort);
                    server.send(packet);
                } 
            } catch (Exception e) {
                //TODO: handle exception
            }   
        }).start(); 
    }

    public String Handle(String sms){
        String msg = sms.split(": ")[1];
        String kq = "";
        int chon = Integer.parseInt(msg);
        switch (chon){
            case 0:
                kq = "ban da chon lua nay\n";
                break;
            default:
                kq = "chua co chuc nang nay\n";
                break;
        }
        return kq;
    }

    private String Menu(){
        return "---------------MENU--------------\n"
                +"1. 1 \n"
                +"2. 1 \n"
                +"3. 1 \n"
                +"4. 1 \n"
                +"5. 1 \n"
                +"----------------END--------------\n"
                +"nhap lua chon: ";

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

    private String receiveData(DatagramSocket server) throws IOException{
        byte buf[] = new byte[1024];
        
        DatagramPacket receive = new DatagramPacket(buf, buf.length);
        server.receive(receive);
        clientIP = receive.getAddress();
        clientPort = receive.getPort();
        checkDuplicate(receive);
        return new String(receive.getData(), 0, receive.getLength());
    }

    private void checkDuplicate(DatagramPacket packet) {
        for (DatagramPacket item : ListSK.keySet()) {
            if(item.getAddress().equals(packet.getAddress()) && item.getPort() == packet.getPort()) {
                ListSK.replace(item, 0);
                return;
            } 
        }
        ListSK.put(packet, 0);
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
                for(DatagramPacket item : Server.ListSK.keySet()){
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
