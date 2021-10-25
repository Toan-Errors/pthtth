package bai7TCP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final int SERVER_PORT = 7777;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("Server da duoc khoi tao");

            Socket s = ss.accept();
            System.out.println("Client da ket noi den server voi ip " + s.getLocalAddress());

            //gui ket noi
            PrintStream welcome = new PrintStream(s.getOutputStream());
            welcome.println("Hi! Chao ban. Hay gui yeu cau cho toi");

            //Nhan file
            Scanner sc_filepath = new Scanner(s.getInputStream());
            String filepath = sc_filepath.nextLine();
            File file = new File(filepath);

            System.out.println("Nhan thanh cong file" + file.getName());
            
            //Nhan duong dan

            Scanner sc_path = new Scanner(s.getInputStream());
            String path = sc_path.nextLine() + "\\" + file.getName();

            //Xu ly luu file
            if(file.exists()){
                String data= "";
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }
                myReader.close();
                System.out.println("Noi dung file >> \n" + data);

                File ghifile = new File(path);
                if(!ghifile.exists()){
                    ghifile.createNewFile();
                }
                FileWriter fw = new FileWriter(ghifile);
                fw.write(data);
                fw.close();

                PrintStream ps_tc = new PrintStream(s.getOutputStream());
                ps_tc.println("luu file thanh cong");
            } else {
                PrintStream ps_tb = new PrintStream(s.getOutputStream());
                ps_tb.println("luu file that bai");
            }

            ss.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
