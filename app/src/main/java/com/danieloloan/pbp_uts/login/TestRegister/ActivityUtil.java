package com.danieloloan.pbp_uts.login.TestRegister;

import android.content.Context;
import android.content.Intent;

import com.danieloloan.pbp_uts.Activity.MainActivity;
import com.danieloloan.pbp_uts.login.Login;

public class ActivityUtil {
    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startLoginActivity() {
        context.startActivity(new Intent(context, Login.class));
    }
}
