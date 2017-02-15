package com.jd.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class DemoServer {
	public static final int SERVER_PORT = 8089;
	
	
	public static void main (String [] args) {
		
		DemoServer svr = new DemoServer();
		svr.startServer();
	}
	
	public void startServer() {
		try {
			// initialize processor
			DemoService.Processor<DemoService.Iface> processor = 
					new DemoService.Processor<DemoService.Iface>(new DemoServiceImpl());
			// define socket 
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			
			// define server parameters
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(new TCompactProtocol.Factory());
			
			// initialize server and start 
			TServer server = new TSimpleServer(tArgs);
			server.serve();
			
		}catch (Exception e) {
			System.out.println("Server start error !!!\n"  + e);
			
		}
	}
}


