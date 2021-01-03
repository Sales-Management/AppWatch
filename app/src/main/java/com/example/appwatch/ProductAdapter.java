package com.example.appwatch;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> mListProduct;
    private IClickAddToCartListener iClickAddToCartListener;

    public interface IClickAddToCartListener{
        void onClickAddToCart(ImageView imgAddToCart, Product product);
    }

   public void setData(List<Product> list, IClickAddToCartListener listener ){
        this.iClickAddToCartListener=listener;

       this.mListProduct=list;
       notifyDataSetChanged();
   }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
       return new ProductViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }
        holder.imgProduct.setImageResource(product.getResourceId());
        holder.tvProductname.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        if (product.isAddToCart()) {
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_gray_corner_6);
        } else {
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_red_corner_6);
        }
       holder.imgAddToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!product.isAddToCart()){
                   iClickAddToCartListener.onClickAddToCart(holder.imgAddToCart,product);
               }
           }
       });

        holder.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly trong nay
                Intent intent = new Intent(v.getContext(), ProductDescriptionActivity.class);

                intent.putExtra("description",product.getDescription());
                v.getContext().startActivity(intent);

            }
        });

        holder.tvProductname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDescriptionActivity.class);
                intent.putExtra("position", product.getId());
                v.getContext().startActivity(intent);
                //Code edit


            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListProduct != null){
            return mListProduct.size();
    }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvProductname;
        private Button tvDescription;
        private ImageView imgAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.img_product);
            tvProductname = itemView.findViewById(R.id.tv_product_name);
            tvDescription = itemView.findViewById(R.id.btn_description);
            imgAddToCart = itemView.findViewById(R.id.img_add_to_cart);
        }
    }


}
