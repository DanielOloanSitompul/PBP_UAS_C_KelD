package com.danieloloan.pbp_uts.login.UserID;

import com.google.firebase.auth.FirebaseAuth;

public class UID {
    public static String getUserID (){
        String userID = "";

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        return userID;
    }
}
