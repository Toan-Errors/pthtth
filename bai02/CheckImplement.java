package bai02;

import java.io.BufferedReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CheckImplement extends UnicastRemoteObject implements CheckInterface {
    HashMap account;
    public CheckImplement() throws RemoteException {
        super();
        account = new HashMap();
    }

    @Override
    public boolean check(String username, String password) throws RemoteException {
        String line;
        boolean check = false;
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader("/home/errors/GitHub/pthtth/bai02/account.txt"));
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                account.put(st.nextToken(), st.nextToken());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(account.containsKey(username)) {
            if(account.get(username).equals(password)) {
                check = true;
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        return check;
    }
    public int add(int a, int b) throws java.rmi.RemoteException {
        return a + b;
    }

    public int sub(int a, int b) throws java.rmi.RemoteException {
        return a - b;
    }

    public int mul(int a, int b) throws java.rmi.RemoteException {
        return a * b;
    }

    public int div(int a, int b) throws java.rmi.RemoteException {
        return a / b;
    }
}
