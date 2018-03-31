package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // "From Date" button listener
        Button SelectFromDate = (Button) findViewById(R.id.selectFromDate);
        SelectFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, SelectDateActivity.class));
            }
        });

        // "From Time" button listener
        Button SelectFromTime = (Button) findViewById(R.id.selectFromTime);
        SelectFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, SelectTimeActivity.class));
            }
        });

        // "To Date" button listener
        Button SelectToDate = (Button) findViewById(R.id.selectToDate);
        SelectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, SelectDateActivity.class));
            }
        });

        // "To Time" button listener
        Button SelectToTime = (Button) findViewById(R.id.selectToTime);
        SelectToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, SelectTimeActivity.class));
            }
        });

        // "Save" button listener
        Button SubmitEventChanges = (Button) findViewById(R.id.submitEventChanges);
        SubmitEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewEventActivity.this, ViewCalendarActivity.class));
            }
        });
    }
}
