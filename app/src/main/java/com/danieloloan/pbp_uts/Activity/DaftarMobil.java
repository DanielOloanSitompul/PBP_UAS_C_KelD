package com.danieloloan.pbp_uts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.danieloloan.pbp_uts.ListMobil;
import com.danieloloan.pbp_uts.Mobil;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.RecyclerViewAdapter;
import com.danieloloan.pbp_uts.databinding.ActivityDaftarMobilBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DaftarMobil extends AppCompatActivity {

    private ArrayList<Mobil> listMobil;
    private RecyclerViewAdapter adapter;
    private ActivityDaftarMobilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil);
        //databind
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daftar_mobil);
        binding.recyclerViewMobil.setLayoutManager(new LinearLayoutManager(this));
        listMobil = new ListMobil().MOBIL;
        adapter = new RecyclerViewAdapter(this, listMobil);
        binding.recyclerViewMobil.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_search);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
                            startActivity(new Intent(getApplicationContext()
                            , MainActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_search:
//                            startActivity(new Intent(getApplicationContext()
//                                    ,DaftarMobil.class));
//                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_booking:
                            startActivity(new Intent(getApplicationContext()
                                    ,Booking.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_profile:
                            startActivity(new Intent(getApplicationContext()
                                    , Profile.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_ticket:
                            startActivity(new Intent(getApplicationContext()
                                    , AskTicket.class));
                            overridePendingTransition(0,0);
                            return true;
                    }
                    return false;
                }
            };
}