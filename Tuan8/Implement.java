package Tuan8;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Implement extends UnicastRemoteObject implements Interface {
    public Implement() throws Exception {
        super();
    }

    @Override
    public String UpperCase(String str) throws RemoteException {
        char[] ch = str.toCharArray();
        boolean isUpper = true;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isLetter(ch[i])) {
                if(isUpper) {
                    ch[i] = Character.toUpperCase(ch[i]);
                    isUpper = false;
                }
            } else {
                isUpper = true;
            }
        }
        return String.valueOf(ch);
    }

    @Override
    public String Reverse(String str) throws RemoteException {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    @Override
    public String Menu() throws RemoteException {
        return "1. UpperCase\n" +
                "2. Reverse\n" +
                "0. Exit\n" +
                "Nhap lua chon: ";
    }

    @Override
    public String Select(int n, String s) throws RemoteException {
        String KQ = "";
        if(s.equals("exit") || n == 0){
            KQ = "Bye";
            System.exit(0);
        }
        switch(n){
            case 1:
                KQ = "KQ: "+ UpperCase(s);
                break;
            case 2:
                KQ = "KQ: "+ Reverse(s);
                break;
            case 0:
                KQ = "Bye";
                System.exit(0);
                break;
            default:
                KQ = "Nhap sai";
                break;
        }
        return KQ;
    }
    

}
