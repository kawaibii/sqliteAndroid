package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotel.models.Nhanvien;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Nhanvien> {
    Context context;
    int resource;
    List<Nhanvien> objects;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Nhanvien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        TextView tenv = view.findViewById(R.id.tennv);
        tenv.setText(objects.get(position).getName().toString());
        return view;
    }
}
