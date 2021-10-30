package Xuly;

import java.util.Scanner;

public class XuLyTachChuoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sms = scanner.nextLine();
        if(sms.equals("")){
            sms = "6 7 8 9 10 11 12 3";
        }
        System.out.println("Sum = " + TongChuoi(sms));
    }

    public static int TongChuoi(String sms){
        String[] str = sms.split(" ");
        int tong = 0;
        for (String s : str) {
            tong += Integer.parseInt(s);
        }
        return tong;
    }
}
