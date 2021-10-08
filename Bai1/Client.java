package Bai1;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import java.io.*;

public class Client{
    public final static String serverIP = "127.0.0.1";
    public final static int serverPort = 7;
    public static void main(String[] args) throws InterruptedException, IOException {
        Socket s = null;
        Scanner sc = new Scanner(System.in);
        try {
            s = new Socket(serverIP, serverPort);
            System.out.println("Client tao thanh cong");
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            for(int i = '0'; i <= 'B'; i++){
                os.write(i);
                int ch = is.read();
                System.out.println((char)ch);
                Thread.sleep(2000);
            }
            
            try {
                do{
                    System.out.print("Nhap 1 ki tu bat ki: ");
                    int x = System.in.read();
                    os.write(x);
                    int ch = is.read();
                    Thread.sleep(2000);
                } while (true);
            } catch (Exception e) {
                //TODO: handle exception
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(s != null){
                s.close();
            }
        }
    }
}