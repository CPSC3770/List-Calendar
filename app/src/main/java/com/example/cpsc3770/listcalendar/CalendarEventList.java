package com.example.cpsc3770.listcalendar;

import com.google.gson.Gson;

import java.util.List;

// The purpose of this class is to convert a list of events to a string using Gson
// so it can be used to save the instance state of ViewCalendarActivity

class CalendarEventList {
    private List<CalendarEvent> m_eventList;

    public List<CalendarEvent> viewEventList(){
        return this.m_eventList;
    }

    public void setEventList(List<CalendarEvent> eventList){
        this.m_eventList = eventList;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static CalendarEventList fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, CalendarEventList.class);
    }
}
