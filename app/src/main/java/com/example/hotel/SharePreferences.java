package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SharePreferences extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences sharedPreferences;
    private EditText edhoten, edemail;
    private Button btnluu, btnlaydulieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        edhoten = findViewById(R.id.hoten);
        edemail = findViewById(R.id.email);
        btnluu = findViewById(R.id.btnluu);
        btnlaydulieu = findViewById(R.id.btnlaydulieu);
        sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
        btnluu.setOnClickListener(this);
        btnlaydulieu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnluu :
                //SharedPreferences.Editor editor = sharePreferences.edit();
                //SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String hoten = edhoten.getText().toString();
                String email = edemail.getText().toString();
                editor.putString("hoten", hoten);
                editor.putString("email", email);
                editor.commit();
                break;
            case R.id.btnlaydulieu:
                edhoten.setText(sharedPreferences.getString("hoten",""));
                edemail.setText(sharedPreferences.getString("email",""));
                break;
        }
    }


}