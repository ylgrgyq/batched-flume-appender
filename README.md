Flume log4jAppender can only sent log one by one in order to insure that only send new log after the previouse log is safely received by the next flume agent. But this kind of behavior will make the application which using this appender lib performance suffer. 

batched-flume-appender will batch the log it received till the batch is full or some specific delay time is reached. 

Copyed a lot of code from [flume log4jAppender](https://flume.apache.org/FlumeUserGuide.html#log4j-appender), if there's any problem please let me know.


