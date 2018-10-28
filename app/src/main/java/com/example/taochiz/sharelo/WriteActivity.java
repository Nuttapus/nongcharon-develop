package com.example.taochiz.sharelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        mDatabase =FirebaseDatabase.getInstance().getReference();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Topic = (EditText) findViewById(R.id.topic);
                EditText County = findViewById(R.id.county);
                EditText Detail = findViewById(R.id.detail);
                EditText StartDate = findViewById(R.id.startdate);
                EditText EndDate = findViewById(R.id.enddate);
                if (!Topic.getText().toString().equals("null")) {
                    if(!County.getText().toString().equals("null")) {
                        if (!Detail.getText().toString().equals("null")) {
                            if (!StartDate.getText().toString().equals("null")) {
                                if (!EndDate.getText().toString().equals("null")) {

                                    writeNewEvent("",Topic.getText().toString(),County.getText().toString(),Detail.getText().toString(),StartDate.getText().toString(),EndDate.getText().toString());

                                }else{
                                    Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                    }
                }
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


    public void writeNewEvent(String userID,String topic,String county,String detail,String startDay,String endDay){
        Event event = new Event(topic,county,detail,startDay,endDay);
        mDatabase.child("event").push().setValue(event);
    }
}
