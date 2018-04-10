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

    private String[] time_list;
    private int[] color_list;

    public CustomAdapter(Context context, String[] inputList, String[] inputTime, int[] inputColor) {
        super(context, R.layout.custom_list_adapter, inputList);
        time_list = inputTime;
        color_list = inputColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_list_adapter, parent, false);

        // get references.
        LinearLayout displayA = (LinearLayout) customView.findViewById(R.id.displayA);
        LinearLayout displayB = (LinearLayout) customView.findViewById(R.id.displayB);

        String singleEvent = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.calendarListTitle);
        TextView itemTime = (TextView) customView.findViewById(R.id.calendarListTitle2);
        ImageView calendarImage = (ImageView) customView.findViewById(R.id.calendarListImage);
        TextView dateBox = (TextView) customView.findViewById(R.id.dateBox);

        if (time_list[position].equals("NULL")) {

            displayA.setVisibility(View.GONE);
            displayB.setVisibility(View.VISIBLE);

            dateBox.setText(singleEvent);

        } else {

            itemText.setText(singleEvent);
            itemTime.setText(time_list[position]);
            calendarImage.setImageResource(color_list[position]);

        }
        return customView;
    }
}
