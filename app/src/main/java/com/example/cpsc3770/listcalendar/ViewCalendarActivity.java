package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ViewCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Add new event button, does onClick transition to 'ViewEventActivity'
        FloatingActionButton AddNewEventButton = (FloatingActionButton) findViewById(R.id.AddNewEvent);
        AddNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewCalendarActivity.this, ViewEventActivity.class));
            }
        });
    }

}
