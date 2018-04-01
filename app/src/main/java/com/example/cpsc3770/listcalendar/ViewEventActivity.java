package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class ViewEventActivity extends AppCompatActivity {

    // Member variables
    CalendarEvent m_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Receive event object from previous activity
        Bundle bundle = getIntent().getExtras();
        String calendarEventAsJson = bundle.getString("event");

        if(calendarEventAsJson == null) {
            m_event = new CalendarEvent();
        }else{
            m_event = CalendarEvent.fromJson(calendarEventAsJson);
        }

        // Set views
        TextView fromDateView = (TextView)findViewById(R.id.viewFromDate);
        fromDateView.setText(m_event.viewFromDateAsString());

        TextView fromTimeView = (TextView)findViewById(R.id.viewFromTime);
        fromTimeView.setText(m_event.viewFromTimeAsString());

        TextView toDateView = (TextView)findViewById(R.id.viewToDate);
        toDateView.setText(m_event.viewToDateAsString());

        TextView toTimeView = (TextView)findViewById(R.id.viewToTime);
        toTimeView.setText(m_event.viewToTimeAsString());

        // "Title" edit text listener
        EditText titleField = (EditText)findViewById(R.id.eventTitle);
        titleField.setText(m_event.viewTitle());
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
                m_event.setTitle(String.valueOf(s));
            }
        });

        // "Location" edit text listener
        EditText locationField = (EditText)findViewById(R.id.eventLocation);
        locationField.setText(m_event.viewLocation());
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
                m_event.setLocation(String.valueOf(s));
            }
        });

        // "From Date" button listener
        Button selectFromDate = (Button)findViewById(R.id.selectFromDate);
        selectFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareFromDateChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, SetDateActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "From Time" button listener
        Button selectFromTime = (Button)findViewById(R.id.selectFromTime);
        selectFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareFromTimeChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, SetTimeActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "To Date" button listener
        Button selectToDate = (Button)findViewById(R.id.selectToDate);
        selectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareToDateChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, SetDateActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "To Time" button listener
        Button selectToTime = (Button)findViewById(R.id.selectToTime);
        selectToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareToTimeChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, SetTimeActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "Save" button listener
        Button submitEventChanges = (Button)findViewById(R.id.submitEventChanges);
        submitEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, ViewCalendarActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }
}
