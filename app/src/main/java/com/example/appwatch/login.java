package com.example.appwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class login extends AppCompatActivity {
    Button Login;
    EditText edtTK, edtMK;
    FirebaseAuth mAuthencation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuthencation = FirebaseAuth.getInstance();

        Anhxa();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DangNhap();

            }
        });
    }
    private void DangNhap() {
        String taikhoan = edtTK.getText().toString();
        String matkhau = edtMK.getText().toString();
        mAuthencation.signInWithEmailAndPassword(taikhoan, matkhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){

                             Toast.makeText(login.this,"Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(), Menu.class);
                             startActivity(intent);

                         }else {
                             Toast.makeText(login.this,"Lỗi!!",Toast.LENGTH_SHORT).show();

                         }
                    }
                });


    }


        private void Anhxa(){
            Login = (Button)findViewById(R.id.btnLogin);
            edtTK = (EditText) findViewById(R.id.edtTk);
            edtMK = (EditText) findViewById(R.id.edtMk);
        }
}
