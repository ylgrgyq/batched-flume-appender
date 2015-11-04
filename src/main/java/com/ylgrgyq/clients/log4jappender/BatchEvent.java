package com.ylgrgyq.clients.log4jappender;

import org.apache.flume.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/11/2.
 * Author: ylgrgyq
 */
public class BatchEvent {
    private static final int defaultInitSize = 128;
    private final List<Event> events;

    public BatchEvent(){
        this(defaultInitSize);
    }

    public BatchEvent(int initSize){
        events = new ArrayList<>(initSize);
    }

    public void addEvent(final Event flumeEvent){
        events.add(flumeEvent);
    }

    public List<Event> getEvents(){
        return events;
    }
}
