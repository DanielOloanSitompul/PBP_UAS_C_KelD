package com.danieloloan.pbp_uts.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danieloloan.pbp_uts.Api.PesananAPI;
import com.danieloloan.pbp_uts.Booked;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.login.UserID.UID;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class Tambah_pesanan extends Fragment {

    TextInputEditText addName, addAddress;
    String addMobil, addLamaSewa, addHarga;
    AutoCompleteTextView mobil,hari;
    int HargaByMobil,hitung;
    Button addBtn, cancelBtn;
    Booked booked;

    public Tambah_pesanan() {
        // Required empty public constructor
    }

    public static Tambah_pesanan newInstance(String param1, String param2) {
        Tambah_pesanan fragment = new Tambah_pesanan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_pesanan, container,false);
        booked = (Booked) getArguments().getSerializable("booked");
        addName = view.findViewById(R.id.addNama);
        addAddress = view.findViewById(R.id.addAddress);
        addBtn = view.findViewById(R.id.add_booking);
        cancelBtn = view.findViewById(R.id.cancel_addBooking);
        mobil = view.findViewById(R.id.dropDownMobil);
        final String[] Mobil = new String[]{
                "Avanza", "Xpander", "Fortuner", "Ertiga", "HRV", "CRV", "Calya", "Pajero", "Outlander"
        };
        final ArrayAdapter<String> adapter_mobil = new ArrayAdapter<>(getActivity(), R.layout.dropdown, Mobil);
        mobil.setAdapter(adapter_mobil);
        mobil.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mobil = adapterView.getItemAtPosition(i).toString();
                addMobil = mobil;
                if (addMobil.equals("Avanza")) {
                    HargaByMobil = 120000;
                }else if (addMobil.equals("Xpander")){
                    HargaByMobil = 280000;
                }else if (addMobil.equals("Fortuner")){
                    HargaByMobil = 250000;
                }else if (addMobil.equals("Ertiga")){
                    HargaByMobil = 150000;
                }else if (addMobil.equals("HRV")){
                    HargaByMobil = 200000;
                }else if (addMobil.equals("CRV")){
                    HargaByMobil = 220000;
                }else if (addMobil.equals("Calya")){
                    HargaByMobil = 150000;
                }else if (addMobil.equals("Pajero")){
                    HargaByMobil = 280000;
                }else if (addMobil.equals("Outlander")){
                    HargaByMobil = 275000;
                }
            }
        });

        hari = view.findViewById(R.id.dropDownHari);
        final Integer[] Hari = new Integer[]{
                1, 2, 3, 7, 14
        };
        final ArrayAdapter<Integer> adapter_hari = new ArrayAdapter<>(getActivity(), R.layout.dropdown, Hari);
        hari.setAdapter(adapter_hari);
        hari.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String hari = adapterView.getItemAtPosition(i).toString();
                addLamaSewa = hari;
                if (addLamaSewa.equals("1")) {
                    hitung = 1 *  HargaByMobil ;
                }else if (addLamaSewa.equals("2")){
                    hitung = 2 * HargaByMobil;
                }else if (addLamaSewa.equals("3")){
                    hitung = 3 * HargaByMobil;
                }else if (addLamaSewa.equals("7")){
                    hitung = 7 * HargaByMobil;
                }else if (addLamaSewa.equals("14")) {
                    hitung = 14 * HargaByMobil;
                }
                 addHarga = String.valueOf(hitung);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addName.getText().toString().isEmpty()) {
                    addName.setError("Isikan dengan benar");
                    addName.requestFocus();
                } else if (addAddress.getText().toString().isEmpty()) {
                    addAddress.setError("Isikan dengan benar");
                    addAddress.requestFocus();
                } else if (mobil.getText().toString().equals(null)) {
                    mobil.setError("Isikan dengan benar");
                    mobil.requestFocus();
                } else if (hari.getText().toString().equals(null)) {
                    hari.setError("Isikan dengan benar");
                    hari.requestFocus();
                } else {
                    AddPesanan(UID.getUserID(),addName.getText().toString(),addAddress.getText().toString(),addMobil,addLamaSewa,addHarga);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(Tambah_pesanan.this).commit();
            }
        });
    }

    public void AddPesanan(final String userID, final String addName, final String addAddress, final String addMobil,
                           final String addLamaSewa, final String addHarga){
        RequestQueue queue = Volley.newRequestQueue(getContext()) ;

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menambahkan data booking");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(POST, PesananAPI.URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    startActivity(new Intent(getContext()
                            ,Booking.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext()
                            ,Booking.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Pastikan seluruh field sudah diisi !!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", userID);
                params.put("nama", addName);
                params.put("alamat", addAddress);
                params.put("mobil", addMobil);
                params.put("lamaSewa", addLamaSewa);
                params.put("harga", addHarga);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}