package com.example.secondminiproject.ui.RecentProduct;

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
    private List<RecentProduct> recentProductList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public RecentProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View recentProductView = layoutInflater.inflate(R.layout.fragment_recent_product_card, parent, false);
        //괄호안에 inflater 넣어야함
        RecentProductViewHolder recentProductViewHolder = new RecentProductViewHolder(recentProductView,onItemClickListener);

        return recentProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentProductViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        RecentProduct recentProduct = recentProductList.get(position);
        //홀더에 데이터를 세팅해준다.
        holder.setData(recentProduct);
    }

    public void setRecentProductList(List<RecentProduct> recentProductList) {
        this.recentProductList = recentProductList;
    }

    @Override
    public int getItemCount() {
        return recentProductList.size();
    }
    public RecentProduct getItem(int position){
        return recentProductList.get(position);
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
