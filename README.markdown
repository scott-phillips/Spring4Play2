# Spring Module for Play 2.0 #

This module manages helps you integrate Spring managed beans directly within your play 2.0
application. This module is a continuation of [Nicolas Leroux](https://github.com/pepite)'s
original Spring Module for play 1.x, available at 
[https://github.com/pepite/Play--framework-Spring-module](https://github.com/pepite/Play--framework-Spring-module)

## Install ##

For the immediate future this plugin is available from an Texas A&M University's local unindexed
Maven repository. In the future when then new modules site is ready at 
`http://modules.playframework.org` this plugin will be deployed there. Until then you can use A&M's
local repository, but since it is not indexed by Maven Central you will need to add the repository
explicitly in your sbt build configuration.

Add the dependency shown below on this module, along with the definition for TAMU's maven repository.

>
> val appDependencies = Seq(
>     "play" % "spring_2.9.1" % "1.1"
> )
>
> val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
>     resolvers += "TAMU Release Repository" at "https://maven.library.tamu.edu/content/repositories/releases/"
> )
>

## Configuration ##

This module is very simple to configure, simply place your Spring application context in
`conf/application-context.xml`. When this module is installed Play will look at this location and
construct a context to load Spring managed beans from. Alternatively you may specify a series of
optional configuration parameters in `conf/application.conf` determining how the context is loaded.

If you need to load an alternative application-context file use the `spring.context` parameter. In
addition there are two optional parameters controlling how the context is interpreted. By default
play assumes the context uses namespaces which were introduced in Spring 2.0. If you do not need
them or they are causing you problems you can turn off namespaces. The other option is to include
Play configuration (i.e. items from conf/application.conf) using Spring's 
PropertyPlaceholderConfigurer. This means that you can access play-based configuration within your
spring context file by simple using `${...}`. 

>
> spring.context = another-application-context.xml
> # Defaults to "application-context.xml"
>
> spring.namespace-aware = true
> # Defaults to true
> 
> spring.add-play-properties = true
> # Defaults to true
>

## How To Use Play With Spring ##

### Retrieving beans from application code ###
You can obtain Spring managed beans instances from within your play application using the 
`play.modules.spring.Spring` helper. 

>
> import play.modules.spring.Spring
>
> // Reference a bean by name
> MyBean bean = Spring.getBean("byBeanName");
>
> // Reference a bean by type
> MyBean bean = Spring.getBeanOfType(MyBean.class);
>
> // Reference a set of beans by type
> Map<String,MyBean> = Spring.getBeansOfType(MyBean.class);
>

See example project: https://github.com/scott-phillips/Spring4Play2/tree/master/samples/BasicSpringExample

### Component scanning & annotation-based configuration ###

This spring module supports annotation-based configuration of Spring beans through component
scanning. Earlier version of the Play framework and Spring Module required extra play specific
configuration for Spring's component scanning and annotation-based configuration to work correctly.
With the new version of Play 2.0 this is no longer required because behind the scenes the Play 
framework is not magically manipulating the classpath. To enable annotation configuration all you
need to do is set up the appropriate spring context using the annotation-config and component-scan
elements. Here's an example:

>
>    <context:annotation-config />
>    <context:component-scan base-package="beans" />
>

See example project: https://github.com/scott-phillips/Spring4Play2/tree/master/samples/AnnotationConfigExample

### Injecting dependencies into controllers ###

You can inject dependencies into Play controllers! Earlier versions of the Play framework did not
allow for dependency injection into controller objects because they were all static. However that
restriction is no longer present with the newest version of the framework. 

To enable spring-managed controllers in your application you will need to follow at least some kind
of singlton/factory pattern for constructing controllers. Either with an explicit controller
factory or through a singleton pattern with static accessory method (i.e. getInstance()). For
example using a `controllers.ControllerFactory` with two methods as shown:

>
> public static ControllerOne getControllerOne() {
>     return Spring.getBeanOfType(ControllerOne.class);
> }
>
> public static ControllerTwo getControllerTwo() {
>     return Spring.getBeanOfType(ControllerTwo.class);
> }
>

The factory will produce controller instances that must inherit from Play's
`play.mvc.Controller` class. However the action methods do not need to remain static, they just
need to return a `play.mvc.Result` object for display. Using this example your need to modify your
`conf/routes` file to accommodate the ControllerFactory, as shown below: 
> 
> GET    /one     controllers.ControllerFactory.getControllerOne.index()
> GET    /two     controllers.ControllerFactory.getControllerTwo.index()
> 

See example project: https://github.com/scott-phillips/Spring4Play2/tree/master/samples/ControllerInjectionExample