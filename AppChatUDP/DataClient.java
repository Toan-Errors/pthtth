package AppChatUDP;

import java.net.InetAddress;
import java.util.Map;

public class DataClient {
    private InetAddress IP;
    private int port;
    private String name;

    //Khởi tạo constructor cho DataClient
    //Với đối số là client, host, port, name
    //Với các getter và setter cho các thuộc tính
    public DataClient(InetAddress iP, int port, String name) {
        this.IP = iP;
        this.port = port;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress iP) {
        this.IP = iP;
    }
}
