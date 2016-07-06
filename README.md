# Batched Flume Appender

The [default log4jAppender for Apache Flume](https://flume.apache.org/FlumeUserGuide.html#log4j-appender) use a synchronous way to append log to flume agent, which means appender send next log only when it can make sure that the previous log has already been received by the flume agent.

It's fair enough when application needs logs' arrival rate as high as possible. But in some circumstances we can tolerate some lost of logs to trade for efficiency. This is why batched flume appender comes.

Batched flume appender is originally from the great lib: [flume log4jAppender](https://flume.apache.org/FlumeUserGuide.html#log4j-appender).

# Usage:

1. Import maven dependency like this:
```xml
<dependency>
    <groupId>org.clojars.ylgrgyq</groupId>
    <artifactId>batched-flume-appender</artifactId>
    <version>1.2.4</version>
</dependency>
```

2. Add log4j configuration:
```xml
  <appender class="com.ylgrgyq.clients.log4jappender.Log4jAppender" name="FlumeAppender">
    <param name="Hostname" value="127.0.0.1"/>
    <param name="Port" value="42424"/>
    <param name="DelayMillis" value="500"/>
    <param name="EventQueueSize" value="20000"/>
    <param name="BatchSize" value="200"/>
    <param name="UnsafeMode" value="true"/>
    <layout class="org.apache.log4j.PatternLayout">
        <param value="%d %-5p %c: %m%n" name="ConversionPattern"/>
    </layout>
  </appender>

  <logger additivity="false" name="GoodLogger">
    <level value="info"/>
    <appender-ref ref="FlumeAppender"/>
  </logger>
```
Then use log4j API to log logs to "GoodLogger".

## Supported Arguments

Argument | meaning
--------- | --------
Hostname | the hostname where you deploy your flume agent
Port     | the port for your flume agent
DelayMillis | Max wait time for logs to accumulate to BatchSize. Batched flume appender will send logs to flume agent if wait time exceed DelayMillis.
BatchSize | Max batch size for logs which can be sent to flume agent at one time
EventQueueSize | the internal queue size.
UnsafeMode | In unsafe mode, batched flume appender will not throw any exception out. Only log error information to Stderr.

# Internal

Batched flume appender is very simple. When there's a log sent to batched flume appender by using log4j API, batched flume appender will cache this log in a internal queue. Then there's a worker thread which will consume the internal queue continously.

The worker thread will sent at most BatchSize logs to flume agent when it consumes BatchSize logs or wait beyond DelayMillis since last sent.


