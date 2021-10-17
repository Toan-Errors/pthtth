package bai8;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(7777);
            System.out.println("Server UDP da duoc tao");

            //Nhan
            byte n[] = new byte[6000];
            DatagramPacket dp_receive = new DatagramPacket(n, n.length);
            s.receive(dp_receive);
            String strnhan = new String(dp_receive.getData(), 0, dp_receive.getLength());
            String str = strnhan.substring(0,strnhan.indexOf("\\n"));
            System.out.println(str);
            int kq = 0;
            String op = str.split(" ")[0];
            int Operant1 = Integer.parseInt(str.split(" ")[1]);
            int Operant2 = Integer.parseInt(str.split(" ")[2]);
            
            switch (op) {
                case "+":
                    kq = Operant1 + Operant2;
                    break;
                case "-":
                    kq = Operant1 - Operant2;
                    break;
                case "*":
                    kq = Operant1 * Operant2;
                    break;
                case "/":
                    kq = Operant1 / Operant2;
                    break;
                default:
                    kq = 0;
                    break;
            }


        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
