package bai02;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CheckInterface extends Remote{
    public boolean check(String username, String password) throws RemoteException;
    public int add(int a, int b) throws RemoteException;
    public int sub(int a, int b) throws RemoteException;
    public int mul(int a, int b) throws RemoteException;
    public int div(int a, int b) throws RemoteException;
}