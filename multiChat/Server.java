package multiChat;

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
        new WriteServer(server).start();
        System.out.println("Server started");
        try {
               
            while(true){
                String sms = receiveData(server);
                for(DatagramPacket item : ListSK.keySet()) {
                    if(!(item.getAddress().equals(clientIP) && item.getPort() == clientPort)){
                        DatagramPacket send = SendData(sms, item.getAddress(), item.getPort());
                        server.send(send);
                    }
                }
                System.out.println(sms);
            } 
        } catch (Exception e) {
            //TODO: handle exception
        }    
    }
    private void WriteFile(String sms){
        try {
            String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\multiChat\\";
            String[] str = sms.split(":");
            String name = str[0];
            String msg = str[1];
            String filepath = path + name + ".txt";
            File file = new File(filepath);
            if(!file.exists()) {
                file.createNewFile();
            } 
            FileWriter fw = new FileWriter(file);
            fw.write(msg);
            fw.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    private String receiveData(DatagramSocket server) throws IOException{
        byte[] buf = new byte[1024];
        
        DatagramPacket receive = new DatagramPacket(buf, buf.length);
        server.receive(receive);
        clientIP = receive.getAddress();
        clientPort = receive.getPort();
        checkDuplicate(receive);
        String str = new String(receive.getData(), 0, receive.getLength());
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        return new String(bytes, "UTF-8");
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
