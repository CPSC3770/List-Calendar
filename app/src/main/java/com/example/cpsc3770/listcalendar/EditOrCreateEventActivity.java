package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditOrCreateEventActivity extends AppCompatActivity {
    // Member variables
    private CalendarEvent m_event;
    private boolean m_originIsView = false;
    private ArrayList<ColorItem> m_ColorList;
    private CustomSpinnerAdapter m_Adapter;

    private boolean allRequiredFieldsSet(){
        if(!this.m_event.hasTitle()){
            Toast.makeText(getApplicationContext(),"Title Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.m_event.hasLocation()){
            Toast.makeText(getApplicationContext(),"Location Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.m_event.hasFromDate()){
            Toast.makeText(getApplicationContext(),"From Date Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.m_event.hasFromTime()){
            Toast.makeText(getApplicationContext(),"From Time Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.m_event.hasToDate()){
            Toast.makeText(getApplicationContext(),"To Date Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.m_event.hasToTime()){
            Toast.makeText(getApplicationContext(),"To Time Required",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_create_event);

        // Receive event object from previous activity
        Bundle bundle = getIntent().getExtras();
        String calendarEventAsJson = bundle.getString("event");
        this.m_event = CalendarEvent.fromJson(calendarEventAsJson);

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
        EditText titleField = findViewById(R.id.eventTitle);
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
        EditText locationField = findViewById(R.id.eventLocation);
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
        Button selectFromDate = findViewById(R.id.selectFromDate);
        selectFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareFromDateChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(EditOrCreateEventActivity.this, SetDateActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "From Time" button listener
        Button selectFromTime = findViewById(R.id.selectFromTime);
        selectFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareFromTimeChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(EditOrCreateEventActivity.this, SetTimeActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "To Date" button listener
        Button selectToDate = findViewById(R.id.selectToDate);
        selectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareToDateChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(EditOrCreateEventActivity.this, SetDateActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "To Time" button listener
        Button selectToTime = findViewById(R.id.selectToTime);
        selectToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_event.prepareToTimeChange();
                String eventAsJson = m_event.toJson();
                Intent intent = new Intent(EditOrCreateEventActivity.this, SetTimeActivity.class);
                intent.putExtra("event", eventAsJson);
                startActivity(intent);
            }
        });

        // "Save" button listener
        Button submitEventChanges = findViewById(R.id.submitEventChanges);
        submitEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allRequiredFieldsSet()) {
                    String eventAsJson = m_event.toJson();
                    Intent intent = new Intent(EditOrCreateEventActivity.this, ViewCalendarActivity.class);
                    intent.putExtra("event", eventAsJson);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });

        // "Cancel" button listener
        Button cancelEventChanges = findViewById(R.id.cancelEventChanges);
        cancelEventChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditOrCreateEventActivity.this, ViewCalendarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        //-- color spinner
        initList();

        final ImageView imageColor = findViewById(R.id.color_view);
        imageColor.setImageResource(m_event.viewColor().getColorImg());

        Spinner spinnerColor = findViewById(R.id.color_spinner);
        m_Adapter = new CustomSpinnerAdapter(this, m_ColorList);
        spinnerColor.setAdapter(m_Adapter);
        spinnerColor.setSelection(getColorIndex(m_event.viewColor().getColorImg()));

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ColorItem clickedItem = (ColorItem) adapterView.getItemAtPosition(i);
                m_event.setColor(clickedItem);
                imageColor.setImageResource(clickedItem.getColorImg());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                imageColor.setImageResource(m_event.viewColor().getColorImg());
            }
        });


    }


    private void initList() {
        m_ColorList = new ArrayList<>();
        m_ColorList.add(new ColorItem("Red",    R.drawable.red));
        m_ColorList.add(new ColorItem("Blue",   R.drawable.blue));
        m_ColorList.add(new ColorItem("Green",  R.drawable.green));
        m_ColorList.add(new ColorItem("Yellow", R.drawable.yellow));
        m_ColorList.add(new ColorItem("Purple", R.drawable.purple));
        m_ColorList.add(new ColorItem("Pink",   R.drawable.pink));
        m_ColorList.add(new ColorItem("Grey",   R.drawable.grey));
    }

    private int getColorIndex(int currentColor) {
        for (int i = 0; i < m_ColorList.size(); i++) {
            if (m_ColorList.get(i).getColorImg() == currentColor) {
                return i;
            }
        }
        return 0;
    }
}
