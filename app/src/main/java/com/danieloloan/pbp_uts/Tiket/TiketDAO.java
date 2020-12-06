package com.danieloloan.pbp_uts.Tiket;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TiketDAO implements Serializable {
    private int id;
    private String nama;
    private String alamat;
    private String email;
    private String question;

    public TiketDAO(int id, String nama, String alamat, String email, String question){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.question = question;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
}
