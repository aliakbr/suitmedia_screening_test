package com.inkubatorit.test_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class EventListActivity extends AppCompatActivity {
    ListView list;

    /* Dummmy data */
    String[] event_name = {
            "Event_1",
            "Event_2",
            "Event_3",
            "Event_4",
            "Event_5",
            "Event_6",
    } ;

    String[] date ={
            "17 Agustus 2017",
            "7 Agustus 2017",
            "6 Agustus 2016",
            "4 Agustus 2015",
            "9 Agustus 2017",
            "11 Agustus 2017",
    };
    Integer[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,

    };

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = DataHolder.getInstance().getData(0);
        setContentView(R.layout.activity_event_list);
        /* Creating List */
        CustomList adapter = new CustomList(EventListActivity.this, event_name, date, imageId);
        list = (ListView)findViewById(R.id.event_list);
        list.setAdapter(adapter);

        /* item OnClick */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("event_name", event_name[position]);
                setResult(EventListActivity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
