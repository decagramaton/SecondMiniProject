package com.example.secondminiproject.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class RecommendPackageAdapter extends RecyclerView.Adapter<RecommendPackageViewHolder> {
    private static final String TAG = "ProductAdapter";

    private List<Board> boardList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public RecommendPackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_home_recommend_package_item, parent, false);
        RecommendPackageViewHolder recommendPackageViewHolder = new RecommendPackageViewHolder(itemView, onItemClickListener);

        return recommendPackageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendPackageViewHolder holder, int position) {
        Board board = boardList.get(position);
        holder.setData(board);
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public void setList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public void addProduct(Board board){
        this.boardList.add(board);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public Board getItem(int position) {
        return boardList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}