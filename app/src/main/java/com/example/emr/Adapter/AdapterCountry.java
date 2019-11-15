package com.example.emr.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emr.CountryItem;
import com.example.emr.R;

import java.util.ArrayList;

public class AdapterCountry extends ArrayAdapter<CountryItem> {


    public AdapterCountry(Context context, ArrayList<CountryItem> countryList) {
        super(context, 0, countryList);
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
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_custom_layout, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.imageView_flag);
        TextView textViewName = convertView.findViewById(R.id.textView_Country);

        CountryItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getCountrFlag());
            textViewName.setText(currentItem.getCountryName());
        }

        return convertView;
    }

}
