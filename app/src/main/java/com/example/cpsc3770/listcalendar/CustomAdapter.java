package com.example.cpsc3770.listcalendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] desc_list;
    private String[] time_list;
    private int[] color_list;
    private int n;

    public CustomAdapter(Context context, String[] inputList, String[] inputTime, int[] inputColor, int total) {
        super(context, R.layout.custom_list_adapter, inputList);
        desc_list = inputList;
        time_list = inputTime;
        color_list = inputColor;
        n = total;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_list_adapter, parent, false);

        // get references.
        LinearLayout displayA = (LinearLayout) customView.findViewById(R.id.displayA);
        LinearLayout displayB = (LinearLayout) customView.findViewById(R.id.displayB);

        if (position < n) {

            //String singleEvent = getItem(position);
            TextView itemText = (TextView) customView.findViewById(R.id.calendarListTitle);
            TextView itemTime = (TextView) customView.findViewById(R.id.calendarListTitle2);
            ImageView calendarImage = (ImageView) customView.findViewById(R.id.calendarListImage);
            TextView dateBox = (TextView) customView.findViewById(R.id.dateBox);

            if (color_list[position] == -1) {

                displayA.setVisibility(View.GONE);
                displayB.setVisibility(View.VISIBLE);

                //dateBox.setText(singleEvent);
                dateBox.setText(desc_list[position]);

            } else {

                displayA.setVisibility(View.VISIBLE);
                displayB.setVisibility(View.GONE);

                itemText.setText(desc_list[position]);
                itemTime.setText(time_list[position]);
                calendarImage.setImageResource(color_list[position]);
            }

            return customView;
        }

        //-- if we are out of range, display nothing
        displayA.setVisibility(View.GONE);
        displayB.setVisibility(View.GONE);
        return customView;
    }

}
