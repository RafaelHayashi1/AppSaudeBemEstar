package com.example.appsaudebemestar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout menuLayout;
    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menuLayout = findViewById(R.id.menuLayout);
    }

    public void toggleMenu(View view) {
        if (isMenuOpen) {
            menuLayout.setVisibility(View.GONE);
            isMenuOpen = false;
        } else {
            menuLayout.setVisibility(View.VISIBLE);
            isMenuOpen = true;
        }
    }


}
