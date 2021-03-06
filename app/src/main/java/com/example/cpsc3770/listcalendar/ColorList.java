package com.example.cpsc3770.listcalendar;

import com.google.gson.Gson;

import java.util.List;

class ColorList {
    private List<String> m_colorList;

    public List<String> viewColorList(){
        return this.m_colorList;
    }

    public void setColorList(List<String> colorList){
        this.m_colorList = colorList;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ColorList fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ColorList.class);
    }
}
