package com.example.secondminiproject.ui.RecentProduct;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.RecentProduct;

import java.util.ArrayList;
import java.util.List;

public class RecentProductAdapter extends RecyclerView.Adapter<RecentProductViewHolder> {
    private static final String TAG = "RecentProductAdapter";
    private List<RecentProduct> recentProductList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public RecentProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View recentProductView = layoutInflater.inflate(R.layout.fragment_recent_product_card, parent, false);
        RecentProductViewHolder recentProductViewHolder = new RecentProductViewHolder(recentProductView,onItemClickListener);

        return recentProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentProductViewHolder holder, int position) {
        Log.i(TAG, "최근본 상품 ViewHolder에 List 데이터 추가 이벤트 실행");
        Log.i(TAG, "최근본 상품 ViewHolder에 List 데이터 : "+ recentProductList);
        RecentProduct recentProduct = recentProductList.get(position);
        holder.setData(recentProduct);
    }

    @Override
    public int getItemCount() {
        return recentProductList.size();
    }
    public RecentProduct getItem(int position){
        return recentProductList.get(position);
    }

    public void setRecentProductList(List<RecentProduct> recentProductList) {
        this.recentProductList = recentProductList;
    }

    public void addRecentProduct(RecentProduct recentProduct){
        recentProductList.add(recentProduct);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
