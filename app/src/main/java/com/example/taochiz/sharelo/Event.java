package com.example.taochiz.sharelo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Event {

    public String Topic;
    public String County;
    public String Detail;
    public String StartDay;
    public String EndDay;

    public Event() {

    }
    public Event(String topic, String county, String detail, String startDay, String endDay){
        Topic = topic;
        County = county;
        Detail = detail;
        StartDay = startDay;
        EndDay = endDay;

    }
}
