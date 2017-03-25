package com.wizdem.vinay.a5indr.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.wizdem.vinay.a5indr.Adapters.HistoryAdapter;
import com.wizdem.vinay.a5indr.R;
import com.wizdem.vinay.a5indr.Utils.Utils;
import com.wizdem.vinay.a5indr.models.SaveLocation;

import java.util.ArrayList;

import static com.wizdem.vinay.a5indr.Utils.Utils.saveLocations;

public class HistoryActivity extends AppCompatActivity {

    private TextView mViewHistory;
    private Firebase dbRef,ref;
    private boolean bipass;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_history);

        mViewHistory = (TextView) findViewById(R.id.history_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        String s = (String) Utils.uid;
        String s2 = "dX8rtORkKLfClFy1lZuqzqekknY2";
        try {
           // dbRef = new Firebase("https://indr-b46dd.firebaseio.com/Location"+"/");

            dbRef = new Firebase("https://indr-b46dd.firebaseio.com/Location"+"/");
            ref = dbRef.child(s2);
            function();
            ref = dbRef.child(s);
            function();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    void function(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        SaveLocation history = dataSnapshot1.getValue(SaveLocation.class);
                        //   Toast.makeText(getApplication(),history.message,Toast.LENGTH_SHORT).show();
                        if(!bipass){
                            saveLocations = new ArrayList<SaveLocation>();
                            /*Utils.lat=new ArrayList<Double>();
                            Utils.lon=new ArrayList<Double>();*/
                            bipass=true;
                        }
                        saveLocations.add(history);
                        /*Utils.lat.add(history.latitude);
                        Utils.lon.add(history.logitude);*/
                        mViewHistory.setText(history.message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    adapter = new HistoryAdapter(getApplicationContext(),saveLocations);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
