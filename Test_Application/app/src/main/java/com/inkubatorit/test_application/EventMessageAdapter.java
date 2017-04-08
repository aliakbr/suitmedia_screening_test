package com.inkubatorit.test_application;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Ali-pc on 4/6/2017.
 */

public class EventMessageAdapter extends RecyclerView.Adapter<EventMessageAdapter.ViewHolder> {
    private Context mContext;
    // references to our images
    private Integer[] mImage = {
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4,
            R.drawable.event5,
            R.drawable.event6
    };

    private LatLng[] location ={
            new LatLng(-6.573246, 108.178165),
            new LatLng(-9.285369, 124.859070),
            new LatLng(0.081754, 114.879907),
            new LatLng(-5.087887, 119.837130),
            new LatLng(-3.073054, 129.362050),
            new LatLng(-1.801146, 137.647553),
            new LatLng(4.528076, 97.058377)
    };

    String[] event_name = {
            "Event_1",
            "Event_2",
            "Event_3",
            "Event_4",
            "Event_5",
            "Event_6",
    };


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public ViewHolder(ImageView v) {
            super(v);
            mImageView = v;
        }
    }

    int current_position;
    // Provide a suitable constructor (depends on the kind of dataset)
    public EventMessageAdapter(Context context) {
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ImageView v = new ImageView(mContext);
        // set the view's size, margins, paddings and layout parameters
        v.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
        v.setPadding(8, 8, 8, 8);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImageView.setImageResource(mImage[position]);
        current_position = position;
    }

    public String getEventName(){
        return event_name[current_position];
    }

    public LatLng getLocation(){
        return location[current_position];
    }

    public String getEventNameFromPosition(int position){
        return event_name[position];
    }

    public LatLng getLocationFromPosition(int position){
        return location[position];
    }

    public LatLng[] getAllLocation(){
        return location;
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mImage.length;
    }
}
