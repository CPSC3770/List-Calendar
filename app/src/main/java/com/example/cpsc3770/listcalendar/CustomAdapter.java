package com.example.cpsc3770.listcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] inputList) {
        super(context, R.layout.custom_list_adapter, inputList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_list_adapter, parent, false);

        // get references.
        String singleEvent = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.calendarListTitle);
        ImageView calendarImage = (ImageView) customView.findViewById(R.id.calendarListImage);


        itemText.setText(singleEvent);
        calendarImage.setImageResource(R.drawable.placeholder);

        return customView;
    }
}
