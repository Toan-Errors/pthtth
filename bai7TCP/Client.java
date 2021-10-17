package bai7TCP;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public final static int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap IP: ");
            String address = sc.nextLine();

            Socket s = new Socket(address, SERVER_PORT);
            System.out.println("Client da duoc tao");

            //Nhan ket noi
            Scanner welcome = new Scanner(s.getInputStream());
            System.out.println("Server >> " + welcome.nextLine());

            //Gui file
            System.out.print("Nhap duong dan file: ");
            String filepath = sc.nextLine();
            File file = new File(filepath);
            PrintStream ps_filepath = new PrintStream(s.getOutputStream());
            ps_filepath.println(filepath);
            System.out.println("Gui thanh cong file: " + file.getName());

            //Duong dan luu file
            System.out.print("Nhap duong dan luu file: ");
            String path = sc.nextLine();
            PrintStream ps_path = new PrintStream(s.getOutputStream());
            ps_path.println(path);

            //Nhan ket qua
            Scanner sc_kq = new Scanner(s.getInputStream());
            String kq = sc_kq.nextLine();
            System.out.println("Server >> " + kq);
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
