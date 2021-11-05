package bai03;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException, Exception {
        System.out.println("Server is running...");
        String number = "";
        if(args.length > 0) {
            number = args[0];
        }
        
        Naming.rebind("//localhost/Sort" + number, new SortListImpl());
        System.out.println("The sort Server" + number + " is ready.");
    }
}
