package com.example.cpsc3770.listcalendar;

public class ColorItem {

    private String m_name;
    private int m_img;

    public ColorItem(String color_name, int color_img) {

        m_name = color_name;
        m_img = color_img;
    }

    public String getColorName() {
        return m_name;
    }

    public int getColorImg() {
        return m_img;
    }
}
