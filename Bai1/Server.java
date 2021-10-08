package Bai1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int serverPort = 7; 

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(serverPort);
            System.out.println("Server da duoc tao");
            while(true){
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                int ch = 0;
                while(true){
                    ch = is.read();
                    if(ch == -1) break;
                    switch (ch) {
                        case 48:
                            System.out.println("Khong");
                            break;
                        case 49:
                            System.out.println("Mot");
                            break;
                        case 50:
                            System.out.println("Hai");
                            break;
                        case 51:
                            System.out.println("Ba");
                            break;
                        case 52:
                            System.out.println("Bon");
                            break;
                        case 53:
                            System.out.println("Nam");
                            break;
                        case 54:
                            System.out.println("Sau");
                            break;
                        case 55:
                            System.out.println("Bay");
                            break;
                        case 56:
                            System.out.println("Tam");
                            break;
                        case 57:
                            System.out.println("Chin");
                            break;
                        default:
                            System.out.println("Không phải là số");
                            break;
                    }
                    os.write(ch);
                }
                s.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
