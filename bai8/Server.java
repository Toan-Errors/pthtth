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
            String kq;
            String op = str.split(" ")[0];
            double Operant1 = Integer.parseInt(str.split(" ")[1]);
            double Operant2 = Integer.parseInt(str.split(" ")[2]);
            
            switch (op) {
                case "+":
                    kq = Operant1 + " + " + Operant2 + " = " + (Operant1 + Operant2);
                    break;
                case "-":
                    kq = Operant1 + " - " + Operant2 + " = " + (Operant1 - Operant2);
                    break;
                case "*":
                    kq = Operant1 + " * " + Operant2 + " = " + (Operant1 * Operant2);
                    break;
                case "/":
                    kq = Operant1 + " / " + Operant2 + " = " + (Operant1 / Operant2);
                    break;
                default:
                    kq = "Nhập sai dữ liệu";
                    break;
            }
            
            //Gui
            DatagramPacket dp_send = new DatagramPacket(kq.getBytes(), kq.length(), dp_receive.getAddress(), dp_receive.getPort());
            s.send(dp_send);
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
