package com.danieloloan.pbp_uts.login.TestRegister;

public interface RegisterView {
    String getFullName();
    void showFullNameError(String message);
    String getPhone();
    void showPhoneError(String message);
    String getAddress();
    void showAddressError(String message);
    String getBirthday();
    void showBirthdayError(String message);
    String getEmail();
    void showEmailError(String message);
    String getPassword();
    void showPasswordError(String message);
    void startLoginActivity();
    //    void restartLoginActivity(String cek);
//    void startUserProfileActivity(UserDAO user);
    void showRegisterError(String message);
    void showErrorResponse(String message);
}
