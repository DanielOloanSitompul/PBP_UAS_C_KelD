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
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danieloloan.pbp_uts.Api.TiketAPI;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.Tiket.TiketDAO;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.PUT;

public class Update_tiket extends Fragment {

    TextInputEditText input_nama, input_address, input_email, input_question;
    private String sIdUser, sNama, sAddress, sEmail, sQuestion;
    Button btn_save, btn_cancel, btn_delete;
    TiketDAO tiket;

    public Update_tiket() {
        // Required empty public constructor
    }

    public static Update_tiket newInstance(String param1, String param2) {
        Update_tiket fragment = new Update_tiket();
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
        View view = inflater.inflate(R.layout.fragment_update_tiket, container, false);
        tiket = (TiketDAO) getArguments().getSerializable("tiket");

        input_nama = view.findViewById(R.id.input_nama);
        input_address = view.findViewById(R.id.input_address);
        input_email = view.findViewById(R.id.input_email);
        input_question = view.findViewById(R.id.input_question);
        btn_save = view.findViewById(R.id.btn_update);
        btn_delete= view.findViewById(R.id.btn_delete);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        input_nama.setText(tiket.getNama());
        input_address.setText(tiket.getAlamat());
        input_email.setText(tiket.getEmail());
        input_question.setText(tiket.getQuestion());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_nama.getText().toString().isEmpty()) {
                    input_nama.setError("Isikan dengan benar");
                    input_nama.requestFocus();
                } else if (input_address.getText().toString().isEmpty()) {
                    input_address.setError("Isikan dengan benar");
                    input_address.requestFocus();
                } else if (input_email.getText().toString().isEmpty()) {
                    input_email.setError("Isikan dengan benar");
                    input_email.requestFocus();
                }else if (input_question.getText().toString().isEmpty()) {
                    input_question.setError("Isikan dengan benar");
                    input_question.requestFocus();
                }else {
                    tiket.setNama(input_nama.getText().toString());
                    tiket.setAlamat(input_address.getText().toString());
                    tiket.setEmail(input_email.getText().toString());
                    tiket.setQuestion(input_question.getText().toString());
                    updateUser(tiket);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTiketById(tiket);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(Update_tiket.this).commit();
            }
        });

        return view;
    }

    public void updateUser(final TiketDAO tiket){
        RequestQueue queue = Volley.newRequestQueue(getContext()) ;

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Mengubah data Question Ticket");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(PUT, TiketAPI.URL_UPDATE + tiket.getId(), new
                com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            progressDialog.dismiss();
                            startActivity(new Intent(getContext()
                                    ,AskTicket.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            startActivity(new Intent(getContext()
                                    ,AskTicket.class));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext()
                        ,AskTicket.class));
            }
        }){
            @Override
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", tiket.getNama());
                params.put("alamat", tiket.getAlamat());
                params.put("email", tiket.getEmail());
                params.put("question", tiket.getQuestion());

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteTiketById (final TiketDAO tiket){
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menghapus data Question Tiket");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(DELETE, TiketAPI.URL_DELETE + tiket.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            progressDialog.dismiss();
                            startActivity(new Intent(getContext()
                                    ,AskTicket.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}