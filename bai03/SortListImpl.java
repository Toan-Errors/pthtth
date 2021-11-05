package bai03;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortListImpl extends UnicastRemoteObject implements ISortList {

    public SortListImpl() throws Exception {
        super();
    }

    @Override
    public int[] sort(int[] arr) throws RemoteException {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

}