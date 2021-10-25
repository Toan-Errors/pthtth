package bai6TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final int SERVER_PORT = 7777;
    public static int kq1 = 0;
    public static int kq2 = 0;
    public static int kq3 = 0;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server da duoc khoi tao");

            Socket s = ss.accept();
            System.out.println("Client da ket noi den server voi" + s.getLocalAddress());
            
            PrintStream welcome = new PrintStream(s.getOutputStream());
            welcome.println("Hi! Chao ban. Hay gui yeu cau cho toi");
            int n;
            do{
                //Nhan
                Scanner inFromClient = new Scanner(s.getInputStream());
                n = inFromClient.nextInt();

                //Send
                TinhToan(n);
                
                PrintStream outPrintStream = new PrintStream(s.getOutputStream());
                outPrintStream.println("Tổng 1+3+5+7+...+(2n+1) = " + kq1 + " ,Tổng 1*2 + 2*3+...+n*(n+1) = " + kq2 + " ,Biểu thức 1-2+3-4+..+(2n+1) = "+ kq3);
                kq1 = 0; kq2 = 0; kq3 = 0;
            } while(n != 0);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static boolean TinhToan(int n){
        for(int i = 1; i <= 2*n+1; i++){
            kq2 += i*(i+1);
            if(i%2!=0){
                kq1 += i;
                kq3 += i;
            } else {
                kq3 -= i;
            }
        }
        return true;
        
    }
}
