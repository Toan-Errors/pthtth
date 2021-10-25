package bai6UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.xml.crypto.Data;

public class Server {
    public static void main(String[] args) {
        int kq1 = 0, kq2 = 0, kq3 = 0;

        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("UDP Server da duoc tao");
            byte[] arr = new byte[6000];
            DatagramPacket nhan = new DatagramPacket(arr, arr.length);
            s.receive(nhan);
            String str = new String(nhan.getData(), 0, nhan.getLength());
            int n = Integer.parseInt(str);
            
            for(int i = 1; i <= 2*n+1; i++){
                kq2 += i*(i+1);
                if(i%2!=0){
                    kq1 += i;
                    kq3 += i;
                } else {
                    kq3 -= i;
                }
            }
            System.out.println("Tính n = " + n);
            String dang = "Server đang tính với n = " + n + " của địa chỉ " + nhan.getAddress();
            DatagramPacket pkd = new DatagramPacket(dang.getBytes(), dang.getBytes().length, nhan.getAddress(), nhan.getPort());
            s.send(pkd);

            Thread.sleep(2000);
            String kq = "Tổng 1+3+5+7+...+(2n+1) = " + kq1 + " ,Tổng 1*2 + 2*3+...+n*(n+1) = " + kq2 + " ,Biểu thức 1-2+3-4+..+(2n+1) = "+ kq3;
            byte b[] = kq.getBytes();
            DatagramPacket gui = new DatagramPacket(b, b.length, nhan.getAddress(), nhan.getPort());
            s.send(gui);

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
