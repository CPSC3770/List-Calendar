package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

public class SetDateActivity extends AppCompatActivity {
    // Member variables
    private CalendarEvent m_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);

        // Receive event object from previous activity
        Bundle bundle = getIntent().getExtras();
        String calendarEventAsJson = bundle.getString("event");
        m_event = CalendarEvent.fromJson(calendarEventAsJson);

        final DatePicker datePicker = findViewById(R.id.datePicker);

        Calendar calendar = Calendar.getInstance();

        // Set hand positions to current values
        if(m_event.fromDateChange()){
            if(m_event.hasFromDate()){
                datePicker.updateDate(
                        m_event.viewFromYear(),
                        m_event.viewFromMonth(),
                        m_event.viewFromDay());
            }else{
                datePicker.updateDate(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
            }

        }else{
            if(m_event.toDateChange()){
                if(m_event.hasToDate()) {
                    datePicker.updateDate(
                            m_event.viewToYear(),
                            m_event.viewToMonth(),
                            m_event.viewToDay());
                }else{
                    datePicker.updateDate(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                }
            }
        }

        // "Set" button listener
        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_event.fromDateChange()){
                    m_event.setFromDate(
                            datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth());
                }else{
                    if(m_event.toDateChange()){
                        m_event.setToDate(
                                datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth());
                    }
                }
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(SetDateActivity.this, ViewEventActivity.class);
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
                Intent intent = new Intent(SetDateActivity.this, ViewEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }
}
