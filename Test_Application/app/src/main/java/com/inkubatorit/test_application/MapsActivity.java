package com.inkubatorit.test_application;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView mRecyclerView;
    private EventMessageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView event_title;
    private LatLng current_location;
    CameraUpdate zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.map_recycler_view);
        event_title = (TextView)findViewById(R.id.event_name);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EventMessageAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
//        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        event_title.setText(mAdapter.getEventName());
        current_location = mAdapter.getLocation();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng[] all_marker = mAdapter.getAllLocation();
        for (int i = 0; i< all_marker.length; i++){
            mMap.addMarker(new MarkerOptions().position(all_marker[i]).title("Current Location"));
        }
        zoom=CameraUpdateFactory.zoomTo(5);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current_location));
        mMap.animateCamera(zoom);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > RecyclerView.LayoutParams.MATCH_PARENT) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    System.out.println(firstVisiblePosition);
                    event_title.setText(mAdapter.getEventNameFromPosition(firstVisiblePosition));
                    current_location = mAdapter.getLocationFromPosition(firstVisiblePosition);
                    mMap.addMarker(new MarkerOptions().position(current_location).title("Current Location"));
                    zoom=CameraUpdateFactory.zoomTo(5);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current_location));
                    mMap.animateCamera(zoom);
                }
            }
        });
    }

    public void goBack(View view){
        finish();
    }
}
