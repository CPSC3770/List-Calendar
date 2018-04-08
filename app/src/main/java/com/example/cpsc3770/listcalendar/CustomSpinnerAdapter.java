package com.example.cpsc3770.listcalendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<ColorItem> {

    public CustomSpinnerAdapter(Context context, ArrayList<ColorItem> colorList) {
        super(context, 0, colorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_adapter, parent, false);
        }

        ImageView imageViewColor = convertView.findViewById(R.id.color_img);
        TextView textViewColor = convertView.findViewById(R.id.color_name);

        ColorItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewColor.setImageResource(currentItem.getColorImg());
            textViewColor.setText(currentItem.getColorName());
        }

        return convertView;
    }
}
