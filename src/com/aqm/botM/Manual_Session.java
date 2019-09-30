package com.aqm.botM;

public class Manual_Session implements Session<Manual_Testing>{
	
	public Master_Device master;
	
	@Override
	public Manual_Testing get_session() {
		return new Manual_Testing();
	}
}