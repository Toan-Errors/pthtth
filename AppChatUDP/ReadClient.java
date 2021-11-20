package AppChatUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//Tạo lớp ReadClient để đọc dữ liệu từ server
class ReadClient extends Thread {
    private DatagramSocket client;

    public ReadClient(DatagramSocket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            //Tạo một luồng while để chạy vô tận
            while (true) {
                //Gọi hàm receive() để nhận dữ liệu từ server
                String sms = ReceiveData();
                //In ra dữ liệu nhận được
                System.out.println(sms);
                //Kiểm tra tên người dùng
                if(sms.contains("ten da ton tai")){
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    //Hàm nhận dữ liệu từ server
    private String ReceiveData() throws IOException{
        //Tạo một mảng byte để nhận dữ liệu
        byte data[] = new byte[6000];
        //Tạo một DatagramPacket để nhận dữ liệu
        DatagramPacket packet = new DatagramPacket(data, data.length);
        client.receive(packet);
        //Chuyển dữ liệu từ mảng byte thành chuỗi String
        return new String(data, 0, data.length);
    }
}
