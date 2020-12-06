package com.danieloloan.pbp_uts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.map.Location;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mapbox.android.core.permissions.PermissionsManager;

public class MainActivity extends AppCompatActivity {
    ViewFlipper autoSlide;
    private Button location;
    private TextView Explore;
    private static final String DESTINATION_SYMBOL_LAYER_ID="destination-symbol-layer-id";
    private static final String DESTINATION_ICON_ID="destination-icon-id";
    private static final String DESTINATION_SOURCE_ID = "destination-source-id";
    private PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.navigation_home);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Explore = findViewById(R.id.explore);

        int images[] = {R.drawable.autoslide1, R.drawable.autoslide2, R.drawable.autoslide3};
        autoSlide = findViewById(R.id.autoSlide);

        for (int image: images){
            flipperImages(image);
        }
        //location (mapbox)
        location=findViewById(R.id.locationBtn);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, Location.class);
                startActivity(intent);
            }
        });
        //firebase notif
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String desciption = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desciption);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("News")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String mag = "Succesful";
                        if (!task.isSuccessful()){
                            mag = "failed";
                        }
                    }
                });

        Explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , DaftarMobil.class));
            }
        });
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        autoSlide.addView(imageView);
        autoSlide.setFlipInterval(4000);
        autoSlide.setAutoStart(true);
        autoSlide.setInAnimation(this, android.R.anim.slide_in_left);
        autoSlide.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
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