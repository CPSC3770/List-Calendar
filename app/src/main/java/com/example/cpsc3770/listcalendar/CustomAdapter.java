package com.example.cpsc3770.listcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] tempList;
    private int[] tempColor;

    public CustomAdapter(Context context, String[] inputList, String[] inputTime, int[] inputColor) {
        super(context, R.layout.custom_list_adapter, inputList);
        tempList = inputTime;
        tempColor = inputColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_list_adapter, parent, false);

        // get references.
        String singleEvent = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.calendarListTitle);
        TextView itemTime = (TextView) customView.findViewById(R.id.calendarListTitle2);
        ImageView calendarImage = (ImageView) customView.findViewById(R.id.calendarListImage);


        itemText.setText(singleEvent);
        itemTime.setText(tempList[position]);
        calendarImage.setImageResource(tempColor[position]);

        return customView;
    }
}
