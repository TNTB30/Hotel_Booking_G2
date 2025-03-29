package com.example.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText phoneInput;
    private CheckBox robotCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        phoneInput = findViewById(R.id.phoneInput);
        robotCheckbox = findViewById(R.id.robotCheckbox);

        Button sendOtpButton = findViewById(R.id.sendOtpButton);
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs
                if (validateInputs()) {
                    // Navigate to OTP screen
                    Intent intent = new Intent(ForgotPasswordActivity.this, OtpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInputs() {
        // Add validation logic here
        return true;
    }
}

