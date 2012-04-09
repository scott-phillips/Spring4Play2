package controllers;

import org.springframework.beans.factory.NamedBean;

import play.mvc.Controller;
import play.mvc.Result;

public class ControllerOne extends Controller {

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