package com.example.infinimood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class MainActivity extends MoodCompatActivity {

    FrameLayout progressBarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        progressBarContainer = findViewById(R.id.progress_bar_container);

        // Already logged in?
        if (firebaseUser != null) {
            toast("Welcome back!");
            startActivityNoHistory(AddEditMood.class);
        }

        // TODO: Debug only
        Button test_addEdit = findViewById(R.id.test_add_edit);
        test_addEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEditMood.class);
                startActivity(intent);
            }
        });
    }

    public void onCreateAccountClicked(View view) {
        final Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void onLoginClicked(View view) {
        progressBarContainer.setVisibility(View.VISIBLE);

    }

}
