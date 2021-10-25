package Bai1;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.io.*;

public class Client{
    public final static String serverIP = "127.0.0.1";
    public final static int serverPort = 7;
    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            Socket s = new Socket(serverIP, serverPort);
            System.out.println("Client tao thanh cong");
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            for(int i = '0'; i < '9'; i++){
				os.write(i);
				byte b[] =new byte[50];
				int n=is.read(b);
				String kq=new String(b,0,n);
				System.out.println("Result:"+kq);
                Thread.sleep(2000);
            }

            while(true) {
				System.out.print("Nhap mot so: ");
				int ch=System.in.read();
				os.write(ch);
				if(ch=='@')
					break;
				byte b[] =new byte[50];
				int n=is.read(b);
				String kq=new String(b,0,n);
				System.out.println("Result:"+kq);
			}
            s.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}