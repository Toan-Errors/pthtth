package RMI;

import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);
            Interface client = (Interface) registry.lookup("Server");
            int[][] resultadd = client.addMatrix(new int[][]{{1, 2}, {3, 4}}, new int[][]{{5, 6}, {7, 8}});
            int[][] resultsub = client.subMatrix(new int[][]{{1, 2}, {3, 4}}, new int[][]{{5, 6}, {7, 8}});
            System.out.println("Tổng 2 ma trận");
            for(int i = 0; i < resultadd.length; i++) {
                for(int j = 0; j < resultadd[i].length; j++) {
                    System.out.print(resultadd[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Hiệu 2 ma trận");
            for(int i = 0; i < resultsub.length; i++) {
                for(int j = 0; j < resultsub[i].length; j++) {
                    System.out.print(resultsub[i][j] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
