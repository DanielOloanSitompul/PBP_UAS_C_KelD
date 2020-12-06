package com.danieloloan.pbp_uts.Activity;

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
import com.danieloloan.pbp_uts.Api.TiketAPI;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.Tiket.TiketDAO;
import com.danieloloan.pbp_uts.Tiket.TiketRecyclerAdapter;
import com.danieloloan.pbp_uts.login.UserID.UID;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class AskTicket extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TiketRecyclerAdapter recyclerAdapter;
    private List<TiketDAO> tiket = new ArrayList<>();
    protected SwipeRefreshLayout swipe_Refresh;
    private Button addBtn;
    private TiketRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_ticket);
        swipe_Refresh = findViewById(R.id.swipe_refresh);
        addBtn = findViewById(R.id.add_button);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_ticket);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        tiket = new ArrayList<TiketDAO>();
        recyclerView = findViewById(R.id.list_tiket_rv);
        adapter = new TiketRecyclerAdapter(AskTicket.this, tiket);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AskTicket.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        swipe_Refresh.setRefreshing(true);


        swipe_Refresh.setRefreshing(true);
        loadTiket();
        swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTiket();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle data = new Bundle();
                Tambah_tiket addTiket = new Tambah_tiket();
                addTiket.setArguments(data);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_ticket, addTiket )
                        .commit();
            }
        });
    }

    public void loadTiket() {
        RequestQueue queue = Volley.newRequestQueue(AskTicket.this);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(AskTicket.this);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menampilkan data booking");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, TiketAPI.URL_SELECT + UID.getUserID(),
                null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    if (!tiket.isEmpty())
                        tiket.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                            int id = jsonObject.optInt("id");
                            String nama = jsonObject.optString("nama");
                            String alamat = jsonObject.optString("alamat");
                            String email = jsonObject.optString("email");
                            String question = jsonObject.optString("question");

                            TiketDAO Tiket = new TiketDAO(id, nama, alamat, email, question);
                            tiket.add(Tiket);
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
        swipe_Refresh.setRefreshing(false);
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