package com.danieloloan.pbp_uts.Tiket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danieloloan.pbp_uts.Activity.Update_pesanan;
import com.danieloloan.pbp_uts.Activity.Update_tiket;
import com.danieloloan.pbp_uts.Booked;
import com.danieloloan.pbp_uts.R;

import java.util.ArrayList;
import java.util.List;

public class TiketRecyclerAdapter extends RecyclerView.Adapter<TiketRecyclerAdapter.TiketViewHolder> {
    private List<TiketDAO> tiketList;
    private List<TiketDAO> filteredDataList;
    private Context context;
    public TiketRecyclerAdapter(Context context, List<TiketDAO> tiketList){
        this.context = context;
        this.tiketList = tiketList;
    }
    @NonNull
    public TiketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_adapter_tiket, parent, false);
        return new TiketViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TiketRecyclerAdapter.TiketViewHolder holder, int position) {
        TiketDAO tiket = tiketList.get(position);
        holder.twNama.setText(tiket.getNama());
        holder.twAddress.setText(tiket.getAlamat());
        holder.twEmail.setText(tiket.getEmail());
        holder.twQuestion.setText(tiket.getQuestion());
        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                TiketDAO tiket = tiketList.get(position);
                Bundle data = new Bundle();
                data.putSerializable("tiket", tiket);
                Update_tiket update_tiket = new Update_tiket();
                update_tiket.setArguments(data);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_ticket, update_tiket)
                        .commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        return tiketList.size();
    }
    public class TiketViewHolder extends RecyclerView.ViewHolder{
        private TextView twNama, twAddress, twEmail, twQuestion;
        private LinearLayout mParent;
        public TiketViewHolder(@NonNull View itemView){
            super(itemView);
            twNama = itemView.findViewById(R.id.twNama);
            twAddress= itemView.findViewById(R.id.twAddress);
            twEmail= itemView.findViewById(R.id.twEmail);
            twQuestion= itemView.findViewById(R.id.twQuestion);
            mParent = itemView.findViewById(R.id.linearLyout);
        }
    }
}
