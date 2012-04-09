package beans;

import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A simple bean with an @autowired property of a named bean.
 * 
 */
@Component // Important for component scaning
public class AutowiredBean {
	
	private NamedBean autowired = null;
	
	@Autowired // Mark as autowired
	public void setAutowiredProperty(NamedBean bean) {
		this.autowired = bean;
	}
	
	public NamedBean getAutowiredProperty() {
		return autowired;
	}

}
