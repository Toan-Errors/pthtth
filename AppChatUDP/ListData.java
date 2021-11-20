package AppChatUDP;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ListData{
    public ArrayList<DataClient> list;
    DatagramSocket s;
    private String check = "";

    //Khởi tạo constructor cho ListData với tham số là một DatagramSocket
    public ListData(DatagramSocket s) {
        this.s = s;
        //Khởi tạo mảng list để lưu các DataClient
        list = new ArrayList<DataClient>();
    }
    
    //Hàm thêm một DataClient vào mảng list
    public void addData(DataClient data) throws IOException, InterruptedException{
        //Kiểm tra xem DataClient có null hay không
        if(data != null){
            //Kiểm tra xem DataClient đã tồn tại trong mảng list chưa
            for(DataClient d : list){
                if(d.getName().equals(data.getName())){
                    //Nếu đã tồn tại thì thông báo lỗi và thoát
                    String str = "ten da ton tai";
                    DatagramPacket p = new DatagramPacket(str.getBytes(), str.length(), data.getIP(), data.getPort());
                    s.send(p);
                    return;
                }
            }
            //Nếu chưa thì thêm vào mảng list
            list.add(data);
        }
    }

    //Hàm xóa một DataClient trong mảng list
    public void RemoveData(DataClient data){
        list.remove(data);
    }

    //Hàm SendData là hàm gửi dữ liệu từ một DataClient đến một DataClient khác
    public void SendMsg(String sms ,InetAddress host, int port) throws IOException, InterruptedException{
        //Tạo một String msg để lấy dữ liệu gửi đi gồm name và sms
        String msg = getFullContent(sms);
        //String check là 1 thuộc tính để kiểm tra xem có lệnh nào được gửi tới server hay không. các lệnh được bắt đầu bằng @
        if(!check.equals("")){
            //Nếu có lệnh thì thực hiện xử lý lệnh và gửi dữ liệu đi
            DatagramPacket p = new DatagramPacket(check.getBytes(), check.getBytes().length, host, port);
            s.send(p);
            //Đưa check thành rỗng để tránh trùng lặp
            check = "";
        } else {
            //Nếu không có lệnh thì gửi tin nhắn như bình thường
            //Lặp qua mảng list để tìm DataClient có IP và Port tương ứng với host và port
            for(DataClient d : list){
                //Kiểm tra xem DataClient có IP và Port tương ứng với host và port không
                //Và nếu có thì loại trừ client đó ra thì sẽ gửi cho toàn bộ client khác
                if(!(d.getPort() == port && d.getIP().equals(host))){
                    DatagramPacket p = new DatagramPacket(msg.getBytes(), msg.length(), d.getIP(), d.getPort());
                    s.send(p);
                }
            }
        }
        System.out.println(msg);
    }

    //Hàm nhận content và trả về full content(name + sms)
    private String getFullContent(String sms) throws IOException, InterruptedException {
        //Khởi tạo 1 map để lưu tên và sms
        Map<String, String> map = new HashMap<>();
        //Do không biết hàm get JSON của java nên dùng cắt chuỗi
        //Loại bỏ "{" và "}" của dữ liệu json
        String str = sms.substring(1) + sms.substring(2, sms.length() - 1);

        //Lấy tên và sms và lưu vào map
        for(String s1 : str.split(", ")){
            String[] s2 = s1.split("=");
            map.put(s2[0], s2[1]);
        }
        //Lấy tên và sms từ map
        String name = map.get("name");
        String content = map.get("sms");
        //Hàm kiểm tra có lệnh hay không sẽ được chạy trước khi gửi tin nhắn
        CheckStr(content);
        //Tạo ra 1 luồng riêng để chạy các hàm luôn được chạy nhưng không bị chặn
        new Thread(() -> {
            try {
                //Hàm kiểm tra thoát của client
                CheckExit(name, content);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //Hàm Ghi dữ liệu vào file
            GhiFile(content, name);
        }).start();
        //Trả về full content
        return "{" + name + "} : " + content;
    }

    //Hàm kiểm tra thoát của client
    public void CheckExit(String name, String content) throws IOException{
        //Nếu có lệnh exit thì thoát
        if(content.equals("exit")){
            for(DataClient d : list){
                if(d.getName().equals(name)){
                    //xoá DataClient ra khỏi mảng list
                    list.remove(d);
                    //Gửi tin nhắn đã thoát của client đó đến toàn bộ client khác
                    SendMsgByServer(name + " da thoat");
                    System.out.println(name + " da thoat");
                    break;
                }
            }
        }
    }

    //Hàm gửi tin nhắn của Server đến toàn bộ client đang kết nối
    public void SendMsgByServer(String msg) throws IOException{
        String sms = "{Server} : " + msg;
        for(DataClient d : list){
            DatagramPacket p = new DatagramPacket(sms.getBytes(), sms.length(), d.getIP(), d.getPort());
            s.send(p);
        }
    }
    
    //Hàm kiểm tra có lệnh hay không
    public void CheckStr(String content) throws IOException, InterruptedException {
        //nếu có lệnh thì thực hiện xử lý lệnh
        if(content.contains("@")){
            //Lệnh @chucnang : gửi các chức năng đến client
            if(content.contains("@chucnang")){
                String[] chucnang = {
                    "1. @tong a b c d",
                    "2. @hieu a b c d",
                    "3. @tich a b c d",
                    "4. @thuong a b c d",
                    "5. @read filepath",
                    "6. @readupper filepath",
                    "7. @readlower filepath",   
                    "8. @readnumber filepath",
                    "9. @readrecevie filepath",
                    "10. @readsolan filepath str",
                    "11. @tinh pt so1 so2;pt so3 so4;....",
                    "12. @readupperstr filepath str",
                    "13. @random start end",
                };
                for(String s : chucnang){
                    check += s + "\n";
                }
            }
            
            //Lệnh @tinh pt so1 so2;pt so3 so4;... : tính toán phương trình
            if(content.contains("@tinh")){
                String chuoi = content.split("@tinh ")[1];
                check = Tinh(chuoi);
            }

            //Lệnh @random start end : tạo ra 1 số ngẫu nhiên từ start đến end
            if(content.contains("@random")){
                String str = content.split("@random ")[1];
                String[] s = str.split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                Random random = new Random();
                int number = random.nextInt(end - start + 1) + start;
                check = "So ngau nhien: "+ number;
            }

            //Lệnh @tong a b c d : tính tổng các số a, b, c, d
            //Lệnh @hieu a b c d : tính hiệu các số a, b, c, d
            //Lệnh @tich a b c d : tính tích các số a, b, c, d
            //Lệnh @thuong a b c d : tính thương các số a, b, c, d
            String[] calc = {"@tong", "@hieu", "@tich", "@thuong"};
            for(String s : calc){
                if(content.contains(s)){
                    String[] arr3 = content.split(" ");
                    String pt = arr3[0];
                    String[] arr2 = content.split(pt + " ");
                    String number = arr2[1];
                    check = Calc(pt, number);
                    break;
                }
            }
            
            //Gói lệnh đọc file
            //Lệnh @read filepath : đọc file từ filepath
            //Lệnh @readupper filepath : đọc file từ filepath và chuyển tất cả các ký tự thành chữ hoa
            //Lệnh @readlower filepath : đọc file từ filepath và chuyển tất cả các ký tự thành chữ thường
            //Lệnh @readnumber filepath : đọc file từ filepath và gửi về toàn bộ kí tự trong file
            //Lệnh @readrecevie filepath str : đọc file từ filepath và đảo ngược các ký tự trong file
            //Lệnh @readsolan filepath str : đọc file từ filepath và trả về số lần xuất hiện của str trong file
            //Lệnh @readupperstr filepath str : đọc file từ filepath và chuyển tất cả các ký tự str thành ký tự thành chữ hoa
            String[] file = {"@read", "@readupper", "@readlower", "@readnumber", "@readsolan", "@readrecevie", "@readupperstr"};
            for(String s : file){
                if(content.contains(s)){
                    String[] arr = content.split(" ");
                    String pt = arr[0];
                    String[] arr2 = content.split(pt + " ");
                    String fileName = arr2[1];
                    check = FileReader(pt, fileName);
                    break;
                }
            }
        }
    }

    //Hàm tính toán sử lý lênhj @tinh pt so1 so2;pt so3 so4;...
    public String Tinh(String sms){
        String[] str = sms.split(";");
        System.out.println(str.length);
        System.out.println(str[0]);
        String tinh = "";
        int count = 0;
        for (String s : str) {
            count++;
            String[] v = s.split(" ");
            String op = v[0];
            int Operant1 = Integer.parseInt(v[1]);
            int Operant2 = Integer.parseInt(v[2]);
            String kq = "";
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
                tinh += "{Server} : Phep tinh " + count + ": " + kq + "\n";    
        }
        return tinh;        
    }

    //Hàm đọc file và xử lý các lệnh của @read
    public String FileReader(String pt, String path) throws IOException{
        String data= "";
        try {
            File file;
            if(path.split(" ").length == 2){
                file = new File(path.split(" ")[0]);
            } else {
                file = new File(path);
            }
            Scanner myReader;
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();

            switch (pt) {
                case "@read":
                    return "Noi dung\n{ " + data + " }";
                case "@readupper":
                    return "Noi dung\n{ " + data.toUpperCase() + " }";
                case "@readlower":
                    return "Noi dung\n{ " + data.toLowerCase() + " }";
                case "@readnumber":
                    int sum = 0;
                    for(int i = 0; i < data.length(); i++){
                        sum += data.charAt(i);
                    }
                    return "Tong so ki tu\n{ " + sum + " }";
                case "@readsolan":
                    String[] arr = path.split(" ");
                    String temp = arr[1];
                    int count = 0;
                    String[] x = data.toLowerCase().split(" ");
                    for (String string : x) {
                        String[] c = string.split(" ");
                        for (String string2 : c) {
                            if(string2.toLowerCase().equals(temp.toLowerCase())){
                                count++;
                            } 
                        }
                    }
                    return "So lan xuat hien\n{ " + count + " }";
                case "@readupperstr":
                    arr = path.split(" ");
                    temp = arr[1];
                    x = data.split("\n");
                    String str = "";
                    for (String string : x) {
                        String[] c = string.split(" ");
                        for (String string2 : c) {
                            if(string2.toLowerCase().equals(temp.toLowerCase())){
                                str += string2.toUpperCase() + " ";
                            } else {
                                str += string2 + " ";
                            }
                        }
                        str += "\n";
                    }
                    return "Noi dung\n{ " + str + " }";
                case "@readrecevie":
                    StringBuilder sb = new StringBuilder(data);
                    sb.reverse();
                    return "Noi dung dao nguoc\n{ " + sb.toString() + " }";
                default:
                    return "khong co chuc nang nay";
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    //Hàm máy tính xử lý lệnh @tong, @hieu, @tich, @thuong
    public String Calc(String pt, String number){
        switch(pt){
            case "@tong":
                int sum = 0;
                String[] temp = number.split(" ");
                for(String item : temp){
                    sum += Integer.parseInt(item);
                }
                return "tong cua " + number + " la: " + sum;
            case "@hieu":
                String[] temp2 = number.split(" ");
                int sub = Integer.parseInt(temp2[0]);
                for(int i = 1; i < temp2.length; i++){
                    sub -= Integer.parseInt(temp2[i]);
                }
                return "hieu " + number + " la: " + sub;
            case "@tich":
                int mul = 1;
                String[] temp3 = number.split(" ");
                for(String item : temp3){
                    mul *= Integer.parseInt(item);
                }
                return "tich cua " + number + " la: " + mul;
            case "@thuong":
                String[] temp4 = number.split(" ");
                int div = Integer.parseInt(temp4[0]);
                for(int i = 1; i < temp4.length; i++){
                    div /= Integer.parseInt(temp4[i]);
                }
                return "thuong cua " + number + " la: " + div;
            default:
                return "Lỗi";
        }
    }
    
    //Hàm ghi file
    private void GhiFile(String sms, String name){
        try {
            String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\AppChatUDP\\";
            String filepath = path + name + ".txt";
            File file = new File(filepath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sms);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        } catch (Exception e) {

        }
    }
    
    //Hàm đọc file và gửi lịch sử tin nhắn cho client
    public String LoadLichSu(String name) throws FileNotFoundException{
        String path = "C:\\Users\\zlove\\Documents\\GitHub\\pthtth\\AppChatUDP\\";
        File file = new File(path + name + ".txt");
        String str = "";
        if(file.exists()){
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                str += sc.nextLine() + "\n";
            }
        }
        return str;
    }
}
