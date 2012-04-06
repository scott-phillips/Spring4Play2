Play 2.0 Spring Module
======================

The is a relatively simple module for the relatively new Play 2.0 Framework. This is my 
second application in the new version of the framework, the first was the basic tutorial 
app that is described in the play documentation. So, don't expect too much.

The other major point is that this module is basically a fork and repackaging of the 
original Spring plugin from Play 1.0. The original plugin is by [Nicolas Leroux](https://github.com/pepite) 
and available on github, at 
[https://github.com/pepite/Play--framework-Spring-module](https://github.com/pepite/Play--framework-Spring-module)

How to Use
----------

If your application uses a single context then you're in luck you don't need to configure 
anything. Just put your Spring bean configuration in ``conf/application-context.xml``. The 
plugin will look at this location by default, otherwise you will need to specify the context. 
To do this use the "spring.context" parameter passing it the path to the spring context file. 
The path is relative to the application's ``conf`` directory.

    spring.context = path/to/application-context.xml.

The other available parameters control how the application context is processed. They all evaluate 
to true, so you don't need to mess with them unless you need to turn them off.

    spring.namespace-aware = false
    spring.add-play-properties = false
    
Enabeling namspaces allows you to use the ``util`` and other namespaces within your application's 
context. The ``add-play-properties`` will automatically load all the entries from the 
``application.conf`` into the spring configuration for easy reference.


Limitations
-----------

At the present time this plugin does not support Component Scanning. In the future.