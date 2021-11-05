package bai02;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server extends Thread{
    public Server(){
        String name = "rmi://localhost:4444/Server";
        try {
            CheckImplement objRemote = new CheckImplement();
            LocateRegistry.createRegistry(4444);
            Naming.rebind(name, objRemote);
            System.out.println("Server is running");
        } catch (Exception e) {
            //TODO: handle exception
        }     
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
