package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Imp extends UnicastRemoteObject implements Interface {
    public Imp() throws Exception {
        super();
    }

    @Override
    public int[][] addMatrix(int[][] matrix1, int[][] matrix2) throws RemoteException {
        int[][] result = new int[matrix1.length][matrix2.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    @Override
    public int[][] subMatrix(int[][] matrix1, int[][] matrix2) throws RemoteException {
        int[][] result = new int[matrix1.length][matrix2.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }
}
    
