package bai03;

import java.rmi.Remote;

public interface ISortList extends Remote{
    public int[] sort(int[] arr) throws Exception;
}
