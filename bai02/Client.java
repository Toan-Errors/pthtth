package bai02;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Client extends JFrame{
    private JButton btnExit;
    private JButton btnLogin;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblTitle;
    private JPasswordField txtPassword;
    private JTextField txtUsername;
    CheckInterface stub;

    public Client() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        lblTitle = new JLabel("Login");
        lblTitle.setBounds(100, 10, 100, 30);
        add(lblTitle);
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(10, 50, 100, 30);
        add(lblUsername);
        txtUsername = new JTextField();
        txtUsername.setBounds(100, 50, 200, 30);
        add(txtUsername);
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 90, 100, 30);
        add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 90, 200, 30);
        add(txtPassword);
        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 130, 100, 30);
        add(btnLogin);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnExit = new JButton("Exit");
        btnExit.setBounds(200, 130, 100, 30);
        add(btnExit);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        setVisible(true);
        try {         
            stub = (CheckInterface) java.rmi.Naming.lookup("rmi://localhost:4444/Server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void Calc(){
        JFrame frame = new JFrame("Calc");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);
        JLabel lblTitle = new JLabel("Calculator");
        lblTitle.setBounds(100, 10, 200, 30);
        frame.add(lblTitle);
        JLabel lblNum1 = new JLabel("Number 1");
        lblNum1.setBounds(10, 50, 100, 30);
        frame.add(lblNum1);
        JTextField txtNum1 = new JTextField();
        txtNum1.setBounds(100, 50, 100, 30);
        frame.add(txtNum1);
        JLabel lblNum2 = new JLabel("Number 2");
        lblNum2.setBounds(10, 90, 100, 30);
        frame.add(lblNum2);
        JTextField txtNum2 = new JTextField();
        txtNum2.setBounds(100, 90, 100, 30);
        frame.add(txtNum2);
        JLabel lblResult = new JLabel("Result");
        lblResult.setBounds(10, 130, 100, 30);
        frame.add(lblResult);
        JTextField txtResult = new JTextField();
        txtResult.setBounds(100, 130, 100, 30);
        txtResult.setEditable(false);
        frame.add(txtResult);
        JButton btnAdd = new JButton("+");
        btnAdd.setBounds(10, 170, 100, 30);
        frame.add(btnAdd);
        JButton btnSub = new JButton("-");
        btnSub.setBounds(120, 170, 100, 30);
        frame.add(btnSub);
        JButton btnMul = new JButton("*");
        btnMul.setBounds(10, 210, 100, 30);
        frame.add(btnMul);
        JButton btnDiv = new JButton("/");
        btnDiv.setBounds(120, 210, 100, 30);
        frame.add(btnDiv);
        btnAdd.addActionListener(e -> {
            try {
                int num1 = Integer.parseInt(txtNum1.getText());
                int num2 = Integer.parseInt(txtNum2.getText());
                int result = stub.add(num1, num2);
                txtResult.setText(String.valueOf(result));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
            }
        });
        btnSub.addActionListener(e -> {
            try {
                int num1 = Integer.parseInt(txtNum1.getText());
                int num2 = Integer.parseInt(txtNum2.getText());
                int result = stub.sub(num1, num2);
                txtResult.setText(String.valueOf(result));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
            }
        });
        btnMul.addActionListener(e -> {
            try {
                int num1 = Integer.parseInt(txtNum1.getText());
                int num2 = Integer.parseInt(txtNum2.getText());
                int result = stub.mul(num1, num2);
                txtResult.setText(String.valueOf(result));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
            }
        });
        btnDiv.addActionListener(e -> {
            try {
                int num1 = Integer.parseInt(txtNum1.getText());
                int num2 = Integer.parseInt(txtNum2.getText());
                int result = stub.div(num1, num2);
                txtResult.setText(String.valueOf(result));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
            }
        });

    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String pass = "";
        String username = txtUsername.getText();
        char[] password = txtPassword.getPassword();
        Character[] passwordChar = new Character[password.length];
        for (int i = 0; i < password.length; i++) {
            passwordChar[i] = password[i];
            pass += passwordChar[i].toString();
        }
        try {
            if (stub.check(username, pass)) {
                JOptionPane.showMessageDialog(null, "Login success", "Message", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                Calc();
            } else {
                JOptionPane.showMessageDialog(null, "Login Fail", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        
    }
}
