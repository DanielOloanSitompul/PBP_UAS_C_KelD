package com.danieloloan.pbp_uts.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.login.Login;

public class SplashScreen extends AppCompatActivity {
    Animation top,bot;
    ImageView logo;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        top = AnimationUtils.loadAnimation(this,R.anim.top_animatiom);
        bot = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.logoIV);
        name = findViewById(R.id.logoTV);

        logo.setAnimation(top);
        name.setAnimation(bot);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}