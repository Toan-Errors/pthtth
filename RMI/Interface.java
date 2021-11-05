package RMI;

import java.rmi.Remote;

public interface Interface extends Remote {
    public int[][] addMatrix(int[][] matrix1, int[][] matrix2) throws java.rmi.RemoteException;
    public int[][] subMatrix(int[][] matrix1, int[][] matrix2) throws java.rmi.RemoteException;
}
