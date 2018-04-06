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

public class ViewEventActivity extends AppCompatActivity {
    // Member variables
    private CalendarEvent m_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Receive event object from previous activity
        Bundle bundle = getIntent().getExtras();
        try{
            String calendarEventAsJson = bundle.getString("event");
            this.m_event = CalendarEvent.fromJson(calendarEventAsJson);
        }catch(java.lang.NullPointerException e){
        // Do nothing
    }
        // Set views
        TextView fromDateView = findViewById(R.id.viewFromDate);
        fromDateView.setText(this.m_event.viewFromDateAsString());

        TextView fromTimeView = findViewById(R.id.viewFromTime);
        fromTimeView.setText(this.m_event.viewFromTimeAsString());

        TextView toDateView = findViewById(R.id.viewToDate);
        toDateView.setText(this.m_event.viewToDateAsString());

        TextView toTimeView = findViewById(R.id.viewToTime);
        toTimeView.setText(this.m_event.viewToTimeAsString());

        // "Title" edit text listener
        TextView titleField = findViewById(R.id.eventTitle);
        titleField.setText(m_event.viewTitle());

        // "Location" edit text listener
        TextView locationField = findViewById(R.id.eventLocation);
        locationField.setText(m_event.viewLocation());

        // "Return" button listener
        Button submitEventChanges = findViewById(R.id.submitEventChanges);
        submitEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, ViewCalendarActivity.class);
                intent.putExtra("event", eventAsJson);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // "Edit" button listener
        Button cancelEventChanges = findViewById(R.id.cancelEventChanges);
        cancelEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewEventActivity.this, EditOrCreateEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }
}
