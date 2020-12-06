package com.danieloloan.pbp_uts;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Mobil  {
    public String merkMobil;
    public String namaMobil;
    public String jenisFuel;
    public double jumlahSeat;
    public double harga;
    public String imgURL;


    public Mobil(String merkMobil, String namaMobil, String jenisFuel, double jumlahSeat, double harga, String imgURL) {
        this.merkMobil = merkMobil;
        this.namaMobil = namaMobil;
        this.jenisFuel = jenisFuel;
        this.jumlahSeat = jumlahSeat;
        this.harga = harga;
        this.imgURL = imgURL;
    }


    public String getMerkMobil() {return merkMobil;}

    public void setMerkMobil(String merkMobil) { this.merkMobil = merkMobil; }

    public String getNamaMobil() {return namaMobil;}

    public void setNamaMobil(String namaMobil) {this.namaMobil = namaMobil;}

    public String getJenisFuel() {return jenisFuel;}

    public void setJenisFuel(String jenisFuel) {this.jenisFuel = jenisFuel;}

    public double getJumlahSeat() {return jumlahSeat;}

    public void setJumlahSeat(double jumlahSeat) {this.jumlahSeat = jumlahSeat;}

    public double getHarga() {return harga;}

    public void setHarga(double harga) {this.harga = harga;}

    public String getImgURL() {return imgURL;}

    public void setImgURL(String imgURL) {this.imgURL = imgURL;}

    @BindingAdapter({"imgURL"})
    public static void loadImage(ImageView view, String imageURL) {
        Glide.with(view.getContext())
                .load(imageURL)
                .into(view);
    }
}
