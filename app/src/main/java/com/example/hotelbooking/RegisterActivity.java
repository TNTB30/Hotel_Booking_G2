package com.example.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText firstNameInput;
    private TextInputEditText lastNameInput;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private TextInputEditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    showSuccessSnackbar(v);
                }
            }
        });
    }

    private void showSuccessSnackbar(View view) {
        Snackbar.make(view, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Chuyển sang màn hình ProfileActivity
                        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish(); // Đóng RegisterActivity để không quay lại được
                    }
                })
                .show();
    }
    private boolean validateInputs() {
        if (firstNameInput.getText().toString().trim().isEmpty() ||
                lastNameInput.getText().toString().trim().isEmpty() ||
                emailInput.getText().toString().trim().isEmpty() ||
                passwordInput.getText().toString().trim().isEmpty() ||
                confirmPasswordInput.getText().toString().trim().isEmpty()) {

            showErrorSnackbar("Vui lòng điền đầy đủ thông tin!");
            return false;
        }

        if (!passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
            showErrorSnackbar("Mật khẩu không khớp!");
            return false;
        }

        if (passwordInput.getText().toString().length() < 6) {
            showErrorSnackbar("Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        return true;
    }

    private void showErrorSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }
}

