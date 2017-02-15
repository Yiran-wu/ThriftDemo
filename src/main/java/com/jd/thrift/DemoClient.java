package com.jd.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class DemoClient {
	public static final String SERVER_IP   = "127.0.0.1";
	public static final int    SERVER_PORT = 8089;
	public static final int    TIMEOUT = 300;
	
	
	public static void main(String [] args) throws TException, InterruptedException {
		DemoClient  cli = new DemoClient();
		cli.startClient();
	}
	
	public void startClient () throws TException, InterruptedException {
		TTransport transport = null;
		
		try {
			transport = new TSocket (SERVER_IP, SERVER_PORT, TIMEOUT); // define socket
			TProtocol protocol = new TCompactProtocol (transport);	   // define transport type
			
			DemoService.Client client = new DemoService.Client(protocol); 
			
			transport.open();
			for (int i = 0; i < 10; i++) {
				System.out.println("Get time from server: " + 
									client.getCurrentTime(String.valueOf(System.currentTimeMillis())));
				Thread.sleep(1000);
			}
			
		}catch (TTransportException e) {
			
			System.out.println("Start client failed, " + e);
		}finally {
			if (transport != null) {
				transport.close();
			}
		}
	}
}




