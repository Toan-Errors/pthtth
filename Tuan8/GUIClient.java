package Tuan8;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIClient extends JFrame{
    public static void main(String[] args) throws RemoteException, NotBoundException {
        GUIClient client = new GUIClient();
    }

    public GUIClient() throws RemoteException, NotBoundException {
        Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 4444);
        Interface obj = (Interface) registry.lookup("Server");
        JFrame frame = new JFrame("Client");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setLayout(null);
        JLabel lpltitle = new JLabel("Nhap chuoi");
        lpltitle.setBounds(100, 10, 150, 30);
        frame.add(lpltitle);
        JTextField txtStr = new JTextField();
        txtStr.setBounds(25, 50, 300, 30);
        frame.add(txtStr);
        JLabel lplresult = new JLabel("Ket qua");
        lplresult.setBounds(100, 100, 150, 30);
        lplresult.setFocusable(false);
        frame.add(lplresult);
        JTextField txtResult = new JTextField();
        txtResult.setBounds(25, 140, 300, 30);
        txtResult.setEditable(false);
        frame.add(txtResult);
        JCheckBox chkUpperCase = new JCheckBox("In hoa chu dau");
        chkUpperCase.setBounds(100, 180, 150, 30);
        frame.add(chkUpperCase);
        JCheckBox chkReverse = new JCheckBox("Dao nguoc chuoi");
        chkReverse.setBounds(100, 220, 150, 30);
        frame.add(chkReverse);
        JButton btnProcess = new JButton("Xu ly");
        btnProcess.setBounds(100, 260, 150, 30);
        frame.add(btnProcess);
        btnProcess.addActionListener(e -> {
            try {

                String str = txtStr.getText();
                if(str.equals("exit")){
                    System.exit(0);
                }
                String result = "";
                if(chkReverse.isSelected() && chkUpperCase.isSelected()){
                    result = obj.UpperCase(obj.Reverse(str));
                } else if(chkUpperCase.isSelected()){
                    result = obj.UpperCase(str);
                } else if(chkReverse.isSelected()){
                    result = obj.Reverse(str);
                } else {
                    result = "vui long chon chuc nang";
                }
                
                txtResult.setText(result);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });

        frame.setVisible(true);
        
    }
}
