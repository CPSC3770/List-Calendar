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
    List<CalendarEvent> m_eventList = new ArrayList<CalendarEvent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Receive event, catch is for fresh launch or cancel on next window
        Bundle bundle = getIntent().getExtras();
        try{
            String calendarEventAsJson = bundle.getString("event");
            CalendarEvent receivedEvent = CalendarEvent.fromJson(calendarEventAsJson);

            boolean found = false;

            for(int i = 0; i < this.m_eventList.size(); i++){
                if(receivedEvent.viewUUID() == this.m_eventList.get(i).viewUUID()){
                    found = true;
                    this.m_eventList.set(i, receivedEvent);
                    break;
                }
            }

            if(!found){
                m_eventList.add(receivedEvent);
                Collections.sort(m_eventList);
            }else{
                // Do nothing
            }
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

}
