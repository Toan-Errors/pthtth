package Tuan8;

public class Server {
    public static void main(String[] args) {
        String name = "rmi://localhost:4444/Server";
        try {
            Implement obj = new Implement();
            java.rmi.registry.LocateRegistry.createRegistry(4444);
            java.rmi.Naming.bind(name, obj);
            System.out.println("Server is ready");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
