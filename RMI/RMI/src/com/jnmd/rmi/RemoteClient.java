package com.jnmd.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RemoteClient {

	public static void main(String[] args) {
		try {
			IHello ihello = (IHello)Naming.lookup("rmi://localhost:8866/RHello");
			
			System.out.println(ihello.hello());
			System.out.println(ihello.sayHello("zhangsan"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
