package bai6TCP;

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
            System.out.println("Client da duoc tao");
            
            Scanner welcome = new Scanner(s.getInputStream());
            System.out.println("Server >> " + welcome.nextLine());
            int n;
            do{
                //Send
                System.out.println("Nhap n = 0 de thoat");
                PrintStream outPrintStream= new PrintStream(s.getOutputStream());
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhap n: ");
                n = sc.nextInt();
                outPrintStream.println(n);

                //Nhan
                Scanner inFromServer = new Scanner(s.getInputStream());
                String nhan = inFromServer.nextLine();
                String[] str = nhan.split(",");
                System.out.println("Server >> " + str[0]);
                System.out.println("Server >> " + str[1]);
                System.out.println("Server >> " + str[2]);

            }while(n != 0);

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
