package Xuly;

import java.util.Scanner;

public class InHoaThuong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sms = scanner.nextLine();
        System.out.println(InHoa(sms));
        System.out.println(InThuong(sms));
        System.out.println(InHoaThuong(sms));
    }

    public static String InHoa(String sms){
        return sms.toUpperCase();
    }

    public static String InThuong(String sms){
        return sms.toLowerCase();
    }

    public static String InHoaThuong(String sms){
        String s = "";
        for(int i=0; i<sms.length(); i++){
            if(i%2 == 0){
                s += String.valueOf(sms.charAt(i)).toUpperCase();
            } else {
                s += sms.charAt(i);
            }
        }
        return s;
    }
}
