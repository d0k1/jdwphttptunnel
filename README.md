# Java(tm) Debug Wire Protocol HTTP Tunnel

This project makes possible to debug a java web applications via http protocol.
Spring-boot devtools has same functionality, but sometimes it's not an option to make legacy app spring-boot based.

Concepts of remote debugging by http protocol is the same as defined in spring-boot manual:
* you start a debugee application with additional command line args like ``-Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n``
* you start a debugee client side application with additional command line args ``-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000``
* you start a debuger from IDE you like, using jdwp port 8000 and host localhost
* debugee client reads traffic from port 8000 and passes it to a remote debugee

