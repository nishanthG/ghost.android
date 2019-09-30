package com.aqm.botM;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Event {
	
	private void tap(String[] XY) {
		String tap_cmd = "adb -s " + UID + " shell input tap " + XY[0] + " " + XY[1];
		run_command(tap_cmd);
	}
	
	private void touch(String key_code) {
		String touch_cmd = "adb -s " + UID + " shell input keyevent "+ key_code;
		run_command(touch_cmd);
	}
	
	private void type(String text) {
		String text_cmd = "adb -s " + UID + " shell input type "+ text;
		run_command(text_cmd);
	}
	
	private void swipe(String[] XY) {
		String swipe_cmd = "adb -s " + UID + " shell input swipe " + XY[0] + " " + XY[1] + " " + XY[2] + " " + XY[3];
		run_command(swipe_cmd);
	}
	
	static void perform(String event_type, String[] args) {
		switch (event_type) {
		case "tap":
			new Event().tap(args);
			break;
			
		case "touch":
			new Event().touch(args[args.length - 1]);
			break;
			
		case "type":
			new Event().type(args[args.length - 1]);
			break;
			
		case "swipe":
			new Event().swipe(args);
			break;
			
		default:
			break;
		}
	}
	
	static void run_command(String command) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.err.println(line);
			}
			p.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}