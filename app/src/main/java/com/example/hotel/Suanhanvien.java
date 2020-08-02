package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotel.database.NhanvienDAO;
import com.example.hotel.models.Nhanvien;

public class Suanhanvien extends AppCompatActivity{
   private Button btnedit, btnexit;
   private EditText etname;
   private NhanvienDAO nhanviendao;
   private Nhanvien nhanviensua; // nhan vien can sua
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suanhanvien);
        btnedit = findViewById(R.id.btnedit);
        btnexit = findViewById(R.id.exit);
        etname = findViewById(R.id.editname);
        nhanviendao = new NhanvienDAO(this);
        nhanviendao.open();
        nhanviensua = (Nhanvien) getIntent().getSerializableExtra("nhanvien2");
        Log.d("tennhanvien", nhanviensua.getName());
        etname.setText(nhanviensua.getName());
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                Nhanvien nhanvienmoi = new Nhanvien();
                nhanvienmoi.setName(name);
                Log.d("ten nhan vien", nhanvienmoi.getName());
                boolean ckeck = nhanviendao.update(nhanvienmoi, nhanviensua);
                if(ckeck == true){
                    Toast.makeText(Suanhanvien.this, "sua thanh cong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Suanhanvien.this, "sua that bai", Toast.LENGTH_SHORT).show();
                }

            }

        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}