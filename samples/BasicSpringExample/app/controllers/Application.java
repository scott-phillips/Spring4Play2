package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import edu.tamu.play.modules.Spring;
import beans.MyBean;

/**
 * This simple controller obtains a "MyBean" from spring with all it's
 * dependencies injected and simply display's the bean's name. *
 */

public class Application extends Controller {

	public static Result index() {

		MyBean myBean = Spring.getBeanOfType(MyBean.class);

		if (myBean == null)
			notFound("Unable to load MyBean from the Spring Context.");

		return ok("MyBean.getBeanName() = " + myBean.getBeanName());
	}

}