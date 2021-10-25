package bai2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public final static int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server da duoc tao! Dang cho client tai: "+ ss.getInetAddress().getHostAddress() + "/" + ss.getLocalPort() + "...");

            Socket s = ss.accept();
            System.out.println("Client da ket noi den server " + s.getRemoteSocketAddress());

            String remoteIp = s.getInetAddress().getHostAddress();
            String remotePort = ":" + s.getLocalPort();
            System.out.println("RemoteIP: "+ remoteIp + "/ remotePort " + remotePort);
            PrintStream welcome = new PrintStream(s.getOutputStream());
            welcome.println("Hi, say something!!!");
            String msg_in = "", msg_out = "";
            while(!msg_in.equals("bye") && !msg_out.equals("bye")){
                Scanner inFormClient = new Scanner(s.getInputStream());
                msg_in = inFormClient.nextLine();
                System.out.println("Client: "+ msg_in);

                PrintStream outToClient = new PrintStream(s.getOutputStream());
                System.out.println("Server: ");
                Scanner sc = new Scanner(System.in);
                msg_out = sc.nextLine();
                outToClient.println(msg_out);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
