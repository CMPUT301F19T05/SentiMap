package com.example.infinimood.view;

import com.example.infinimood.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.infinimood.controller.BooleanCallback;
import com.example.infinimood.controller.MainController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * MainActivity.java
 * Home page when not logged in
 * Options to login and create account
 */

public class MainActivity extends MoodCompatActivity {

    MainController controller;

    FrameLayout progressOverlayContainer;

    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        controller = new MainController(this);

        progressOverlayContainer = findViewById(R.id.progressOverlayContainer);

        editTextEmail = findViewById(R.id.loginCreateAccountEmailEditText);
        editTextPassword = findViewById(R.id.loginCreateAccountPasswordEditText);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseController.userAuthenticated()) {
            controller.userLoggedIn();
        }
    }

    public void onLoginClicked(View view) {
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        if (email.isEmpty()) {
            toast("Please enter your email");
            editTextEmail.requestFocus();
        } else if (password.isEmpty()) {
            toast("Please enter your password");
            editTextPassword.requestFocus();
        } else {
            progressOverlayContainer.setVisibility(View.VISIBLE);
            progressOverlayContainer.bringToFront();

            firebaseController.signIn(MainActivity.this, email, password, new BooleanCallback() {
                @Override
                public void onCallback(boolean success) {
                    if (success) {
                        startActivityNoHistory(UserProfileActivity.class);
                    }
                    progressOverlayContainer.setVisibility(View.GONE);
                }
            });
        }
    }

    public void onCreateAccountClicked(View view) {
        controller.signUp();
    }

}
