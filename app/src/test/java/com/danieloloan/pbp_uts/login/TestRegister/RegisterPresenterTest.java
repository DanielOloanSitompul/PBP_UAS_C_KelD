package com.danieloloan.pbp_uts.login.TestRegister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterTest {

    @Mock
    private RegisterView view;
    @Mock
    private RegisterService service;
    private RegisterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenFullnameIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("");
        System.out.println("fullname : "+view.getFullName());
        presenter.onRegisterClicked();
        verify(view).showFullNameError("Full name is required");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("");
        System.out.println("phone : "+view.getPhone());
        presenter.onRegisterClicked();
        verify(view).showPhoneError("Phone is required");
    }

    @Test
    public void shouldShowErrorMessageWhenAddressIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("");
        System.out.println("address : "+view.getAddress());
        presenter.onRegisterClicked();
        verify(view).showAddressError("Address is required");
    }

    @Test
    public void shouldShowErrorMessageWhenBirthdayIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("GG Banana");
        System.out.println("address : "+view.getAddress());
        when(view.getBirthday()).thenReturn("");
        System.out.println("birthday : "+view.getBirthday());
        presenter.onRegisterClicked();
        verify(view).showBirthdayError("Birthday is required");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("GG Banana");
        System.out.println("address : "+view.getAddress());
        when(view.getBirthday()).thenReturn("010100");
        System.out.println("birthday : "+view.getBirthday());
        when(view.getEmail()).thenReturn("");
        System.out.println("email : "+view.getEmail());
        presenter.onRegisterClicked();
        verify(view).showEmailError("Email is required");
    }

//    @Test
//    public void shouldShowErrorMessageWhenEmailIsInvalid() throws Exception {
//        when(view.getFullName()).thenReturn("DanielSitompul");
//        System.out.println("fullname : "+view.getFullName());
//        when(view.getPhone()).thenReturn("081212121212");
//        System.out.println("phone : "+view.getPhone());
//        when(view.getAddress()).thenReturn("GG Banana");
//        System.out.println("address : "+view.getAddress());
//        when(view.getBirthday()).thenReturn("010100");
//        System.out.println("birthday : "+view.getBirthday());
//        when(view.getEmail()).thenReturn("bangjago");
//        System.out.println("email : "+view.getEmail());
//        presenter.onRegisterClicked();
//        verify(view).showEmailError("Please provide valid email");
//    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("GG Banana");
        System.out.println("address : "+view.getAddress());
        when(view.getBirthday()).thenReturn("010100");
        System.out.println("birthday : "+view.getBirthday());
        when(view.getEmail()).thenReturn("bangjago@gmail.com");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("");
        System.out.println("password : "+view.getPassword());
        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password is required");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsShort() throws Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("GG Banana");
        System.out.println("address : "+view.getAddress());
        when(view.getBirthday()).thenReturn("010100");
        System.out.println("birthday : "+view.getBirthday());
        when(view.getEmail()).thenReturn("bangjago@gmail.com");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("123");
        System.out.println("password : "+view.getPassword());
        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password is too short");
    }

    @Test
    public void shouldStartMainActivityWhenDatasAreCorrect() throws
            Exception {
        when(view.getFullName()).thenReturn("DanielSitompul");
        System.out.println("fullname : "+view.getFullName());
        when(view.getPhone()).thenReturn("081212121212");
        System.out.println("phone : "+view.getPhone());
        when(view.getAddress()).thenReturn("GG Banana");
        System.out.println("address : "+view.getAddress());
        when(view.getBirthday()).thenReturn("010100");
        System.out.println("birthday : "+view.getBirthday());
        when(view.getEmail()).thenReturn("bangjago@gmail.com");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("1234567");
        System.out.println("password : "+view.getPassword());
        presenter.onRegisterClicked();
    }

}