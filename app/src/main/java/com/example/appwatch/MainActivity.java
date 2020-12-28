package com.example.appwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {
    Button btnDangky;
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuthencation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuthencation = FirebaseAuth.getInstance();

        Anhxa();

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }


    private void DangKy(){
        String email = edtEmail.getText().toString();
        String password= edtPassword.getText().toString();

        mAuthencation.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this,"Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(MainActivity.this,"Lỗi!!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
    private void Anhxa(){
        btnDangky = (Button) findViewById(R.id.btnDangKy);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }
}