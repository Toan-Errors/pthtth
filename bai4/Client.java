package bai4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            Socket s = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Client da duoc tao");
            Scanner welcome = new Scanner(s.getInputStream());
            
            System.out.println("Server: " + welcome.nextLine());
            int msg_out;
            String msg_in;
            do{
                System.out.println("-----------Menu------------");
                System.out.println("Chon chuc nang");
                System.out.println("1. Time");
                System.out.println("2. Date");
                System.out.println("3. Date time");
                System.out.println("---------------------------");
                PrintStream outPrintStream = new PrintStream(s.getOutputStream());
                System.out.println("Client: ");
                Scanner sc = new Scanner(System.in);
                msg_out = sc.nextInt();
                outPrintStream.println(msg_out);

                Scanner inFormServer = new Scanner(s.getInputStream());
                msg_in = inFormServer.nextLine();
                System.out.println("Server: " + msg_in);
            }while(msg_out != 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
