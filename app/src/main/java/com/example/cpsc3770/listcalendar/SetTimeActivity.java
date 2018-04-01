package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SetTimeActivity extends AppCompatActivity {
    // Member variables
    private CalendarEvent m_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        // Receive event object from previous activity
        Bundle bundle = getIntent().getExtras();
        String calendarEventAsJson = bundle.getString("event");
        m_event = CalendarEvent.fromJson(calendarEventAsJson);

        final TimePicker timePicker = findViewById(R.id.timePicker);

        // Set hand positions to current values
        if(m_event.fromTimeChange()){
            timePicker.setHour(m_event.viewFromHour());
            timePicker.setMinute(m_event.viewFromMinute());
        }else{
            if(m_event.toTimeChange()){
                timePicker.setHour(m_event.viewToHour());
                timePicker.setMinute(m_event.viewToMinute());
            }
        }

        // "Set" button listener
        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_event.fromTimeChange()){
                    m_event.setFromTime(
                            timePicker.getHour(),
                            timePicker.getMinute());
                }else{
                    if(m_event.toTimeChange()){
                        m_event.setToTime(
                                timePicker.getHour(),
                                timePicker.getMinute());
                    }
                }
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(SetTimeActivity.this, ViewEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "Cancel" button listener
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.cancelPressed();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(SetTimeActivity.this, ViewEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }
}
