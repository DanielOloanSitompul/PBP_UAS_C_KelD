package com.danieloloan.pbp_uts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.danieloloan.pbp_uts.Api.PesananAPI;
import com.danieloloan.pbp_uts.Booked;
import com.danieloloan.pbp_uts.BookedRecyclerViewAdapter;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.login.UserID.UID;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class Booking extends AppCompatActivity {
    private Button addBtn;
    private RecyclerView recyclerView;
    private List<Booked> bookedList;
    private SwipeRefreshLayout refreshLayout;
    private BookedRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        addBtn = findViewById(R.id.add_button);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_booking);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        refreshLayout = findViewById(R.id.swipe_refresh);
        bookedList = new ArrayList<Booked>();
        recyclerView = findViewById(R.id.list_pesanan_rv);
        adapter = new BookedRecyclerViewAdapter(Booking.this, bookedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Booking.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshing(true);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle data = new Bundle();
                Tambah_pesanan addBooking = new Tambah_pesanan();
                addBooking.setArguments(data);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_booking, addBooking )
                        .commit();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBooking();
                refreshLayout.setRefreshing(false);
            }
        });

        getBooking();
    }

    public void getBooking() {
        RequestQueue queue = Volley.newRequestQueue(Booking.this);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(Booking.this);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menampilkan data booking");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, PesananAPI.URL_SELECT + UID.getUserID() ,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    if (!bookedList.isEmpty())
                        bookedList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                            int id = jsonObject.optInt("id");
                            String UserID = jsonObject.optString("userID");
                            String nama = jsonObject.optString("nama");
                            String alamat = jsonObject.optString("alamat");
                            String mobil = jsonObject.optString("mobil");
                            int lamaSewa = jsonObject.optInt("lamaSewa");
                            int harga = jsonObject.optInt("harga");

                            Booked booked =
                                    new Booked(id, UserID, nama, alamat, mobil, lamaSewa, harga);

                            bookedList.add(booked);
                        }
                        adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        refreshLayout.setRefreshing(false);
        queue.add(stringRequest);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
                            startActivity(new Intent(getApplicationContext()
                                    ,MainActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_search:
                            startActivity(new Intent(getApplicationContext()
                                    , DaftarMobil.class));
                            overridePendingTransition(0,0);
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
                    return true;
                }
            };
}