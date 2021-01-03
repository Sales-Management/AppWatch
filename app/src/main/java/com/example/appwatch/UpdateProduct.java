package com.example.appwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateProduct extends AppCompatActivity {

    Button mUpdate_btn;
    EditText edtnamr,edtdescription,edttittle;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        Intent intent=getIntent();
        position = intent.getIntExtra("position",0);

        AnhXa();



        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
                UpdateProduct.this.finish();
            }
        });

    }

    private void AnhXa() {
        mUpdate_btn=(Button)findViewById(R.id.update_btn);
        edtnamr =(EditText)findViewById(R.id.author_ediTxt);
        edtdescription=(EditText)findViewById(R.id.isbn_ediTxt);
        edttittle=(EditText)findViewById(R.id.title_ediTxt);
    }

    private void Update() {
       AppApi.getInstance().editProduct(position, edtnamr.getText().toString(),edtdescription.getText().toString(),2131165332);
    }
}


