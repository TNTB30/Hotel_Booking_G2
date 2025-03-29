package com.example.hotelbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputEditText newPasswordInput;
    private TextInputEditText confirmNewPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

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

        newPasswordInput = findViewById(R.id.newPasswordInput);
        confirmNewPasswordInput = findViewById(R.id.confirmNewPasswordInput);

        Button confirmPasswordButton = findViewById(R.id.confirmPasswordButton);
        confirmPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs
                if (validateInputs()) {
                    // Show success dialog
                    showSuccessDialog();
                }
            }
        });
    }

    private boolean validateInputs() {
        String newPassword = newPasswordInput.getText().toString().trim();
        String confirmNewPassword = confirmNewPasswordInput.getText().toString().trim();

        if (newPassword.isEmpty()) {
            newPasswordInput.setError("Please enter a new password");
            return false;
        }

        if (newPassword.length() < 6) {
            newPasswordInput.setError("Password must be at least 6 characters");
            return false;
        }

        if (confirmNewPassword.isEmpty()) {
            confirmNewPasswordInput.setError("Please confirm your new password");
            return false;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            confirmNewPasswordInput.setError("Passwords do not match");
            return false;
        }

        return true;
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.success)
                .setMessage(R.string.password_reset_success)
                .setPositiveButton(R.string.login_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to Login Email screen and clear all previous activities
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginEmailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .show();
    }
}

