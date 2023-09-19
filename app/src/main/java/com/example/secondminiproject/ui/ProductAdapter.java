package com.example.secondminiproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<Board> boardList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    private OnBtnWishClickListener onBtnWishClickListener;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_product_list2, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(itemView, onItemClickListener, onBtnWishClickListener);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
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

    //찜버튼 클릭 - 어떤뷰가 클릭되었는지, 클릭된 뷰의 위치, 클릭된 아이템의 productNo - 3개를 사용
    public interface OnBtnWishClickListener{
        void onItemClick(View itemView, int position, int productNo);
    }

    public Board getItem(int position) {
        return boardList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnBtnWishClickListener(OnBtnWishClickListener onBtnWishClickListener) {
        this.onBtnWishClickListener = onBtnWishClickListener;
    }
}