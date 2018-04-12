package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class ViewCalendarActivity extends AppCompatActivity {
    // Member variables
    List<CalendarEvent> m_eventList;
    List<Integer> m_colors;
    private String[] Descrip;
    private String[] Times;
    private int[] Colors;
    private int[] Indexes;

    public static final String KEY_EVENT_LIST = "KEY_EVENT_LIST";
    public static final String KEY_COLOR_LIST = "KEY_COLOR_LIST";
    private static final int NUM_COLORS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Restore saved instance state if it exists
        if(savedInstanceState != null){
            String calendarEventListAsJson = savedInstanceState.getString(KEY_EVENT_LIST);
            CalendarEventList savedCalendarEventList = CalendarEventList.fromJson(calendarEventListAsJson);
            this.m_eventList = savedCalendarEventList.viewEventList();
            Collections.sort(this.m_eventList);
        }else{
            this.m_eventList = new ArrayList<>();
        }

        //-- Initialize the colors to display
        this.m_colors = new ArrayList<>();
        for(int i = 0; i < NUM_COLORS; i++){
            this.m_colors.add(i);
        }
    }

    @Override
    public void onStart(){
        super.onStart();

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
                this.m_eventList.add(receivedEvent);
            }

            Collections.sort(this.m_eventList);

        } catch (java.lang.NullPointerException e){
            // Do nothing
        }

        try{
            String colorListAsJson = bundle.getString(KEY_COLOR_LIST);
            ColorList receivedColorList = ColorList.fromJson(colorListAsJson);
            this.m_colors = receivedColorList.viewColorList();
        } catch (java.lang.NullPointerException e){
            // Do nothing
        }

        if(this.m_eventList.size() != 0){

            //-- sort the events
            Collections.sort(this.m_eventList);

            //-- if there are events, populate them and make them intractable
            Descrip = new String[this.m_eventList.size()*2];
            Times   = new String[this.m_eventList.size()*2];
            Colors  = new int[this.m_eventList.size()*2];
            Indexes = new int[this.m_eventList.size()*2];

            String LastDate = " ";
            int ittr = 0;

            for(int i = 0; i < this.m_eventList.size(); i++){

                if (!LastDate.equals(this.m_eventList.get(i).viewFromDateAsString())) {

                    //-- Set expected values for the header
                    Descrip[ittr] = this.m_eventList.get(i).getDateDisplay();
                    Times[ittr] = "NULL";
                    Colors[ittr] = -1;
                    Indexes[ittr] = -1; // make sure we know this index was a header

                    //-- Grab the last date of a header
                    LastDate = this.m_eventList.get(i).viewFromDateAsString();

                    //-- Increase iterator to next position
                    ittr++;
                }

                //-- set information about this position
                Descrip[ittr] = this.m_eventList.get(i).viewTitle();
                Times[ittr]   = this.m_eventList.get(i).viewTimeAsString();
                Colors[ittr]  = this.m_eventList.get(i).viewColor().getColorImg();
                Indexes[ittr] = i; // make sure we can find this index of m_eventList later

                ittr++;
            }

            //Toast.makeText(this, Integer.toString(ittr), Toast.LENGTH_SHORT).show();

            ListAdapter customListAdapter = new CustomAdapter(this, Descrip, Times, Colors, ittr);
            ListView customListView = findViewById(R.id.CalendarEventList);
            customListView.setAdapter(customListAdapter);


            customListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            //-- check if this intent is a header
                            if (Indexes[position] != -1) {
                                CalendarEvent tempEvent = m_eventList.get(Indexes[position]);
                                String eventAsJson = tempEvent.toJson();
                                Intent intent = new Intent(ViewCalendarActivity.this, ViewEventActivity.class);
                                intent.putExtra("event", eventAsJson);
                                startActivity(intent);
                            }

                        }
                    }
            );

        } else {
            //-- display a message when empty
            ListView customListView = findViewById(R.id.CalendarEventList);
            customListView.setEmptyView(findViewById(R.id.emptyList));
        }

        // "+" Floating Action Button listener (Behaves as "Add New Event"
        FloatingActionButton AddNewEventButton = findViewById(R.id.AddNewEvent);
        AddNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarEvent newEvent = new CalendarEvent();
                String eventAsJson = newEvent.toJson();
                Intent intent = new Intent(ViewCalendarActivity.this, EditOrCreateEventActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "Pencil" Floating Action Button listener (Behaves as "Color Filter"
        FloatingActionButton ColorFilterButton = findViewById(R.id.ChangeColorFilter);
        ColorFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_eventList.size() == 0){
                    Toast.makeText(getApplicationContext(),"At least one event must be added before filters can be applied",Toast.LENGTH_SHORT).show();
                }else{
                    ColorList colorListToBeSent = new ColorList();
                    colorListToBeSent.setColorList(m_colors);
                    Intent intent = new Intent(ViewCalendarActivity.this, ColorFilterActivity.class);
                    String colorListAsJson = colorListToBeSent.toJson();
                    intent.putExtra(KEY_COLOR_LIST, colorListAsJson);
                    startActivity(intent);
                }
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
