package com.example.secondminiproject.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Wish;
import com.example.secondminiproject.ui.ProductViewHolder;
import com.example.secondminiproject.ui.wish.WishAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder  > {

    private List<Product> productList = new ArrayList<>();

    private HomeAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemVIew - CardView를 인플레이션한 객체
        // inflate를 정적으로 얻는 방법을 사용하여 객체를 얻음
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_product_list2, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(itemView,onItemClickListener);

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.setData(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public Product getItem(int position){
        return productList.get(position);
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}