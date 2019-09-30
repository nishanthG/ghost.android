package com.aqm.botM;

public class Session_Provider {
	public static Session<?> createSession(String choice) {
		if ("Manual".equalsIgnoreCase(choice)){
			return new Manual_Session();
		}
		else if ("Script".equalsIgnoreCase(choice)) {
			return new Script_Session();
		}
		return null;
	}
}