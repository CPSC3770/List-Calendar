package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewEventActivity extends AppCompatActivity {

    // Members variables

    // Helper
    boolean m_fromSelected = true;

    // Raw data values
    String m_title;
    String m_location;
    int m_fromYear;
    int m_fromMonth;
    int m_fromDay;
    String m_fromDateAsString;
    int m_fromHour;
    int m_fromMinute;
    String m_fromTimeAsString;
    int m_toYear;
    int m_toMonth;
    int m_toDay;
    String m_toDateAsString;
    int m_toHour;
    int m_toMinute;
    String m_toTimeAsString;
    int m_color; // find some library for this?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // "Title" edit text listener
        EditText titleField = (EditText) findViewById(R.id.eventTitle);
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(
                    Editable s) {
                // Do nothing
            }

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {
                m_title = String.valueOf(s);
            }
        });

        // "Location" edit text listener
        EditText locationField = (EditText)findViewById(R.id.eventLocation);
        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(
                    Editable s) {
                // Do nothing
            }

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {
                m_location = String.valueOf(s);
            }
        });

        // "From Date" button listener
        Button selectFromDate = (Button) findViewById(R.id.selectFromDate);
        selectFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_fromSelected = true;
            }
        });

        // "From Time" button listener
        Button selectFromTime = (Button) findViewById(R.id.selectFromTime);
        selectFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_fromSelected = true;
            }
        });

        // "To Date" button listener
        Button selectToDate = (Button) findViewById(R.id.selectToDate);
        selectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_fromSelected = false;
            }
        });

        // "To Time" button listener
        Button selectToTime = (Button) findViewById(R.id.selectToTime);
        selectToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_fromSelected = false;
            }
        });

        // "Save" button listener
        Button submitEventChanges = (Button) findViewById(R.id.submitEventChanges);
        submitEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, ViewCalendarActivity.class));
            }
        });
    }
}
