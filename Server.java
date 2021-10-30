import java.io.IOException; 
import java.io.UnsupportedEncodingException; 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

public Hashtable<String, InetAddress> table;
public Hashtable<String, Integer> tab;
DatagramSocket sock = new DatagramSocket(5050);
DatagramPacket pack;
private String str[];
int Port;

Server(String str[]) throws SocketException, UnsupportedEncodingException, IOException {
    this.str = str;
    String Sname = str[2];
    System.out.println(Sname);
    byte[] data = new byte[1000];
    pack = new DatagramPacket(data, data.length);
    sock.receive(pack);
    String s = new String(data, "UTF-8");
    String s1 = s.replaceAll("\n", " ");
    StringTokenizer st = new StringTokenizer(s1, " ");
    int f1 = 0, f2 = 0, f3 = 0;
    String sender;

    while (st.hasMoreTokens()) {
        String s2 = st.nextToken();
        if (s2.equals("Via:")) {
            if (st.nextToken().equals(Sname)) {
                f1 = 1;
            }
        } else if (s2.equals("To:")) {
            if (st.nextToken().equals(Sname)) {
                f2 = 1;
            }
        }
        else if(s2.equals("From:"))
        {
            sender = st.nextToken();
            table.put(sender, pack.getAddress());
        }
        else if (s2.equals("Port:")) {
            Port = Integer.parseInt(st.nextToken());
            if (tab.get(Port) != null) {
                f3 = 1;
            }
        }
    }
    if ((f1 == 1) && (f2 == 1) && (f3 == 1)) {
        new Thread(this).start();
    } else {
        if (f1 == 0) {
            System.out.println("Warning: Server name mismatch. Message dropped.");
        }
        if (f2 == 0) {
            System.out.println("Warning: Server name mismatch. Message dropped.");
        }
        if (f3 == 0) {
            System.out.println("Warning: Unknown recipient. Message dropped.");
        }
    }
}

public void run() {
    while (true) {
        try {
            String s;
            String namefind;
            String name = null;
            byte[] data = new byte[1000];
            DatagramPacket receivePack = new DatagramPacket(data, data.length);
            sock.receive(receivePack);
            System.out.println(new String(receivePack.getData()));
            s = new String(data, "UTF-8");
            String s1 = s.replaceAll("\n", " ");
            StringTokenizer st = new StringTokenizer(s1, " ");
            while (st.hasMoreTokens()) {
                namefind=st.nextToken();
                if (namefind.equals("Body:")) {
                    namefind = st.nextToken();
                    StringTokenizer namefind2 = new StringTokenizer(namefind, "$");
                    name = namefind2.nextToken();
                    tab.put(name, Port);
                }
            }
            sendPackToClient(receivePack,name);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
public void sendPackToClient(DatagramPacket receivePack,String name) throws IOException {
    byte[] data = receivePack.getData();
    String s = new String(data, "UTF-8");
    InetAddress add = table.get(name);
    DatagramPacket dp = new DatagramPacket(s.getBytes("UTF-8"), s.getBytes("UTF-8").length, add, Port);
    sock.send(dp);
}

public static void main(String[] args) throws SocketException, IOException {
    try {
        String str[]={"java", "Server ","NetworkingAssignmentServer"};
        new Server(str);
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}