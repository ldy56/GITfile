package com.jnmd.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteIHello extends UnicastRemoteObject implements IHello {

	public RemoteIHello() throws RemoteException {
		
	}
	@Override
	public String hello() throws RemoteException {
		
		return "hello world!";
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		return "hello " + name + "!!";
	}

}
