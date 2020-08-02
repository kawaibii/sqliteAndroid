package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hotel.database.NhanvienDAO;
import com.example.hotel.database.taodatabase;
import com.example.hotel.models.Nhanvien;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button insert,edit,delete,contentprovider;
    private EditText tennv;
    private NhanvienDAO nhanviendao;
    private ListView listnv;
    private NhanVienAdapter nhanVienAdapter;
    private List<Nhanvien> Listnvs;
    private  CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        insert = findViewById(R.id.insert);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        contentprovider = findViewById(R.id.ctprovider);
        tennv = findViewById(R.id.name);
        listnv = findViewById(R.id.list);
        //Stetho.initializeWithDefaults(this);
        nhanviendao = new NhanvienDAO(this);
        nhanviendao.open();
        Listnvs = new ArrayList<Nhanvien>();
        Listnvs = nhanviendao.getall();
        Log.d("dulieu",Listnvs.toString());
        for (Nhanvien nhanvien  : Listnvs){
            Log.d("tag", nhanvien.getName());
        }
        nhanVienAdapter = new NhanVienAdapter(this,Listnvs);
       // adapter = new CustomAdapter(this, R.layout.layout_custom,Listnvs);
        listnv.setAdapter(nhanVienAdapter);

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        contentprovider.setOnClickListener(this);
        registerForContextMenu(listnv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnxoa :
                Toast.makeText(MainActivity.this, item.getTitle(),Toast.LENGTH_SHORT).show();
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Nhanvien nhanvien = Listnvs.get(menuInfo.position);
                nhanviendao.deleteNV(nhanvien);
                Listnvs.remove(menuInfo.position);
                nhanVienAdapter.notifyDataSetChanged();

                break;
            case R.id.mnsua :
                Toast.makeText(MainActivity.this, item.getTitle(),Toast.LENGTH_SHORT).show();
                AdapterView.AdapterContextMenuInfo menuInfosua = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Intent intent = new Intent(MainActivity.this, Suanhanvien.class);
                Nhanvien nhanvien2 = Listnvs.get(menuInfosua.position);
                intent.putExtra("nhanvien2", nhanvien2);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                Nhanvien nv = new Nhanvien();
                String ten = tennv.getText().toString();
                nv.setName( ten );
                Listnvs.add(nv);
                boolean ckeck = nhanviendao.insert(nv);
                if(ckeck == true){
                    Toast.makeText(MainActivity.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.delete:
                Nhanvien nhanvien = Listnvs.get(0);
                Log.d("xoanhanvien", nhanvien.getName());
                nhanviendao.deleteNV(nhanvien);
                Listnvs.remove(nhanvien);
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case R.id.ctprovider :
                Intent intent = new Intent(MainActivity.this, SharePreferences.class);
                startActivity(intent);
        }
    }
}