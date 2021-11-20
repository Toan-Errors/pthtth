package Xuly;

import java.util.Scanner;

public class ptso1so2 {
    

    public static void main(String[] args) {
        String sms = "+ 6 9\n- 8 9\n* 100 200\n";
        Handle(sms);
    }

    public static void Handle(String sms) {
        String[] str = sms.split("\n");
        int count = 0;
        for (String s : str) {
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
            System.out.println("{Server} " + Operant1 + " " + op + " " + Operant2 + " = " + kq);    
        }        
    }

    public static String Tinh(String op, int Operant1, int Operant2){
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
        return kq;
    }

    
}
