package com.example.hotel.models;

import java.io.Serializable;

public class Nhanvien  implements Serializable {
    private int _id;
    private String name;

    public int get_id() {
        return _id;
    }

    public Nhanvien set_id(int _id) {
        this._id = _id;
        return this;
    }


    public String getName() {
        return name;
    }

    public Nhanvien setName(String name) {
        this.name = name;
        return this;
    }
}
