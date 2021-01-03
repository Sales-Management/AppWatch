package com.example.appwatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AppApi {
    public static AppApi instance;


    //Properties
    public ArrayList<Product> listProduct = new ArrayList<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("product");

    public static AppApi getInstance() {
        if(instance == null){
            instance = new AppApi();
        }
        return instance;
    }

    public Product getProductByPosition(int productId){
        for(Product product: listProduct){
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    public void editProduct(int position, String name, String description, int resId){
        Product pro = listProduct.get(position);
        pro.setName(name);
        pro.setDescription(description);
        pro.setResourceId(resId);

        databaseReference.updateChildren(new HashMap<String, Object>() {{
            put(position+"", pro);
        }});
    }

    public void deleteProduct(int productId){
        databaseReference.child(productId+"").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                for (Product product: listProduct){
                    if(product.getId() == productId){
                        listProduct.remove(product);
                    }
                }
            }
        });

    }







}
