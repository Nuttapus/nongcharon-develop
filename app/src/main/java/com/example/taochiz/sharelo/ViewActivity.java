package com.example.taochiz.sharelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Map;

import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewActivity extends AppCompatActivity {
    static ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        String c = intent.getStringExtra("key");
        TextView txt = (TextView)findViewById(R.id.textView);
        txt.setText(c);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ref = database.child("event");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                Intent intent = getIntent();
                String c = intent.getStringExtra("key");
                String value = c;
                if(value.length() >= 1 ) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String county = ds.child("County").getValue(String.class);
                        if(value.equals(county)) {
                            String topic = ds.child("Topic").getValue(String.class);
                            String detail = ds.child("Detail").getValue(String.class);
                            String endday = ds.child("EndDay").getValue(String.class);
                            String startday = ds.child("StartDay").getValue(String.class);
                            TextView txtTop = (TextView)findViewById(R.id.txtTop);
                            txtTop.setText(topic);
                            TextView txtTimeS = (TextView)findViewById(R.id.txtTimeS);
                            txtTimeS.setText(startday);
                            TextView txtTimeF = (TextView)findViewById(R.id.txtTimeF);
                            txtTimeF.setText(endday);

                        }
                    }
                }else{
                    System.out.println("hahahahahahaha");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
