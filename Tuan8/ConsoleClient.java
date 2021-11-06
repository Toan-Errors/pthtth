package Tuan8;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.sql.rowset.spi.SyncFactoryException;

public class ConsoleClient {
    public static void main(String[] args) {
        try {
            Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 4444);
            Interface obj = (Interface) registry.lookup("Server");
            int n;
            Scanner sc = new Scanner(System.in);
            do{
                System.out.print(obj.Menu());
                n = sc.nextInt();
                Select(n, obj);
            } while(n != 0);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public static void Select(int n, Interface obj) throws RemoteException{
        Scanner sc = new Scanner(System.in);
        switch(n){
            case 1:
                System.out.print("Nhap chuoi: ");
                String str = sc.nextLine();
                System.out.println("KQ: "+ obj.UpperCase(str));
                break;
            case 2:
                System.out.print("Nhap chuoi: ");
                str = sc.nextLine();
                System.out.println("KQ: "+ obj.Reverse(str));
                break;
            case 0:
                System.out.println("Bye");
                System.exit(0);
                break;
            default:
                System.out.println("Nhap sai");
                break;
        }
    }
}