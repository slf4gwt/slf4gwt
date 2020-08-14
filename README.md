# slf4gwt

slf4gwt is a slf4j emulation for GWT. With this project it is possible for a Java developer to use the slf4j API on the 
GWT client side. So it is possible to use slf4j on server and client code.

## Motivation
On server code it is a common practice to use slf4j to hide the used logging framework. On GWT client side slf4j cannot 
be used and so it is up to the developer to keep an eye on her code and check where it runs. Especially shared code is a
problem here. The solution for the problem is slf4gwt and the slf4j api is used on client and server side.

Like the well-known slf4j, sl4fgwt uses a logging framework to write the logging messages. GWT provides a java.util.logging 
implementation, and we use this implementation to write the messages to the right appender.

## Inspiration
slf4gwt is inspired by some other open source projects. The most important one is
[gwt-log](https://github.com/fredsa/gwt-log) the former number one logging framework for GWT. It was used before GWT 2.1 very often. Next we like [slf4j-gwt](https://github.com/FinamTrade/slf4j-gwt) a lot
but the implementation stopped some years ago, and slf4gwt likes to bridge the gap and provide a state of the art version,
that can be used with the current GWT implementations.

## Usage

*IMPORTANT*: slf4gwt is in beta state for now, things may change 😎  

First you need to import the jar into your project.

Then add the logging module to your module (*.gwt.xml):

```xml
<inherits name="org.slf4jgwt.logging.gwt.Logging"/>
```

You can also set logging level (TRACE, DEBUG, INFO, WARN, ERROR):

```xml
<set-property name="slf4gwt.log.level" value="INFO"/>
```

And you can configure the used appenders:

```xml
<set-property name="slf4gwt.log.handler.console" value="ENABLED" />
<set-property name="slf4gwt.log.handler.developmentMode" value="ENABLED" />
<set-property name="slf4gwt.log.handler.hasWidgets" value="DISABLED" />
<set-property name="slf4gwt.log.handler.system" value="DISABLED" />
<set-property name="slf4gwt.log.handler.simpleRemote" value="DISABLED" />
```

### Logging in development and production mode:
Normally the logging is enabled in development mode and disabled in production mode.
If you need to get logs on production mode, you have to enable the GWT logging as defined on the
[GWT logging manual](http://www.gwtproject.org/doc/latest/DevGuideLogging.html#Configuring_GWT_Logging). Or simply add this line to you *.gwt.xml file:

```xml
<set-property name="gwt.logging.enabled" value="TRUE"/>
```

Possible values are <code>TRUE</code>, <code>WARNING</code>, <code>SEVERE</code> and FALSE (if you don't need any logging at all).
