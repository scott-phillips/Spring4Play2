package controllers;

import edu.tamu.play.modules.Spring;

public class ControllerFactory {

	
	public static ControllerOne getControllerOne() {
		return Spring.getBeanOfType(ControllerOne.class);
	}
	
	public static ControllerTwo getControllerTwo() {
		return Spring.getBeanOfType(ControllerTwo.class);
	}
	
}
