package OTGK.DE2TCP;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
                //Nhập địa chỉ IP từ bàn phím
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhap IP: ");
                //Nhập Port từ bàn phím
                String SERVER_IP = sc.nextLine();
                System.out.print("Nhap port: ");
                // Có thể dùng sc.NextInt() những t dùng nextLine để chuyển chuỗi thành số cho đỡ lỗi
                int SERVER_PORT = Integer.parseInt(sc.nextLine());

                //Kết nối đến Server bằng IP và port đã nhập
                Socket s = new Socket(SERVER_IP, SERVER_PORT);
                System.out.println("Client đã được khỏi tạo");

                //Nhập 1 chuỗi và gửi đến server
                System.out.print("Nhập mỗi chuỗi: ");
                String str = sc.nextLine();
                //Tạo một yêu cầu gửi (out)
                PrintStream ps_send_str = new PrintStream(s.getOutputStream());
                //Tiến hành gửi chuỗi str bằng hàm println
                ps_send_str.println(str);
                System.out.println("Gửi thành công '" + str + "' đến server");
                //Client gửi xong chuỗi thì bên server sẽ nhận chuỗi

                //Tiến hành tạo menu gửi yêu cầu
                int chon;
                do{
                    //Gọi hàm menu đã khởi tạo bên dưới
                    Menu();
                    //Gửi dữ liệu chọn được nhập vào đến server
                    //Nhập chon
                    chon = sc.nextInt();
                    //Nếu nhập lựa chọn = 0 thì kết thúc luôn
                    //Gửi lựa chọn
                    PrintStream ps_send_chon = new PrintStream(s.getOutputStream());
                    ps_send_chon.println(chon);
                    if(chon == 0){
                        break;
                    }
                    //Nhận kết quả từ Server
                    Scanner sc_receive_kq = new Scanner(s.getInputStream());
                    String kq = sc_receive_kq.nextLine();

                    //In kết quả ra màn hình
                    System.out.println("---------------KẾT QUẢ--------------");
                    System.out.println("Server >> " + kq);
                    System.out.println("-----------------END----------------");
                }while(chon != 0);
                
                s.close();
                System.out.println("Đóng client");
        } catch (Exception e) {
            System.out.println("Hình như sai bạn nhập sai port để kết nối hãy thử chạy lại và thử lại với port '7777' ");
        }
    }

    //Viết Hàm menu
    public static void Menu(){
        System.out.println("---------------MENU--------------");
        System.out.println("1. Chuyển chuỗi thành in hoa");
        System.out.println("2. Đảo ngược chuỗi");
        System.out.println("0. Thoát");
        System.out.println("---------------END--------------");
    }
}
