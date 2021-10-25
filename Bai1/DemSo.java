package Bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class DemSo extends Thread{
    Socket ss;
    public DemSo(Socket ss){
        this.ss = ss;
    }
    
    @Override
    public void run() {
            try {
                InputStream is = ss.getInputStream();
                OutputStream os = ss.getOutputStream();
                while(true){
                    int ch = is.read();
                    byte[] b = ham(ch).getBytes();
                    os.write(b);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
    }

    public String ham(int ch){
        String kq = "";
        switch (ch) {
            case 48:
                kq = "Khong";
                break;
            case 49:
                kq = "Mot";
                break;
            case 50:
                kq = "Hai";
                break;
            case 51:
                kq = "Ba";
                break;
            case 52:
                kq = "Bon";
                break;
            case 53:
                kq = "Nam";
                break;
            case 54:
                kq = "Sau";
                break;
            case 55:
                kq = "Bay";
                break;
            case 56:
                kq = "Tam";
                break;
            case 57:
                kq = "Chin";
                break;
            default:
                kq = "Khong phai so hoac so lon hon 9";
                break;
        }
        return kq;
    }
    
}
