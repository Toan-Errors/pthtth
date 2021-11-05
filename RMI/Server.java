package RMI;

public class Server {
    public static void main(String[] args) {
        try {
            Imp imp = new Imp();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("rmi://localhost:1099/Server", imp);
            System.out.println("Server is ready");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
