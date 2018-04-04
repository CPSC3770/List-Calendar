package com.example.cpsc3770.listcalendar;

import com.google.gson.Gson;

/**
 * Created by Alex Hochheiden on 3/30/2018.
 */

class CalendarEvent implements Comparable<CalendarEvent>  {
    // Member variables
    private static int counter = 0;

    private String m_title = "";
    private String m_location = "";
    private int m_fromYear = -1;
    private int m_fromMonth = -1;
    private int m_fromDay = -1;
    private boolean m_fromDateBeingSet = false;
    private int m_fromHour = -1;
    private int m_fromMinute = -1;
    private boolean m_fromTimeBeingSet = false;
    private int m_toYear = -1;
    private int m_toMonth = -1;
    private int m_toDay = -1;
    private boolean m_toDateBeingSet = false;
    private int m_toHour = -1;
    private int m_toMinute = -1;
    private boolean m_toTimeBeingSet = false;
    private int m_color = -1; // find some library for this?
    private int m_uuid = counter++;

    public String viewTitle(){
        return this.m_title;
    }

    public void setTitle(String title){
        this.m_title = title;
    }

    public String viewLocation(){
        return this.m_location;
    }

    public void setLocation(String location){
        this.m_location = location;
    }

    public int viewFromYear(){
        return this.m_fromYear;
    }

    public int viewFromMonth(){
        return this.m_fromMonth;
    }

    public int viewFromDay(){
        return this.m_fromDay;
    }

    public void prepareFromDateChange(){
        this.m_fromDateBeingSet = true;
    }

    public boolean fromDateChange(){
        return this.m_fromDateBeingSet;
    }

    public void setFromDate(
            int fromYear,
            int fromMonth,
            int fromDay){
        this.m_fromYear = fromYear;
        this.m_fromMonth = fromMonth;
        this.m_fromDay = fromDay;

        this.m_fromDateBeingSet = false;
    }

    boolean hasFromDate() {
        return (this.m_fromYear != -1) &&
                (this.m_fromMonth != -1) &&
                (this.m_fromDay != -1);
    }

    public String viewFromDateAsString(){
        if(this.hasFromDate()){
            return String.valueOf(this.m_fromYear) + "/" +
                    String.format("%02d", this.m_fromMonth) + "/" +
                    String.format("%02d", this.m_fromDay);
        }else{
            return CalendarEvent.unspecifiedDateString();
        }
    }

    public int viewFromHour(){
        return this.m_fromHour;
    }

    public int viewFromMinute(){
        return this.m_fromMinute;
    }

    public void prepareFromTimeChange(){
        this.m_fromTimeBeingSet = true;
    }
    public boolean fromTimeChange(){
        return this.m_fromTimeBeingSet;
    }

    public void setFromTime(
            int fromHour,
            int fromMinute){
        this.m_fromHour = fromHour;
        this.m_fromMinute = fromMinute;

        this.m_fromTimeBeingSet = false;
    }

    boolean hasFromTime(){
        return (this.m_fromHour != -1) &&
                (this.m_fromMinute != -1);
    }

    public String viewFromTimeAsString(){
        if(hasFromTime()){
            return String.format("%02d", this.m_fromHour) + ":" +
                    String.format("%02d", this.m_fromMinute);
        }else{
            return CalendarEvent.unspecifiedTimeString();
        }
    }

    public int viewToYear(){
        return this.m_toYear;
    }

    public int viewToMonth(){
        return this.m_toMonth;
    }

    public int viewToDay()
    {
        return this.m_toDay;
    }

    public void prepareToDateChange(){
        this.m_toDateBeingSet = true;
    }

    public boolean toDateChange(){
        return this.m_toDateBeingSet;
    }

    public void setToDate(
            int toYear,
            int toMonth,
            int toDay){
        this.m_toYear = toYear;
        this.m_toMonth = toMonth;
        this.m_toDay = toDay;

        this.m_toDateBeingSet = false;
    }

    boolean hasToDate(){
        return (this.m_toYear != -1) &&
                (this.m_toMonth != -1) &&
                (this.m_toDay != -1);
    }

    public String viewToDateAsString(){
        if(this.hasToDate()) {
            return String.valueOf(this.m_toYear) + "/" +
                    String.format("%02d", this.m_toMonth) + "/" +
                    String.format("%02d", this.m_toDay);
        }else{
            return CalendarEvent.unspecifiedDateString();
        }
    }

    public int viewToHour(){
        return this.m_toHour;
    }

    public int viewToMinute()
    {
        return this.m_toMinute;
    }

    public void prepareToTimeChange(){
        this.m_toTimeBeingSet = true;
    }

    public boolean toTimeChange(){
        return this.m_toTimeBeingSet;
    }

    public void setToTime(
            int toHour,
            int toMinute){
        this.m_toHour = toHour;
        this.m_toMinute = toMinute;

        this.m_toTimeBeingSet = false;
    }

    boolean hasToTime(){
        return (this.m_toHour != -1) &&
                (this.m_toMinute != -1);
    }

    public String viewToTimeAsString(){
        if(this.hasToTime()){
            return String.format("%02d", this.m_toHour) + ":" +
                    String.format("%02d", this.m_toMinute);
        }else{
            return CalendarEvent.unspecifiedTimeString();
        }
    }

    public int viewColor(){
        return this.m_color;
    }

    public int viewUUID(){
        return this.m_uuid;
    }

    public void setColor(int color){
        this.m_color = color;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static CalendarEvent fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, CalendarEvent.class);
    }

    private static String unspecifiedDateString(){
        return "YYYY/MM/DD";
    }

    private static String unspecifiedTimeString(){
        return "HH:MM";
    }

    public void cancelPressed(){
        this.m_fromDateBeingSet = false;
        this.m_fromTimeBeingSet = false;
        this.m_toDateBeingSet = false;
        this.m_toTimeBeingSet = false;
    }

    @Override
    public int compareTo(CalendarEvent rhs) {
        if(this.viewFromYear() != rhs.viewFromYear()){
            Integer.compare(this.viewFromYear(), rhs.viewFromYear());
        }else{
            if(this.viewFromMonth() != rhs.viewFromMonth()){
                Integer.compare(this.viewFromMonth(), rhs.viewFromMonth());
            }else{
                if(this.viewFromDay() != rhs.viewFromDay()){
                    Integer.compare(this.viewFromDay(), rhs.viewFromDay());
                }else{
                    if(this.viewFromHour() != rhs.viewFromHour()){
                        Integer.compare(this.viewFromHour(), rhs.viewFromHour());
                    }else{
                        if(this.viewFromMinute() != rhs.viewFromMinute()){
                            Integer.compare(this.viewFromMinute(), rhs.viewFromMinute());
                        }else{
                            if(this.viewToYear() != rhs.viewToYear())                            {
                                Integer.compare(this.viewToYear(), rhs.viewToYear());
                            }else{
                                if(this.viewToMonth() != rhs.viewToMonth()){
                                    Integer.compare(this.viewToMonth(), rhs.viewToMonth());
                                }else{
                                    if(this.viewToDay() != rhs.viewToDay()){
                                        Integer.compare(this.viewToDay(), rhs.viewToDay());
                                    }else{
                                        if(this.viewToHour() != rhs.viewToHour()){
                                            Integer.compare(this.viewToHour(), rhs.viewToHour());
                                        }else{
                                            if(this.viewToMinute() != rhs.viewToMinute()){
                                                Integer.compare(this.viewToMinute(), rhs.viewToMinute());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }
}
