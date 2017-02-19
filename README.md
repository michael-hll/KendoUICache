# KendoUIWithCache
A sample code that using KendoUI and Ehcache together using SpringMVC

This sample project using KendoUI grid, and chart to display a beautiful UI. In the backend it uses restful apis to deal with CRUD requests.

In oder to improve the performance, in the back-end integrated Ehcache into Spring Cache.

To understand the code:

*** UI ***
/resources/js folder:
It used two js frameworks: jquery.min.js & kendo.all.min.js
/resources/styles folder:
Common style files used by kendo ui.
IMPORTANT: KendoUI is NOT free!!!
/resources/KendoEhcacheDemo.html is the source code of the ui.

*** RESTful API ***
From the HomeController.java you can find all the CRUD restful apis there.

*** Ehcache ***
/resources/ehcache.xml is the cache configuration of Ehcache
serlet-context.xml has all the configuratioins for Ehcache

In order to run this sample code, you need to run in a server, eg your local tomcat, and enter the below address to access the main UI:
http://localhost:8080/demo/resources/KendoEhcacheDemo.html

In the folder UI-snapshots you can find the UI pictures.

Hope this sample helps you!
