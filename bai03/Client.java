package bai03;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws Exception {
        int u1[] = {67, 100, 3, 50, 25};
        int u2[] = {93, 66, 77, 4, 88};
        int sort1[], sort2[], sort[];
        String host1 = "localhost";
        String host2 = "localhost";
        if(args.length == 2) {
            host1 = args[0];
            host2 = args[1];
        }
        ISortList sortList1 = (ISortList) Naming.lookup("//" + host1 + "/sort1");
        ISortList sortList2 = (ISortList) Naming.lookup("//" + host2 + "/sort2");
        sort1 = sortList1.sort(u1);
        sort2 = sortList2.sort(u2);
        sort = new Client().merge(sort1, sort2);
        for(int i = 0; i < sort.length; i++) {
            System.out.print(sort[i] + " ");
        }
    }

    private int[] merge(int[] sort1, int[] sort2) {
        int sort[] = new int[sort1.length + sort2.length];
        int i = 0, j = 0, k = 0;
        while(i < sort1.length && j < sort2.length) {
            if(sort1[i] < sort2[j]) {
                sort[k++] = sort1[i++];
            } else {
                sort[k++] = sort2[j++];
            }
        }
        return null;
    }
}
