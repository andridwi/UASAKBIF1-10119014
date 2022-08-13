package com.example.uasakbif110119014;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    private EditText editname, editEmail, editPassword, editPasswordconf;
    private Button btnlogin, btnregister;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnregister = (Button) findViewById(R.id.btn_register);
        editname = findViewById(R.id.nama);
        editPasswordconf = findViewById(R.id.passwordcon);

        mAuth = FirebaseAuth.getInstance();


        btnlogin.setOnClickListener(v -> {
            finish();
        });
        btnregister.setOnClickListener(v -> {
            if (editname.getText().length() > 0 && editEmail.getText().length() > 0 && editPassword.getText().length() > 0 && editPasswordconf.getText().length() > 0) {
                if (editPassword.getText().toString().equals(editPasswordconf.getText().toString())) {
                    register(editname.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "silahkan masukan paswword yang sama", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(String nama, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    }else{
                        Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
                    }

                }
        });

    }

    private  void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}
