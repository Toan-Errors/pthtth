package bai01;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            
            CalcImplement calc = new CalcImplement();
            System.out.println("Server is running...");
            LocateRegistry.createRegistry(7070);
            Registry registry = LocateRegistry.getRegistry(7070);

            registry.rebind("calcc", calc);
            System.out.println("Server is ready...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
