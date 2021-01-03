package com.example.appwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProductDescriptionActivity extends AppCompatActivity {
    Button Delete, Update;
    private String description;
    private TextView availableTextView;
    TextView princeTextView;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mISBN;
    private TextView mProductNameTv;
    private String key;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        availableTextView = findViewById(R.id.availableTextView);
        availableTextView.setText(description);
        Anhxa();
        initData();


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpdateProduct.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              AppApi.getInstance().deleteProduct(position);
              Intent intent1 = new Intent(getApplicationContext(),Menu.class);
              startActivity(intent1);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Product product = AppApi.getInstance().getProductByPosition(position);
        mProductNameTv.setText(product.getName());
        availableTextView.setText(product.getDescription());

    }

    private void Anhxa(){
        princeTextView =(TextView) findViewById(R.id.princeTextView);
        Delete = (Button)findViewById(R.id.deleteButton);
        Update = (Button)findViewById(R.id.updateButton);
        mTitle = (EditText)findViewById(R.id.title_ediTxt);
        mAuthor = (EditText)findViewById(R.id.author_ediTxt);
        mISBN = (EditText)findViewById(R.id.isbn_ediTxt);
        mProductNameTv = (TextView)findViewById(R.id.productNameTextView);
        availableTextView =(TextView)findViewById(R.id.availableTextView);




    }

}