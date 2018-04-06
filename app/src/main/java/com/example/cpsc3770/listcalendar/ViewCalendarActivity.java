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
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class ViewCalendarActivity extends AppCompatActivity {
    // Member variables
    // TODO i think we need save/restore code or this list will be empty every time we come back to this activity
    List<CalendarEvent> m_eventList;
    private static final String KEY_EVENT_LIST = "KEY_EVENT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Restore saved instance state if it exists
        if(savedInstanceState != null){
            String calendarEventListAsJson = savedInstanceState.getString(KEY_EVENT_LIST);
            CalendarEventList savedState = CalendarEventList.fromJson(calendarEventListAsJson);
            this.m_eventList = savedState.viewEventList();
        }else{
            this.m_eventList = new ArrayList<CalendarEvent>();
        }

        // Receive event, catch is for fresh launch or cancel on next window
        Bundle bundle = getIntent().getExtras();
        try{
            String calendarEventAsJson = bundle.getString("event");
            CalendarEvent receivedEvent = CalendarEvent.fromJson(calendarEventAsJson);

            boolean found = false;

            System.out.println(this.m_eventList.size());

            for(int i = 0; i < this.m_eventList.size(); i++){
                if(receivedEvent.viewUUID() == this.m_eventList.get(i).viewUUID()){
                    found = true;
                    this.m_eventList.set(i, receivedEvent);
                    break;
                }
            }

            if(!found){
                this.m_eventList.add(receivedEvent);
            }

            Collections.sort(this.m_eventList);
        } catch (java.lang.NullPointerException e){
            // Do nothing
        }

        // TODO : remove this temp
        String[] tempEvents = {"First", "Second", "Third", "Fourth", "Fifth",
                               "Sixth", "Seventh", "Eighth", "Ninth"};

        // TODO make this work with m_eventList instead
        ListAdapter customListAdapter = new CustomAdapter(this, tempEvents);
        ListView customListView = findViewById(R.id.CalendarEventList);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // TODO make temp event pull from list of real events based on position
                        CalendarEvent tempEvent = m_eventList.get(position);
                        String eventAsJson = tempEvent.toJson();
                        Intent intent = new Intent(ViewCalendarActivity.this, ViewEventActivity.class);
                        intent.putExtra("event", eventAsJson);
                        startActivity(intent);
                    }
                }
        );


        // "+" Floating Action Button listener (Behaves as "Add New Event"
        FloatingActionButton AddNewEventButton = findViewById(R.id.AddNewEvent);
        AddNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarEvent newEvent = new CalendarEvent();
                String eventAsJson = newEvent.toJson();
                Intent intent = new Intent(ViewCalendarActivity.this, ViewEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        String calendarEventListAsJson = savedInstanceState.getString(KEY_EVENT_LIST);
        CalendarEventList savedState = CalendarEventList.fromJson(calendarEventListAsJson);
        this.m_eventList = savedState.viewEventList();

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        CalendarEventList eventListToBeSaved = new CalendarEventList();
        eventListToBeSaved.setEventList(this.m_eventList);
        String eventListToBeSavedAsJson = eventListToBeSaved.toJson();
        outState.putString(KEY_EVENT_LIST, eventListToBeSavedAsJson);

        super.onSaveInstanceState(outState);
    }
}
