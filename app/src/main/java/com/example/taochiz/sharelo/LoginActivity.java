package com.example.taochiz.sharelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_login);

            Button bR = (Button)findViewById(R.id.btnRegister);
            bR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        final Button  login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    EditText email = findViewById(R.id.txtUsername);
                    EditText p = findViewById(R.id.txtPassword);

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map map = (Map) dataSnapshot.getValue();
                        String value = String.valueOf(map.get(email.getText().toString()));
                        if(value.equals(p.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "ยินตีต้อนรับเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, SelectActivity.class));

                        }else{
                            Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
