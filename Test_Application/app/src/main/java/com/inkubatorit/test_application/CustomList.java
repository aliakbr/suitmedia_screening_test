package com.inkubatorit.test_application;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ali-pc on 4/2/2017.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final String[] date;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] name, String[] date, Integer[] imageId) {
        super(context, R.layout.list_item, name);
        this.context = context;
        this.name = name;
        this.date = date;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView user_name_view = (TextView) rowView.findViewById(R.id.name);
        TextView date_view = (TextView) rowView.findViewById(R.id.date);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        user_name_view.setText(name[position]);
        date_view.setText(date[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
