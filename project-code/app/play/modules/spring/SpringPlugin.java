package play.modules.spring;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.xml.sax.InputSource;

import play.Logger;
import play.Play;
import play.Plugin;
import play.Application;

public class SpringPlugin extends Plugin  {

    /**
     * System property keys
     */
    public static final String SYSTEM_PROPERTY_SPRING_CONTEXT_PATH = "system.application.context";

    /** Configuration keys **/
    private static final String SPRING_CONTEXT_PATH = "spring.context";
    private static final String SPRING_NAMESPACE_AWARE = "spring.namespace-aware";
    private static final String SPRING_ADD_PLAY_PROPERTIES = "spring.add-play-properties";

    /** The spring context available for anyone */
    private Application app;
    public static GenericApplicationContext applicationContext;
    //private long startDate = 0;

    
    
    public SpringPlugin(play.api.Application app) {
    	super();
    	Logger.debug("Spring plugin initialized");
    	this.app = new Application(app);
    }
    
//    @Override
//    public void detectChange() {
//        if (Play.mode == Mode.DEV) {
//            VirtualFile appRoot = VirtualFile.open(Play.applicationPath);
//            long mod = appRoot.child("conf/application-context.xml").lastModified();
//            if (mod > startDate) {
//                throw new RuntimeException("conf/application-context.xml has changed");
//            }
//        }
//    }

    @Override
    public void onStop() {
        if (applicationContext != null) {
            Logger.debug("Closing Spring application context");
            applicationContext.close();
        }
    }

    @Override
    public void onStart() {
    	Logger.debug("Spring Plugin Starting");

        String contextPath = System.getProperty(SYSTEM_PROPERTY_SPRING_CONTEXT_PATH);

        if(contextPath == null || contextPath.isEmpty()){
            contextPath = app.configuration().getString(SPRING_CONTEXT_PATH);
        }

    	String namespaceAware = app.configuration().getString(SPRING_NAMESPACE_AWARE);
    	String addPlayProperties = app.configuration().getString(SPRING_ADD_PLAY_PROPERTIES);
    	
    	URL url = null;
    	if (contextPath != null) {
    		Logger.debug("Loading application context: "+contextPath);
    		url = app.classloader().getResource(contextPath);
    	} 
    	if (url == null) {
    		Logger.debug("Loading default application context: application-context.ml");
            url = app.classloader().getResource("application-context.xml");
        }
        if (url != null) {
            InputStream is = null;
            try {
                Logger.debug("Starting Spring application context from "+url.toExternalForm());
                applicationContext = new GenericApplicationContext();
                applicationContext.setClassLoader(Play.application().classloader());
                XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(applicationContext);
                if (!"false".equalsIgnoreCase(namespaceAware)) {
                	Logger.debug("Application context is namespace aware");
                    xmlReader.setNamespaceAware(true);
                } else {
                	Logger.debug("Application context is NOT namespace aware");
                }
                xmlReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);

                // 
                // Load Play Configuration
                //
                if (!"false".equalsIgnoreCase(addPlayProperties)) {
                    Logger.debug("Adding PropertyPlaceholderConfigurer with Play properties");
                    
                    // Convert play's configuration to plain old java properties
                    Properties properties = new Properties();
                    Set<String> keys = app.configuration().keys();
                    for ( String key : keys) {
                    	try  {
	                    	String value = app.configuration().getString(key);
	                    	properties.setProperty(key, value);
                    	} catch (Throwable t) {
                    		// Some config items are complex, so we'll just skip those for now.
                    	}
                    }
                    
                    PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
                    configurer.setProperties(properties);
                    applicationContext.addBeanFactoryPostProcessor(configurer);
                } else {
                    Logger.debug("PropertyPlaceholderConfigurer with Play properties NOT added");
                }
                
                is = url.openStream();
                xmlReader.loadBeanDefinitions(new InputSource(is));
                ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
                Thread.currentThread().setContextClassLoader(app.classloader());
                try {
                    applicationContext.refresh();
                    //startDate = System.currentTimeMillis();
                } catch (BeanCreationException e) {
                    Throwable ex = e.getCause();
                    throw new RuntimeException("Unable to instantiate Spring application context",ex);
                } finally {
                    Thread.currentThread().setContextClassLoader(originalClassLoader);
                }
            } catch (IOException e) {
                Logger.error("Can't load spring config file",e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        Logger.error("Can't close spring config file stream",e);
                    }
                }
            }
        }
    }
}
