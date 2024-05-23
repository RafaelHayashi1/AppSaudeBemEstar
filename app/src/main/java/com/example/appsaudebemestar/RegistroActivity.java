package com.example.appsaudebemestar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextSenha, editTextTelefone, editTextEndereco;
    private Button buttonRegistrar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        final String nome = editTextNome.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        final String telefone = editTextTelefone.getText().toString().trim();
        final String endereco = editTextEndereco.getText().toString().trim();

        if (TextUtils.isEmpty(nome)) {
            editTextNome.setError("Nome é necessário.");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email é necessário.");
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            editTextSenha.setError("Senha é necessária.");
            return;
        }

        if (TextUtils.isEmpty(telefone)) {
            editTextTelefone.setError("Telefone é necessário.");
            return;
        }

        if (TextUtils.isEmpty(endereco)) {
            editTextEndereco.setError("Endereço é necessário.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference userRef = mDatabase.child(userId);

                            Usuario usuario = new Usuario(nome, email, telefone, endereco);
                            userRef.setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistroActivity.this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show();

                                        editTextNome.setText("");
                                        editTextEmail.setText("");
                                        editTextSenha.setText("");
                                        editTextTelefone.setText("");
                                        editTextEndereco.setText("");


                                        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(RegistroActivity.this, "Erro ao registrar usuário: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegistroActivity.this, "Erro ao registrar usuário: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public static class Usuario {
        public String nome;
        public String email;
        public String telefone;
        public String endereco;

        public Usuario() {
            // mudar dps
        }

        public Usuario(String nome, String email, String telefone, String endereco) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.endereco = endereco;
        }
    }
}
