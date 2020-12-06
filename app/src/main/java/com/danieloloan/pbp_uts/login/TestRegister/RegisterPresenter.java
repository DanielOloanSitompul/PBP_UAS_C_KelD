package com.danieloloan.pbp_uts.login.TestRegister;

import android.util.Patterns;

import com.danieloloan.pbp_uts.PasswordHash.PwHash;
import com.danieloloan.pbp_uts.User;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RegisterPresenter {
    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;
    public RegisterPresenter(RegisterView view, RegisterService service) {
        this.view = view;
        this.service = service;
    }
    public void onRegisterClicked() {
        if (view.getFullName().isEmpty()) {
            view.showFullNameError("Full name is required");
            return;
        }else if (view.getPhone().isEmpty()) {
            view.showPhoneError("Phone is required");
            return;
        }else if (view.getAddress().isEmpty()) {
            view.showAddressError("Address is required");
            return;
        }else if (view.getBirthday().isEmpty()) {
            view.showBirthdayError("Birthday is required");
            return;
        }else if (view.getEmail().isEmpty() ) {
            view.showEmailError("Email is required");
            return;
        }
//        else if(!Patterns.EMAIL_ADDRESS.matcher(view.getEmail()).matches()){
//            view.showEmailError("Please provide valid email");
//            return;
//        }
        else if (view.getPassword().isEmpty()){
            view.showPasswordError("Password is required");
            return;
        } else if (view.getPassword().length()<6){
            view.showPasswordError("Password is too short");
        }else{
            User user = new User(view.getFullName(),view.getPhone(), view.getAddress(), view.getBirthday(),
                    view.getEmail(),PwHash.getEncryptedPW(view.getPassword()));
            service.register(view, user, new RegisterCallback(){
                @Override
                public void onSuccess() {
                    view.showRegisterError("Register Success, check your email for verification!!!");
                    view.startLoginActivity();
                }
                @Override
                public void onError() {
                }
            });
            return;
        }
    }
}
