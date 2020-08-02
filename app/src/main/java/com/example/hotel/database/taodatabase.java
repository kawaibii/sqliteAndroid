package com.example.hotel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hotel.MainActivity;

public class taodatabase extends SQLiteOpenHelper {
    public static final String QL_NHANVIEN = "QUANLYNHANVIEN";
    public static final int VERSION = 1;

    public static final String tb_Nhanvien = "nhanvien";
    public static final String _id_nhanvien = "_id";
    public static final String name_nhanvien = "name";

    public taodatabase(Context context) {
        super(context, QL_NHANVIEN, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ tb_Nhanvien + "("+
                _id_nhanvien +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                name_nhanvien +" TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tb_Nhanvien);
        onCreate(db);
    }

}
