package com.danieloloan.pbp_uts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.danieloloan.pbp_uts.Activity.Booking;
import com.danieloloan.pbp_uts.Activity.Update_pesanan;

import java.util.List;

public class BookedRecyclerViewAdapter extends RecyclerView.Adapter<BookedRecyclerViewAdapter.BookedViewHolder> {

    private Context context;
    private List<Booked> bookedList;
    public BookedRecyclerViewAdapter(Context context, List<Booked> bookedList) {
        this.context = context;
        this.bookedList = bookedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view_pesananan, parent, false);
        return new BookedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedViewHolder holder, int position) {
        Booked booked = bookedList.get(position);
        holder.textView.setText(booked.getSimpan_nama());
        holder.textView2.setText(booked.getSimpan_address());
        holder.textView3.setText(booked.getSimpan_namaMobil());
        holder.textView4.setText(booked.getStringSimpan_lamaSewa()+ "\tHari");
        holder.textView5.setText("\tRp." + booked.getStringSimpan_harga());
    }

    @Override
    public int getItemCount() {
        return bookedList.size();
    }

    public class BookedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView,textView2,textView3,textView4,textView5;

        public BookedViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.Nama);
            textView2 = itemView.findViewById(R.id.Alamat);
            textView3 = itemView.findViewById(R.id.ModelMobil);
            textView4 = itemView.findViewById(R.id.LamaSewa);
            textView5 = itemView.findViewById(R.id.Harga);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Booked booked = bookedList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("pesanan", booked);
            data.putString("status", "edit");
            Update_pesanan update_pesanan = new Update_pesanan();
            update_pesanan.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_booking, update_pesanan)
                    .commit();
        }
    }

    public void loadFragment(Fragment fragment) {
//        AppCompatActivity activity = (AppCompatActivity) view.getContext();
//        FragmentManager fragmentManager = activity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.views_mahasiswa_fragment,fragment)
//                .addToBackStack(null)
//                .commit();

    }
}