package com.aqm.botM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manual_Testing {
	
	/*
	 * Takes required info from JSON:
	 * info = {
	 * 'master' : {'UID' : "XXXXX", 'Resolution' : [123, 345]},
	 * 'slave' : {'UID' : ["XXXXX","XXXXX","XXXXX","XXXXX"], 'Resolution' : [[123, 345],[123, 345],[123, 345],[123, 345]]}
	 * }
	 */
	
	public Master_Device master;
	public List<Slave_Device> slaves;
	public UAT target_application;
	
	private Map<String , HashMap<String, ?>> json = new HashMap<>();
	
	public Manual_Testing() {
		create_Session();
	}
	
	private void create_Session() {
		String UID = "XXXxXX";
		int[] ResXY = new int[] {1234,3456};
		
		master = new Master_Device(UID, ResXY);
		
		for (int i = 0; i < 5; i++) {
			slaves.add(new Slave_Device(UID, ResXY));
		}
		
		target_application = new UAT();
		target_application.extract_info("XX/YY/ZZZ");
	}
}