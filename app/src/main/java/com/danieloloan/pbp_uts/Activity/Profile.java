package com.danieloloan.pbp_uts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danieloloan.pbp_uts.User;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.login.Login;
import com.danieloloan.pbp_uts.login.UserID.UID;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private ImageView edit,camera;
    private TextView logout;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_profile);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        logout = findViewById(R.id.exitTV);
        edit = findViewById(R.id.editProfile);

        final TextView name = (TextView) findViewById(R.id.nameUserTV);
        final TextView email = (TextView) findViewById(R.id.emailTV);
        final TextView phone = (TextView) findViewById(R.id.phoneTV);
        final TextView address = (TextView) findViewById(R.id.placeTV);
        final TextView birthday = (TextView) findViewById(R.id.birthdayTV);

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

                    name.setText(Name);
                    email.setText(Email);
                    phone.setText(Phone);
                    address.setText(Address);
                    birthday.setText(Birthday);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , ProfileUpdate.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Profile.this, "LogOut Succsessful.",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profile.this, Login.class);
                startActivity(intent);
            }
        });
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
                                    , Booking.class));
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