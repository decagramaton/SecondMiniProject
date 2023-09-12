package com.example.secondminiproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> productList = new ArrayList<>();

    private ProductAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemVIew - CardView를 인플레이션한 객체
        // inflate를 정적으로 얻는 방법을 사용하여 객체를 얻음
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_product_list2, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(itemView, onItemClickListener);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.setData(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product){
        this.productList.add(product);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public Product getItem(int position) {
        return productList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}