package com.example.secondminiproject.ui.wish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;
import com.example.secondminiproject.ui.review.ReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishViewHolder> {
    private List<Product> wishList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;
    private NavController navController;
    private WishAdapter wishAdapter;

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View wishView = layoutInflater.inflate(R.layout.fragment_wish_card, parent, false);
        //괄호안에 inflater 넣어야함
        WishViewHolder wishViewHolder = new WishViewHolder(wishView,onItemClickListener);

        return wishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        Product wishProduct = wishList.get(position);
        //홀더에 데이터를 세팅해준다.
        holder.setData(wishProduct, wishProduct.getProductNo(), navController,position);
    }

    public void setWishList(List<Product> WishList) {
        this.wishList = WishList;
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }
    public Product getItem(int position){
        return wishList.get(position);
    }

    public void addWishProduct(Product wishProduct){
        wishList.add(wishProduct);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setNavController(NavController navController){
        this.navController = navController;
    };

    public void deleteWishItem(int position){
        wishList.remove(position);
        notifyItemRemoved(position);
    }
}
