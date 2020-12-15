package com.example.authhoanganh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {
    TextView txtCreateAcc;
    EditText ettEmail, ettPassword;
    Button btnLoginToMain;
    FirebaseAuth fAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCreateAcc = findViewById(R.id.txtCreateAcc);
        ettEmail = findViewById(R.id.txtEmail);
        ettPassword = findViewById(R.id.txtPassword);
        btnLoginToMain = findViewById(R.id.btnLoginToMain);
        fAuth = FirebaseAuth.getInstance();

        btnLoginToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ettEmail.getText().toString();
                String password = ettPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    ettEmail.setError("Nhap email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ettPassword.setError("Nhap mat khau");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "createUserWithEmail:success");
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Loi: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        txtCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}
