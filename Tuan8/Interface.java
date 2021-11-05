package Tuan8;

import java.rmi.Remote;

public interface Interface extends Remote{
    public String UpperCase(String str) throws java.rmi.RemoteException;
    public String Reverse(String str) throws java.rmi.RemoteException;
    public String Menu() throws java.rmi.RemoteException;
}