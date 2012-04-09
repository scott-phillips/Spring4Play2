package controllers;

import org.springframework.beans.factory.NamedBean;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * A spring-managed play 2.0 controller.
 */

public class ControllerOne extends Controller {

	// Named bean property
	private NamedBean bean = null;	
	public void setBean(NamedBean bean) {
		this.bean = bean;
	}
	
	
	public Result index() {

		if (bean == null)
			notFound("No bean present in Controller 1");
		
		return ok("ControllerOne: bean.getBeanName() = "+bean.getBeanName());
	}

}