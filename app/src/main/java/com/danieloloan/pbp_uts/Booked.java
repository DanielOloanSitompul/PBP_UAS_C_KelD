package com.danieloloan.pbp_uts;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Booked implements Serializable {
    public int id;
    public String simpan_userID;
    public String simpan_nama;
    public String simpan_address;
    public String simpan_namaMobil;
    public int simpan_lamaSewa;
    public int simpan_harga;

    public Booked(int id, String simpan_userID, String simpan_nama, String simpan_address, String simpan_namaMobil, int simpan_lamaSewa, int simpan_harga){
        this.id = id;
        this.simpan_nama = simpan_nama;
        this.simpan_address = simpan_address;
        this.simpan_namaMobil = simpan_namaMobil;
        this.simpan_lamaSewa = simpan_lamaSewa;
        this.simpan_harga = simpan_harga;
    }

//    public int getId() {return id;}
//
//    public void setId(int id) {this.id=id;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getStringId() {return String.valueOf(id);}

    public void setStringId(String id) {this.id =Integer.parseInt(id);}

    public String getSimpan_userID() {return simpan_userID;}

    public void setSimpan_userID(String simpan_userID) {this.simpan_userID = simpan_userID;}

    public String getSimpan_nama() {return simpan_nama;}

    public void setSimpan_nama(String simpan_nama) {this.simpan_nama = simpan_nama;}

    public String getSimpan_address() {return simpan_address;}

    public void setSimpan_address(String simpan_address) {this.simpan_address = simpan_address;}

    public String getSimpan_namaMobil() {return simpan_namaMobil;}

    public void setSimpan_namaMobil(String simpan_namaMobil) {this.simpan_namaMobil = simpan_namaMobil;}

    public int getSimpan_lamaSewa() {return simpan_lamaSewa;}

    public void setSimpan_lamaSewa(int simpan_lamaSewa) {this.simpan_lamaSewa = simpan_lamaSewa;}

    public String getStringSimpan_lamaSewa() {return String.valueOf(simpan_lamaSewa);}

    public void setStringSimpan_lamaSewa(String simpan_lamaSewa) {this.simpan_lamaSewa =Integer.parseInt(simpan_lamaSewa);}

    public int getSimpan_harga() {return simpan_harga;}

    public void setSimpan_harga(int simpan_harga) {this.simpan_harga = simpan_harga;}

    public String getStringSimpan_harga() {return String.valueOf(simpan_harga);}

    public void setStringSimpan_harga(String simpan_harga) {this.simpan_harga =Integer.parseInt(simpan_harga);}

}