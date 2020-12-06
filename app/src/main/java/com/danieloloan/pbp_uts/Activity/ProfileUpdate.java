package com.danieloloan.pbp_uts.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.User;
import com.danieloloan.pbp_uts.login.UserID.UID;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileUpdate extends AppCompatActivity {

    TextInputEditText edtName,edtEmail, edtPhone, edtPlace, edtBirthday ;
    private ImageView cancelEdit,camera;
    private DatabaseReference reference;
    Button save,cancel;
    private int STORAGE_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_profile);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        cancelEdit = findViewById(R.id.cancelEdit);
        camera = findViewById(R.id.cameraIV);
        edtName = findViewById(R.id.nameUserEdit);
        edtEmail = findViewById(R.id.emailEdit);
        edtPhone = findViewById(R.id.phoneEdit);
        edtPlace = findViewById(R.id.placeEdit);
        edtBirthday = findViewById(R.id.birthdayEdit);
        save = findViewById(R.id.saveEditP);
        cancel = findViewById(R.id.cancelEditP);

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(UID.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);

                if(userProfile != null){
                    String Name = userProfile.fullname;
                    String Email = userProfile.email;
                    String Phone = userProfile.phone;
                    String Address = userProfile.address;
                    String Birthday = userProfile.birthday;

                    edtName.setText(Name);
                    edtEmail.setText(Email);
                    edtPhone.setText(Phone);
                    edtPlace.setText(Address);
                    edtBirthday.setText(Birthday);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileUpdate.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEdit();
                startActivity(new Intent(getApplicationContext()
                        , Profile.class));
                overridePendingTransition(0,0);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , Profile.class));
                overridePendingTransition(0,0);
            }
        });

        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , Profile.class));
                overridePendingTransition(0,0);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ProfileUpdate.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    requestStoragePermission();
                }
                else{
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,100);
                }
            }
        });
    }

    private void saveEdit(){
        reference.child(UID.getUserID()).child("fullname").setValue(edtName.getText().toString());
        reference.child(UID.getUserID()).child("phone").setValue(edtPhone.getText().toString());
        reference.child(UID.getUserID()).child("address").setValue(edtPlace.getText().toString());
        reference.child(UID.getUserID()).child("birthday").setValue(edtBirthday.getText().toString());
    }

    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("test")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ProfileUpdate.this ,new String[] {Manifest.permission.CAMERA}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
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