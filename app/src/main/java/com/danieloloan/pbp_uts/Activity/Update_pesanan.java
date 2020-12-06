package com.danieloloan.pbp_uts.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.PUT;

public class Update_pesanan extends Fragment {

    TextInputEditText editName, editAddress;
    AutoCompleteTextView editNamaMobil, editLamaSewa;
    String addMobil, addLamaSewa, addHarga;
    Button saveBtn, deleteBtn, cancelBtn;
    Booked booked;
    int HargaByMobil,hitung;

    public Update_pesanan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_pesananan, container, false);
        booked = (Booked) getArguments().getSerializable("pesanan");
        editName = view.findViewById(R.id.input_nama);
        editAddress = view.findViewById(R.id.input_address);
        editNamaMobil = view.findViewById(R.id.dropDownMobil);
        editLamaSewa = view.findViewById(R.id.dropDownHari);
        saveBtn = view.findViewById(R.id.btn_update);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        //setTextView
        editName.setText(booked.getSimpan_nama());
        editAddress.setText(booked.getSimpan_address());
        editNamaMobil.setText(booked.getSimpan_namaMobil());
        addMobil =  editNamaMobil.getText().toString();
        editLamaSewa.setText(booked.getStringSimpan_lamaSewa());
        addLamaSewa = editLamaSewa.getText().toString();
        hitung = Integer.parseInt(addLamaSewa);
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

        AutoCompleteTextView mobil,hari;
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
                addHarga = String.valueOf(hitung*HargaByMobil);
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
                    hitung = 1;
                }else if (addLamaSewa.equals("2")){
                    hitung = 2;
                }else if (addLamaSewa.equals("3")){
                    hitung = 3;
                }else if (addLamaSewa.equals("7")){
                    hitung = 7;
                }else if (addLamaSewa.equals("14")) {
                    hitung = 14;
                }
                addHarga = String.valueOf(hitung*HargaByMobil);
            }
        });
        addHarga = String.valueOf(hitung*HargaByMobil);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    booked.setSimpan_nama(editName.getText().toString());
                    booked.setSimpan_address(editAddress.getText().toString());
                    booked.setSimpan_namaMobil(editNamaMobil.getText().toString());
                    booked.setStringSimpan_lamaSewa(addLamaSewa);
                    booked.setStringSimpan_harga(addHarga);
                    update(booked);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMessage("Are you sure want to delete?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(booked);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(Update_pesanan.this).commit();
            }
        });
    }

    public void delete (final Booked booked){
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menghapus data booking");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(DELETE, PesananAPI.URL_DELETE + booked.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            progressDialog.dismiss();
                            startActivity(new Intent(getContext()
                                    ,Booking.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            startActivity(new Intent(getContext()
                                    ,Booking.class));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext()
                        ,Booking.class));
            }
        });
        queue.add(stringRequest);
    }

    public void update(final Booked booked){
        RequestQueue queue = Volley.newRequestQueue(getContext()) ;

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Mengubah data booking");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(PUT, PesananAPI.URL_UPDATE + booked.getId(), new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            progressDialog.dismiss();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.hide(Update_pesanan.this).commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Patikan seluruh field diisi !!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", booked.getSimpan_nama());
                params.put("alamat", booked.getSimpan_address());
                params.put("mobil", booked.getSimpan_namaMobil());
                params.put("lamaSewa", String.valueOf(booked.getStringSimpan_lamaSewa()));
                params.put("harga", String.valueOf(booked.getStringSimpan_harga()));

                return params;
            }
        };
        queue.add(stringRequest);
    }
}