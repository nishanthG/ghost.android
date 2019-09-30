package com.aqm.botM;

public class Client {
	public static void main(String[] args) {
		Session<?> current_session = Session_Provider.createSession("manual");
		Manual_Testing live_session = (Manual_Testing) current_session.get_session();
		live_session.master.getUID();
		live_session.master.getResXY();
		
		for(Slave_Device slave : live_session.slaves) {
			slave.getUID();
			slave.getResXY();
		}
		
		live_session.target_application.getLaunch_activity();
		live_session.target_application.getPackage_name();
		
		live_session.master.Launch_APP(live_session.target_application.getPackage_name(), live_session.target_application.getLaunch_activity());
		
		for(Slave_Device slave: live_session.slaves) {
			slave.Launch_APP(live_session.target_application.getPackage_name(), live_session.target_application.getLaunch_activity());
		}
		
		
		
		
		
		Session<?> another_session = Session_Provider.createSession("script");
		Run_Script script_session = (Run_Script) another_session.get_session();
		script_session.slaves.get(0).getUID();
		script_session.target_application.getLaunch_activity();
		script_session.target_application.getPackage_name();
	}
}