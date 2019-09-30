package com.aqm.botM;

public class Slave_Device extends Device{
	
	private String UID;
	private int[] ResXY = new int[2];
	
	public Slave_Device(String UID, int[] ResXY) {
		this.UID = UID;
		this.ResXY = ResXY;
	}
	
	public String getUID() {
		return UID;
	}
	
	public int[] getResXY() {
		return ResXY;
	}
	
	
	public static void main(String[] args) {
		String UID = "XXXXX";
		int[] ResXY = new int[] {1234, 3456};
	}
}