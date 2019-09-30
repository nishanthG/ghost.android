package com.aqm.botM;

public class Script_Session implements Session<Run_Script>{

	@Override
	public Run_Script get_session() {
		return new Run_Script();
	}
}