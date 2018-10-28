package com.example.taochiz.sharelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        Button bR = (Button)findViewById(R.id.btnBackRegis);
        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText id = (EditText) findViewById(R.id.editText);
                EditText pass = findViewById(R.id.editText2);
                EditText conpass = findViewById(R.id.editText3);
                if (!id.getText().toString().equals("null")) {
                    if(pass.getText().toString().length() >= 4) {
                        if (pass.getText().toString().length() >= 6) {
                            if (conpass.getText().toString().equals(pass.getText().toString())) {
                                Map<String, Object> login = new HashMap<>();
                                login.put(id.getText().toString(), pass.getText().toString());
                                myRef.updateChildren(login);

                                //startActivity(new Intent(Register.this, homepage.class));
                            }
                            Toast.makeText(getApplicationContext(), "ยินดีต้อนรับเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่านมากกว่าหรือเท่ากับ 6 ตัวอัษร", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "กรุณากรอกชื่อผู้ใช้ให้มากกว่าหรือเท่ากับ 4 ตัวอักษร", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}
