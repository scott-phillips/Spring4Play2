package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
	
	private AnotherBean autowired = null;
	
	@Autowired
	public void setAutowiredBean(AnotherBean bean) {
		this.autowired = bean;
	}
	
	public AnotherBean getAutowiredBean() {
		return autowired;
	}

}
