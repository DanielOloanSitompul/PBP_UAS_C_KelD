package com.danieloloan.pbp_uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.danieloloan.pbp_uts.databinding.AdapterRecyclerViewBinding;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Mobil> result = new ArrayList<>();

    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter(Context context, List<Mobil> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterRecyclerViewBinding itemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.adapter_recycler_view, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Mobil mbl = result.get(position);
        holder.adapterRecyclerViewBinding.setMbl(mbl);;
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterRecyclerViewBinding adapterRecyclerViewBinding;
        private ImageView foto_profil;
        private CardView parent;

        public  MyViewHolder(AdapterRecyclerViewBinding adapterRecyclerViewBinding) {
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;
        }

        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }

    }
}
