package com.danieloloan.pbp_uts.login.TestRegister;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.danieloloan.pbp_uts.User;
import com.danieloloan.pbp_uts.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterService {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseAuth mAuth;

    public void register(final RegisterView view, User user, final RegisterCallback callback) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                        FirebaseAuth.getInstance().signOut();
                                        callback.onSuccess();
                                    }else{
                                        view.showRegisterError("Email is already used!!");
                                        callback.onError();
                                    }
                                }
                            });
                        }else {
                            view.showRegisterError("Register Failed!!!");
                            callback.onError();
                        }
                    }
                });
    }


}
