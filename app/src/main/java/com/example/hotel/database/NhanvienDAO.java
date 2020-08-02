package com.example.hotel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hotel.models.Nhanvien;

import java.util.ArrayList;
import java.util.List;

public class NhanvienDAO {
    SQLiteDatabase db;
    taodatabase taodb;
    String[] colum = {taodatabase._id_nhanvien, taodatabase.name_nhanvien};
    public NhanvienDAO(Context context){
        taodb = new taodatabase(context);
    }
    public void open(){
        db = taodb.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public boolean insert(Nhanvien nv){
        ContentValues content = new ContentValues();
        //content.put(taodb._id_nhanvien, nv.get_id());
        content.put(taodb.name_nhanvien, nv.getName());
        long check =  db.insert(taodb.tb_Nhanvien,null, content);
        if(check != 0){
            return true;
        }
        return false;
    }
    public List<Nhanvien> getall(){
        List<Nhanvien> listnv = new ArrayList<Nhanvien>();
// cach 1        Cursor cursor = db.query(taodb.tb_Nhanvien,colum,null,null,null,null,null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            int id = cursor.getInt(0);
//            String nv = cursor.getString(1);
//            Nhanvien nhanvien = new Nhanvien();
//            nhanvien.set_id(id);
//            nhanvien.setName(nv);
//            listnv.add(nhanvien);
//            cursor.moveToNext();
//        }
        // cach 2
        Cursor cursor = db.rawQuery("SELECT * FROM " + taodb.tb_Nhanvien, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String nv = cursor.getString(1);
            Nhanvien nhanvien = new Nhanvien();
            nhanvien.set_id(id);
            nhanvien.setName(nv);
            listnv.add(nhanvien);
            cursor.moveToNext();
        }
        return listnv;
    }
    // xóa nhân vien
    public boolean deleteNV(Nhanvien nhanvien){
      int ckeck =  db.delete(taodb.tb_Nhanvien, taodb._id_nhanvien + "=" + nhanvien.get_id(), null);
        if(ckeck != 0){
            return true;
        }
        return  false;
    }
    public boolean update(Nhanvien nhanvien, Nhanvien nhanvienbandau){
        ContentValues contentValues = new ContentValues();
        contentValues.put(taodb.name_nhanvien, nhanvien.getName());

       int ckeck = db.update(taodb.tb_Nhanvien, contentValues, taodb._id_nhanvien + " = " + nhanvienbandau.get_id(),null);
        if(ckeck != 0){
            return true;
        }
        return false;
    }
}
