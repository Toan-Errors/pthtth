package bai4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import javax.print.attribute.standard.Media;

public class Server {
    public final static int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server da duoc tao! Dnag cho client ket noi den port " + ss.getLocalPort());

            Socket s = ss.accept();
            System.out.println("Client da ket noi den "+ s.getInetAddress().getHostAddress() +":"+s.getLocalPort());
            PrintStream welcome = new PrintStream(s.getOutputStream());
            welcome.println("Hi, say something!!!");
            int msg_in;
            do{
                Scanner inFormClient = new Scanner(s.getInputStream());
                msg_in = inFormClient.nextInt();
                System.out.println("Client: " + msg_in);
                PrintStream outToClient = new PrintStream(s.getOutputStream());
                outToClient.println(Xuly(msg_in));
            } while(msg_in != 0);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static String Xuly(int s){
        switch(s){
            case 1:
                return LocalTime.now().toString();
            case 2:
                return LocalDate.now().toString();
            case 3:
                return LocalDateTime.now().toString();
            default :
                return "Chon sai";
        }
    }
}
