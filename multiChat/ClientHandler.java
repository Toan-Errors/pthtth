package multiChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    DatagramSocket s;
    String username;
    DatagramPacket dp_receive;
    DatagramPacket dp_send;
    ArrayList<Client> client = new ArrayList<Client>();
    byte buf[];
    public ClientHandler(DatagramSocket s){
        try {
            this.s = s;
            buf = new byte[6000];
            this.dp_receive = new DatagramPacket(buf, buf.length);
            this.dp_send = new DatagramPacket(buf, buf.length, InetAddress.getByAddress("localhost", buf), 7777);
            s.receive(dp_receive);
            this.username = new String(buf);
            this.client.add(this)
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        try {
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        super.run();
    }

}
