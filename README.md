Flume log4jAppender can only sent log event one after another in a synchronized way. Which means it'll block if there's a pending log event exist until this pending log event is confirmed receiption by flume agent. 

batched-flume-appender will batch the log it received till the batch is full or some specific delay time is reached. 

Copyed a lot of code from [flume log4jAppender](https://flume.apache.org/FlumeUserGuide.html#log4j-appender), if there's any problem please let me know.

Usage:
```
<dependency>
    <groupId>org.clojars.ylgrgyq</groupId>
    <artifactId>batched-flume-appender</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```


