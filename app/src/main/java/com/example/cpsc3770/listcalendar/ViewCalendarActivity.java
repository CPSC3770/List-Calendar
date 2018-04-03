package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewCalendarActivity extends AppCompatActivity {
    // Member variables
    private CalendarEvent m_event;
    // #TODO <event list here>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Receive event, catch is for fresh launch
        Bundle bundle = getIntent().getExtras();
        try{
            String calendarEventAsJson = bundle.getString("event");
            // #TODO add this to some kind of list, compare UUID of every element in the list first to determine if it was an edit or not
            m_event = CalendarEvent.fromJson(calendarEventAsJson);
        } catch (java.lang.NullPointerException e){
            m_event = new CalendarEvent();
        }

        // TODO : remove this temp
        String[] tempEvents = {"First", "Second", "Third", "Fourth", "Fifth",
                               "Sixth", "Seventh", "Eighth", "Ninth"};

        ListAdapter customListAdapter = new CustomAdapter(this, tempEvents);
        ListView customListView = (ListView) findViewById(R.id.CalendarEventList);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // TODO : make new window showing full calendar event w/ edit option
                        String temp = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ViewCalendarActivity.this, temp, Toast.LENGTH_LONG).show();
                    }
                }
        );


        // "+" Floating Action Button listener (Behaves as "Add New Event"
        FloatingActionButton AddNewEventButton = findViewById(R.id.AddNewEvent);
        AddNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(ViewCalendarActivity.this, ViewEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }

}
