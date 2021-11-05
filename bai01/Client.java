package bai01;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
            CalcInterface calc = (CalcInterface) registry.lookup("calcc");
            System.out.println(calc.add(1, 2));
            System.out.println(calc.sub(2, 3));
            System.out.println(calc.mul(3, 5));
            System.out.println(calc.div(16, 8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
