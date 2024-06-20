package com.example.appsaudebemestar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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


    public void abrirPerfil(View view) {
        Intent intent = new Intent(this, PerfilAcitivty.class);
        startActivity(intent);
    }

    public void abrirAlimentacao(View view) {
        Intent intent = new Intent(this, AlimentacaoActivity.class);
        startActivity(intent);
    }

    public void abrirDados(View view) {
        Intent intent = new Intent(this, DadosAcitivity.class);
        startActivity(intent);
    }

    private void logout() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); //

        Toast.makeText(this, "Você saiu da sua conta", Toast.LENGTH_SHORT).show();
    }


}
