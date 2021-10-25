package bai2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            Socket s = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Server da duoc tao");

            Scanner welcome = new Scanner(s.getInputStream());
            System.out.println("Server: " + welcome.nextLine());
            String msg_out = "", msg_in = "";
            while(!msg_out.equals("bye") && !msg_in.equals("bye")){
                PrintStream outStream = new PrintStream(s.getOutputStream());
                System.out.println("Client: ");
                Scanner sc = new Scanner(System.in);
                msg_out = sc.nextLine();
                outStream.println(msg_out);

                Scanner inFormServer = new Scanner(s.getInputStream());
                msg_in = inFormServer.nextLine();
                System.out.println("Server: " + msg_in);
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
