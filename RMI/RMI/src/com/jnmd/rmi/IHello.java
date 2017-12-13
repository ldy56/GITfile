package com.jnmd.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
	public String hello() throws RemoteException;
	public String sayHello(String name) throws RemoteException;
}
