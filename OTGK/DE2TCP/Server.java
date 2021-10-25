package OTGK.DE2TCP;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            //Khởi tạo server socket
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server da duoc tao");
            //Chấp nhận các client kết nối đến server bằng ss.accept
            Socket s = ss.accept();
            System.out.println("Client: " + s.getInetAddress() + " da ket noi");
            
            //Nhận chuỗi được gửi từ client

            //Dùng Scanner để nhận chuỗi từ client qua input
            Scanner sc_receive_str = new Scanner(s.getInputStream());
            //Dùng nextline để đọc theo dòng, nếU gửi số thì đọc nextint hoạc ép kiểu nextline
            String str = sc_receive_str.nextLine();
            System.out.println("Đã nhận '"+ str +"' từ client");

            //Nhận lựa chọn từ server
            int chon;
            
            do{
                //Tạo biến để lưu kết quả
                String kq = "";
                //Nhận dữ liệu chọn từ client
                Scanner sc_receive_chon = new Scanner(s.getInputStream());
                chon = sc_receive_chon.nextInt();
                //Kiểm tra người dùng có nhâp 0 hay không. nếu có thì kết thúc luôn
                if(chon == 0){
                    break;
                }
                //Dùng switch case và hàm để xử lý
                switch(chon){
                    case 1:
                        kq = LowerToUpper(str);
                        break;
                    case 2:
                        kq = Reverse(str);
                        break;
                    default:
                        kq = "Lựa chọn chưa có trong yêu cầu hoặc bạn đã nhập sai";
                        break;
                }

                //Gửi kết quả về Client
                PrintStream ps_send_kq = new PrintStream(s.getOutputStream());
                ps_send_kq.println(kq);
                System.out.println("Xử lý yêu cầu "+ chon +" thành công");
            }while(chon != 0);
            s.close();
            System.out.println("Client đã đóng kết nối");
            ss.close();
            System.out.println("Server đã đóng kết nối");
        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    //Hàm xử lý chuỗi
    //Hàm chuyển chuỗi thành in hoa
    public static String LowerToUpper(String str){
        return str.toUpperCase();
    }
    //Hàm đảo ngược chuỗi
    public static String Reverse(String str){
        StringBuilder s = new StringBuilder(str);
        return s.reverse().toString();
    }

}
