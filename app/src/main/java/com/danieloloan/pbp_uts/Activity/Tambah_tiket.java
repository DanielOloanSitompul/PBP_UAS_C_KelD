package com.danieloloan.pbp_uts.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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


public class Tambah_tiket extends Fragment {


    TextInputEditText addName, addAddress, addEmail, addQuestion;
    Button addBtn, cancelBtn;

    public Tambah_tiket() {
        // Required empty public constructor
    }

    public static Tambah_tiket newInstance(String param1, String param2) {
        Tambah_tiket fragment = new Tambah_tiket();
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
        View view = inflater.inflate(R.layout.fragment_tambah_tiket, container, false);
        addName = view.findViewById(R.id.addNama);
        addAddress = view.findViewById(R.id.addAddress);
        addEmail = view.findViewById(R.id.addEmail);
        addQuestion = view.findViewById(R.id.addQuestion);
        addBtn = view.findViewById(R.id.add_Ticket);
        cancelBtn = view.findViewById(R.id.cancel_addTicket);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addName.getText().toString().isEmpty()) {
                    addName.setError("Isikan dengan benar");
                    addName.requestFocus();
                } else if (addAddress.getText().toString().isEmpty()) {
                    addAddress.setError("Isikan dengan benar");
                    addAddress.requestFocus();
                } else if (addEmail.getText().toString().isEmpty()) {
                    addEmail.setError("Isikan dengan benar");
                    addEmail.requestFocus();
                } else if (addQuestion.getText().toString().isEmpty()) {
                    addQuestion.setError("Isikan dengan benar");
                    addQuestion.requestFocus();
                } else {
                    saveTicket(UID.getUserID(),addName.getText().toString(),addAddress.getText().toString(),addEmail.getText().toString(),addQuestion.getText().toString());
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(Tambah_tiket.this).commit();
            }
        });


        return view;
    }

    public void saveTicket(final String userID, final String nama, final String alamat, final String email, final String question){
        RequestQueue queue = Volley.newRequestQueue(getContext()) ;

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menambahkan data Question Ticket");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(POST, TiketAPI.URL_ADD, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    startActivity(new Intent(getContext()
                            ,AskTicket.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext()
                            ,AskTicket.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext()
                        ,AskTicket.class));
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", userID);
                params.put("nama", nama);
                params.put("alamat", alamat);
                params.put("email", email);
                params.put("question", question);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}