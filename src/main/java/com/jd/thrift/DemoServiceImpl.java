package com.jd.thrift;

public class DemoServiceImpl implements DemoService.Iface {
	public String getCurrentTime(String time) throws org.apache.thrift.TException {
		System.out.println("Client time:" + time);
		return String.valueOf(System.currentTimeMillis());
	}

}
