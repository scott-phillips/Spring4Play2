package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import edu.tamu.play.modules.Spring;
import beans.AutowiredBean;

/**
 * Display the name of the autowired bean's dependency that was injected via
 * annotation-based configuration. *
 */

public class Application extends Controller {

	public static Result index() {

		AutowiredBean bean = Spring.getBeanOfType(AutowiredBean.class);

		if (bean == null)
			notFound("Unable to load MyBean from the Spring Context.");

		return ok("bean.getAutowiredProperty().getBeanName() = "
				+ bean.getAutowiredProperty().getBeanName());
	}

}