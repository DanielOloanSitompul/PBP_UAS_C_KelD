package com.danieloloan.pbp_uts.login.TestRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.danieloloan.pbp_uts.User;
import com.danieloloan.pbp_uts.R;
import com.danieloloan.pbp_uts.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private FirebaseAuth mAuth;
    private TextInputLayout Fullname,Phone,Address,Birthday,Email,Password;
    private Button Register;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        Fullname = findViewById(R.id.fullnameRegister);
        Phone = findViewById(R.id.phoneRegister);
        Address = findViewById(R.id.addessRegister);
        Birthday = findViewById(R.id.birthdayRegister);
        Email = findViewById(R.id.emailRegister);
        Password = findViewById(R.id.passwordRegister);
        presenter = new RegisterPresenter(this, new RegisterService());

        Register =findViewById(R.id.registerBtn);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRegisterClicked();
            }
        });
    }

    @Override
    public String getFullName() {
        return Fullname.getEditText().getText().toString().trim();
    }
    @Override
    public void showFullNameError(String message) {
        Fullname.setError(message);
    }
    @Override
    public String getPhone() {
        return Phone.getEditText().getText().toString().trim();
    }
    @Override
    public void showPhoneError(String message) {
        Phone.setError(message);
    }
    @Override
    public String getAddress() {
        return Address.getEditText().getText().toString().trim();
    }
    @Override
    public void showAddressError(String message) {
        Address.setError(message);
    }
    @Override
    public String getBirthday() {
        return Birthday.getEditText().getText().toString().trim();
    }
    @Override
    public void showBirthdayError(String message) {
        Birthday.setError(message);
    }
    @Override
    public String getEmail() {
        return Email.getEditText().getText().toString().trim();
    }
    @Override
    public void showEmailError(String message) {
        Email.setError(message);
    }
    @Override
    public String getPassword() {
        return Password.getEditText().getText().toString().trim();
    }
    @Override
    public void showPasswordError(String message) {
        Password.setError(message);
    }
    @Override
    public void startLoginActivity() {
        new ActivityUtil(this).startLoginActivity();
    }

    @Override
    public void showRegisterError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}