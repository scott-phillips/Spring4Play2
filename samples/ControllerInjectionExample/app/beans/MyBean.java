package beans;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.NamedBean;

public class MyBean implements BeanNameAware, NamedBean{
	
	private String name = null;
	
	public void setBeanName(String name) {
		this.name = name;
	}
	
	public String getBeanName() {
		return name;
	}
	
}
