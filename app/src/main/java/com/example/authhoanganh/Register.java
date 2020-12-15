package com.example.authhoanganh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    TextView txtLogin;
    EditText ettPassword, ettEmail, ettUserName;
    Button btnRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin = findViewById(R.id.txtLogin);
        btnRegister = findViewById(R.id.btnRegisterR);
        ettEmail = findViewById(R.id.txtEmailR);
        ettPassword = findViewById(R.id.txtPasswordR);
        ettUserName = findViewById(R.id.txtUserName);

        fAuth = FirebaseAuth.getInstance();

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = ettEmail.getText().toString().trim();
                String password = ettPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    ettEmail.setError("Nhap email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ettPassword.setError("Nhap mat khau");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Loi: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),Login.class));
            }
        });
    }
}
