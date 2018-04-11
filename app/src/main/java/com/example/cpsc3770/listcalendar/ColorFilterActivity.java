package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static com.example.cpsc3770.listcalendar.ViewCalendarActivity.KEY_COLOR_LIST;

public class ColorFilterActivity extends AppCompatActivity {

    private List<Integer> m_colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filter);

        Bundle bundle = getIntent().getExtras();
        String colorListAsJson = bundle.getString(KEY_COLOR_LIST);
        ColorList receivedColorList = ColorList.fromJson(colorListAsJson);
        this.m_colors = receivedColorList.viewColorList();
    }

    @Override
    protected void onStart(){
        super.onStart();

        // "Save" button listener
        Button applyFilterChanges = findViewById(R.id.ApplyFilterChanges);
        applyFilterChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorList colorListToBeSent = new ColorList();
                colorListToBeSent.setColorList(m_colors);
                Intent intent = new Intent(ColorFilterActivity.this, ViewCalendarActivity.class);
                String colorListAsJson = colorListToBeSent.toJson();
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra(KEY_COLOR_LIST, colorListAsJson);
                startActivity(intent);
            }
        });

        // "Cancel" button listener
        Button cancelFilterChanges = findViewById(R.id.CancelFilterChanges);
        cancelFilterChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColorFilterActivity.this, ViewCalendarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

}
