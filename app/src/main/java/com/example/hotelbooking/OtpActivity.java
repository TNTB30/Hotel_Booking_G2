package com.example.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText[] otpDigits;
    private TextView timerText;
    private TextView resendText;
    private Button confirmButton;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

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

        // Initialize OTP digit fields
        otpDigits = new EditText[4];
        otpDigits[0] = findViewById(R.id.otpDigit1);
        otpDigits[1] = findViewById(R.id.otpDigit2);
        otpDigits[2] = findViewById(R.id.otpDigit3);
        otpDigits[3] = findViewById(R.id.otpDigit4);

        timerText = findViewById(R.id.timerText);
        resendText = findViewById(R.id.resendText);
        confirmButton = findViewById(R.id.confirmButton);

        // Set up OTP digit field behavior
        setupOtpInputs();

        // Set up keypad buttons
        setupKeypad();

        // Start countdown timer
        startCountdownTimer();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate OTP
                if (validateOtp()) {
                    // Navigate to Reset Password screen
                    Intent intent = new Intent(OtpActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupOtpInputs() {
        for (int i = 0; i < otpDigits.length; i++) {
            final int currentIndex = i;
            otpDigits[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && currentIndex < otpDigits.length - 1) {
                        otpDigits[currentIndex + 1].requestFocus();
                    }
                }
            });
        }
    }

    private void setupKeypad() {
        findViewById(R.id.keypad0).setOnClickListener(this);
        findViewById(R.id.keypad1).setOnClickListener(this);
        findViewById(R.id.keypad2).setOnClickListener(this);
        findViewById(R.id.keypad3).setOnClickListener(this);
        findViewById(R.id.keypad4).setOnClickListener(this);
        findViewById(R.id.keypad5).setOnClickListener(this);
        findViewById(R.id.keypad6).setOnClickListener(this);
        findViewById(R.id.keypad7).setOnClickListener(this);
        findViewById(R.id.keypad8).setOnClickListener(this);
        findViewById(R.id.keypad9).setOnClickListener(this);
        findViewById(R.id.keypadBackspace).setOnClickListener(this);
    }

    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.format("00:%02d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00");
                resendText.setTextColor(getResources().getColor(R.color.primary));
                resendText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Resend OTP
                        startCountdownTimer();
                    }
                });
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // Find the first empty or focused digit field
        EditText currentField = null;
        for (EditText digit : otpDigits) {
            if (digit.getText().toString().isEmpty() || digit.hasFocus()) {
                currentField = digit;
                break;
            }
        }

        if (currentField == null) {
            currentField = otpDigits[otpDigits.length - 1];
        }

        if (id == R.id.keypadBackspace) {
            currentField.setText("");
            // Move focus to previous field if not the first one
            int currentIndex = -1;
            for (int i = 0; i < otpDigits.length; i++) {
                if (otpDigits[i] == currentField) {
                    currentIndex = i;
                    break;
                }
            }
            if (currentIndex > 0) {
                otpDigits[currentIndex - 1].requestFocus();
            }
        } else {
            // Get the digit from the button text
            String digit = ((Button) v).getText().toString();
            currentField.setText(digit);
        }
    }

    private boolean validateOtp() {
        // Add validation logic here
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

