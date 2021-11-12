package Tuan8;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ConsoleClient {
    public static void main(String[] args) {
        try {
            Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 4444);
            Interface obj = (Interface) registry.lookup("Server");
            int n;
            String s;
            do{
                Scanner sc = new Scanner(System.in);
                System.out.print(obj.Menu());
                n = sc.nextInt();
                Scanner scx = new Scanner(System.in);
                System.out.print("Nhap chuoi: ");
                s = scx.nextLine();
                System.out.println(obj.Select(n, s));
                if(s.equals("exit")){
                    System.exit(0);
                }
            } while(true);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}