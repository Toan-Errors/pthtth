package bai01;

import java.rmi.server.UnicastRemoteObject;

public class CalcImplement extends UnicastRemoteObject implements CalcInterface {
    public CalcImplement() throws java.rmi.RemoteException {
        super();
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
