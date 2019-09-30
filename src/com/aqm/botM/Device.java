package com.aqm.botM;

public class Device {
	
	private String UID;
	
	public void Launch_APP(String package_name, String launch_activity) {
		String LAUNCH_APP_ = "adb -s " + this.UID + " shell am start -a android.intent.action.MAIN -n " + package_name + "/" + launch_activity;
		Event.run_command(LAUNCH_APP_);
	}
	
	public void Close_APP(String package_name) {
		String CLOSE_APP_ = "adb -s " + this.UID + " shell am force-stop " + package_name;
		Event.run_command(CLOSE_APP_);
	}
}