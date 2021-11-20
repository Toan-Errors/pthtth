package AppChatUDP;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.processing.Messager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ScrollBarUI;

public class ClientGUIChat extends JFrame {
    private static int port;
    private static InetAddress host;
    private static String name;
    public static Map<String, String> map;

    //Tạo các thành phần của giao diện
    public ClientGUIChat(int port, InetAddress host, String name) {
        this.port = port;
        this.host = host;
        this.name = name;
        map = new java.util.HashMap<String, String>();
    }

    //Hàm main của clientGUI
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        GUIInputName(s);
    }

    //Giao diện nhập tên
    public static void GUIInputName(DatagramSocket s) throws IOException {  
        //Tạo các thành phần của giao diện nhập tên
        JFrame frame = new JFrame("Who are you?");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Enter your name");
        label.setBounds(100, 100, 200, 30);
        frame.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(100, 150, 200, 30);
        frame.add(textField);
        JButton button = new JButton("Submit");
        button.setBounds(100, 200, 200, 30);
        frame.add(button);
        frame.setVisible(true);
        //Tạo các sự kiện cho các thành phần
        button.addActionListener(e -> {
            name = textField.getText();
            //Kiểm tra tên có rỗng hay không
            if (name.equals("")) {
                label.setText("Enter your name");
                label.setForeground(Color.red);
            } else {
                try {
                    //Khởi tạo ClientGUIChat với các tham số là tên, port, host
                    new ClientGUIChat(7777, InetAddress.getByName("localhost"), name);
                    String str = name + ": da ket noi !!!!!";
                    DatagramPacket p = new DatagramPacket(str.getBytes(), str.length(), host, port);
                    //Gửi tên đến server
                    s.send(p);
                    //Đóng giao diện nhập tên
                    frame.dispose();
                    try {
                        //Mở giao diện chat
                        GUIChat(name, s);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } catch (UnknownHostException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
        });

    }

    public static void GUIChat(String name, DatagramSocket s) throws IOException {
        //Tạo các thành phần của giao diện chat
        JFrame frame = new JFrame("Chat: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        JTextField txtmsg = new JTextField();
        txtmsg.setBounds(5, 600, 300, 30);
        txtmsg.setBorder(null);
        frame.add(txtmsg);
        JButton btnSend = new JButton("Gửi");
        btnSend.setBounds(305, 600, 75, 30);
        frame.add(btnSend);
        JTextArea lblChat = new JTextArea();
        lblChat.setBounds(5, 5, 370, 580);
        lblChat.setForeground(Color.BLUE);
        lblChat.setBackground(Color.white);
        lblChat.setOpaque(true);
        lblChat.setBorder(null);
        lblChat.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblChat.setLineWrap(true);
        lblChat.setWrapStyleWord(true);
        lblChat.setEditable(false);
        frame.add(lblChat);
        JScrollPane scroll = new JScrollPane(lblChat);
        scroll.setBounds(5, 5, 370, 580);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        frame.add(scroll);
        frame.setVisible(true);
        //Tạo các sự kiện cho các thành phần
        //Luồng nhận tin nhắn
        new Thread(() -> {
            try {
                //Khởi tạo mảng byte để nhận tin nhắn
                byte[] buf = new byte[1024];
                //Khởi tạo DatagramPacket để nhận tin nhắn
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                //Nhận tin nhắn
                while (true) {
                    s.receive(p);
                    //Chuyển mảng byte thành chuỗi
                    String str = new String(p.getData(), 0, p.getLength());
                    //Thêm tin nhắn vào giao diện chat
                    lblChat.append(str + "\n");
                }
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();

        //Luồng gửi tin nhắn
        btnSend.addActionListener(e -> {
            new Thread(() -> {
                try {
                    //Nhan tin nhan từ textfield
                    String msg = txtmsg.getText();
                    //Kiểm tra tin nhắn có rỗng hay không
                    if(msg.length() > 0) {
                        //Thêm tin nhắn vào giao diện chat
                        lblChat.append(msg + "\n");
                        txtmsg.setText("");
                        //Dùng Map để lưu dữ liệu thành JSON
                        map.put("name", name);
                        map.put("sms", msg);
                        //Chuyển Map thành String
                        String str = map.toString();
                        //Gửi Map gồm tên và tin nhắn qua server
                        DatagramPacket p = new DatagramPacket(str.getBytes(), str.length(), host, port);
                        s.send(p);
                        //Kiểm tra tin nhắn có từ exit hay không
                        if(msg.equals("exit")){
                            //Đóng giao diện chat
                            System.exit(0);
                        }
                    }    
                } catch (Exception ex) {
                    //TODO: handle exception
                }
            }).start();
        });
    }
}
