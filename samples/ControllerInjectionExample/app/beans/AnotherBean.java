package beans;

import org.springframework.beans.factory.NamedBean;

public class AnotherBean implements NamedBean {
	
	@Override
	public String getBeanName() {
		return "AnotherBean";
	}

}
