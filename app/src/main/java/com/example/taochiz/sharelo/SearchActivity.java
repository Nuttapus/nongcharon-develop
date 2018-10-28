package com.example.taochiz.sharelo;

import android.content.Intent;
import android.nfc.Tag;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        calendarView = (CalendarView) findViewById(R.id.calendarViewS);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView calendarView, int year, int month, int dayOfMonth) {
                 String date = (dayOfMonth + 1) + "/" + month + "/" + year;

            }
        });
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ref = database.child("event");

            final Button  search = findViewById(R.id.btnSearch);
            search.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ref.addValueEventListener(new ValueEventListener() {
                        EditText find = findViewById(R.id.editText4);

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Map map = (Map) dataSnapshot.getValue();
                            String value = find.getText().toString();
                            if(value.length() >= 1 ) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String county = ds.child("County").getValue(String.class);
                                    if(value.equals(county)) {
                                        startActivity(new Intent(SearchActivity.this,ViewActivity.class).putExtra("key",county));
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
