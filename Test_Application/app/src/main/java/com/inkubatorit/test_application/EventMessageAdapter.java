package com.inkubatorit.test_application;

import android.content.Context;
import android.view.ViewGroup;
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
    private Integer[] mDataset = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    private LatLng[] location ={
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61),
            new LatLng(-6.89, 107.61)
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
        v.setLayoutParams(new GridView.LayoutParams(100, 100));
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
        holder.mImageView.setImageResource(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
