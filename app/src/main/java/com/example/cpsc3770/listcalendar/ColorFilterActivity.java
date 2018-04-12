package com.example.cpsc3770.listcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.List;

import static com.example.cpsc3770.listcalendar.ViewCalendarActivity.KEY_COLOR_LIST;

public class ColorFilterActivity extends AppCompatActivity {

    private List<String> m_colors;

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

        //-- Get a reference to each check box
        final CheckBox redCheckBox    = findViewById(R.id.RedCheckBox);
        final CheckBox blueCheckBox   = findViewById(R.id.BlueCheckBox);
        final CheckBox greenCheckBox  = findViewById(R.id.GreenCheckBox);
        final CheckBox yellowCheckBox = findViewById(R.id.YellowCheckBox);
        final CheckBox purpleCheckBox = findViewById(R.id.PurpleCheckBox);
        final CheckBox pinkCheckBox   = findViewById(R.id.PinkCheckBox);
        final CheckBox greyCheckBox   = findViewById(R.id.GreyCheckBox);

        //-- if the color is in the list, set the check box to true
        for (int i = 0; i < m_colors.size(); i++) {
            if (m_colors.get(i).equals("Red")) {
                redCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Blue")) {
                blueCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Green")) {
                greenCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Yellow")) {
                yellowCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Purple")) {
                purpleCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Pink")) {
                pinkCheckBox.setChecked(true);
            } else if (m_colors.get(i).equals("Grey")) {
                greyCheckBox.setChecked(true);
            }
        }

        //-- for each check box,
        //--   if the check box is selected : remove the item from the list
        //--   else : add the item to the list
        redCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (redCheckBox.isChecked()) {
                    m_colors.remove("Red");
                } else {
                    m_colors.add("Red");
                }
            }
        });

        blueCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blueCheckBox.isChecked()) {
                    m_colors.remove("Blue");
                } else {
                    m_colors.add("Blue");
                }
            }
        });

        greenCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (greenCheckBox.isChecked()) {
                    m_colors.remove("Green");
                } else {
                    m_colors.add("Green");
                }
            }
        });

        yellowCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yellowCheckBox.isChecked()) {
                    m_colors.remove("Yellow");
                } else {
                    m_colors.add("Yellow");
                }
            }
        });

        purpleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (purpleCheckBox.isChecked()) {
                    m_colors.remove("Purple");
                } else {
                    m_colors.add("Purple");
                }
            }
        });

        pinkCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinkCheckBox.isChecked()) {
                    m_colors.remove("Pink");
                } else {
                    m_colors.add("Pink");
                }
            }
        });

        greyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (greyCheckBox.isChecked()) {
                    m_colors.remove("Grey");
                } else {
                    m_colors.add("Grey");
                }
            }
        });
    }

}
