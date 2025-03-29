package com.example.hotelbooking;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        // Setup profile menu items
        setupMenuItem(R.id.accountItem, R.drawable.ic_person, R.string.account, R.string.account_subtitle);
        setupMenuItem(R.id.securityItem, R.drawable.ic_security, R.string.security, R.string.security_subtitle);
        setupMenuItem(R.id.themeItem, R.drawable.ic_color_lens, R.string.theme, R.string.theme_subtitle);
        setupMenuItem(R.id.bookingsItem, R.drawable.ic_hotel, R.string.bookings, R.string.bookings_subtitle);
        setupMenuItem(R.id.settingsItem, R.drawable.ic_settings, R.string.settings, R.string.settings_subtitle);
    }

    private void setupMenuItem(int itemId, int iconResId, int titleResId, int subtitleResId) {
        ConstraintLayout item = findViewById(itemId);
        ImageView icon = item.findViewById(R.id.iconImage);
        TextView title = item.findViewById(R.id.titleText);
        TextView subtitle = item.findViewById(R.id.subtitleText);

        icon.setImageResource(iconResId);
        title.setText(titleResId);
        subtitle.setText(subtitleResId);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle menu item click
            }
        });
    }
}

