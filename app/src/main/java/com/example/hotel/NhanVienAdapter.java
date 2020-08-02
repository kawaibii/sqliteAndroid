package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotel.models.Nhanvien;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {

    private Context context;
    private List<Nhanvien> list;


    public NhanVienAdapter(Context context, List<Nhanvien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_custom,parent,false);
            TextView tvName = convertView.findViewById(R.id.tennv);
            tvName.setText(list.get(position).getName());
            ImageView image = convertView.findViewById(R.id.image);
            image.setImageResource(R.drawable.ic_baseline_account);
        }

        return convertView;
    }
}
