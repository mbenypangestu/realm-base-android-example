package com.mygetzu.mygetzuresto.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ResepModel extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String nama_resep;
    private String deskripsi;
    private String resep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_resep() {
        return nama_resep;
    }

    public void setNama_resep(String nama_resep) {
        this.nama_resep = nama_resep;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getResep() {
        return resep;
    }

    public void setResep(String resep) {
        this.resep = resep;
    }
}
