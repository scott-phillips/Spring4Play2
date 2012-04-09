package play.modules.spring;

import java.util.Map;
import play.Play;

public class Spring {

    public static Object getBean(String name) {
        if (SpringPlugin.applicationContext == null) {
            throw new RuntimeException("Unable to obtain spring bean because the application context has not been defined."); 
        }
        return SpringPlugin.applicationContext.getBean(name);
    }
    
    public static <T> T getBeanOfType(Class<T> type) {
        Map<String, Object> beans = getBeansOfType(type);
        if(beans.isEmpty()) {
            return null;
        }
        return (T)beans.values().iterator().next();
    }
    
    public static Object getBeanOfType(String type) {
        try {
            return getBeanOfType(Play.application().classloader().loadClass(type));
        } catch (ClassNotFoundException ex) {
        	throw new RuntimeException(ex);
        }
    }
    
    public static <T> Map<String, T> getBeansOfType(Class type) {
        if (SpringPlugin.applicationContext == null) {
            throw new RuntimeException("Unable to obtain spring bean because the application context has not been defined."); 
        }
        return SpringPlugin.applicationContext.getBeansOfType(type);
    }
    
}
