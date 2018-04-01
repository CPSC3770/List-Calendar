package com.example.cpsc3770.listcalendar;

import java.io.Serializable;

/**
 * Created by Alex Hochheiden on 3/30/2018.
 */

class CalendarEvent implements Serializable {

    // member variables
    private String m_title = "";
    private String m_location = "";
    private int m_fromYear = -1;
    private int m_fromMonth = -1;
    private int m_fromDay = -1;
    private int m_fromHour = -1;
    private int m_fromMinute = -1;
    private int m_toYear = -1;
    private int m_toMonth = -1;
    private int m_toDay = -1;
    private int m_toHour = -1;
    private int m_toMinute = -1;
    private int m_color = -1; // find some library for this?

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

    public void setFromYear(int fromYear){
        this.m_fromYear = fromYear;
    }

    public int viewFromMonth(){
        return this.m_fromMonth;
    }

    public void setFromMonth(int fromMonth){
        this.m_fromMonth = fromMonth;
    }

    public int viewFromDay(){
        return this.m_fromDay;
    }

    public void setFromDay(int fromDay){
        this.m_fromDay = fromDay;
    }

    private boolean hasFromDate() {
        return (this.m_fromYear != -1) &&
                (this.m_fromMonth != -1) &&
                (this.m_fromDay != -1);
    }

    public String viewFromDateAsString(){
        return String.valueOf(this.m_fromYear) + "/" +
                String.valueOf(this.m_fromMonth) + "/" +
                String.valueOf(this.m_fromDay);
    }

    public int viewFromHour(){
        return this.m_fromHour;
    }

    public void setFromHour(int fromHour){
        this.m_fromHour = fromHour;
    }

    public int viewFromMinute(){
        return this.m_fromMinute;
    }

    public void setFromMinute(int fromMinute){
        this.m_fromMinute = fromMinute;
    }

    public String viewFromTimeAsString(){
        return String.valueOf(this.m_fromHour) + ":" +
                String.valueOf(this.m_fromMinute);
    }

    public int viewToYear()
    {
        return this.m_toYear;
    }

    public void setToYear(int toYear)
    {
        this.m_toYear = toYear;
    }

    public int viewToMonth()
    {
        return this.m_toMonth;
    }

    public void setToMonth(int toMonth)
    {
        this.m_toMonth = toMonth;
    }

    public int viewToDay()
    {
        return this.m_toDay;
    }

    public void setToDay(int toDay)
    {
        this.m_toDay = toDay;
    }

    public String viewToDateAsString()
    {
        return String.valueOf(this.m_toYear) + "/" +
                String.valueOf(this.m_toMonth) + "/" +
                String.valueOf(this.m_toDay);
    }

    public int viewToHour()
    {
        return this.m_toHour;
    }

    public void setToHour(int toHour)
    {
        this.m_toHour = toHour;
    }

    public int viewToMinute()
    {
        return this.m_toMinute;
    }

    public void setToMinute(int toMinute)
    {
        this.m_toMinute = toMinute;
    }

    public String viewToTimeAsString()
    {
        return String.valueOf(this.m_toHour) + ":" +
                String.valueOf(this.m_toMinute);
    }

    public int viewColor(){
        return this.m_color;
    }

    public void setColor(int color){
        this.m_color = color;
    }
}
