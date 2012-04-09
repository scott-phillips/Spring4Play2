package controllers;

import play.modules.spring.Spring;

/**
 * Produce Spring managed Controller objects with full dependencies.
 */

public class ControllerFactory {

	/**
	 * @return The singleton for ControllerOne
	 */
	public static ControllerOne getControllerOne() {
		return Spring.getBeanOfType(ControllerOne.class);
	}
	
	/**
	 * @return The singleton for ControllerTwo
	 */
	public static ControllerTwo getControllerTwo() {
		return Spring.getBeanOfType(ControllerTwo.class);
	}
	
}
