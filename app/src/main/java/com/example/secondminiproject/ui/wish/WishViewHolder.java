package com.example.secondminiproject.ui.wish;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.WishService;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WishViewHolder";
    private ImageView wishProductImage;
    private TextView wishProductName;
    private TextView wishProductPrice;

    private ImageButton btnRemoveWish;
    private OnDeleteWishListener onDeleteWishListener;
    private WishAdapter.OnItemClickListener onItemClickListener;
    private NavController navController;



    public WishViewHolder(@NonNull View itemView, WishAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        this.wishProductImage = itemView.findViewById(R.id.wish_product_image);
        this.wishProductName = itemView.findViewById(R.id.wish_product_name);
        this.wishProductPrice = itemView.findViewById(R.id.wish_product_price);
        this.btnRemoveWish = itemView.findViewById(R.id.btnRemoveWish);
        this.onDeleteWishListener = onDeleteWishListener;

        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());//어뎁터 항목번호
        });
    }

    public interface OnDeleteWishListener {
        void onDeleteWish(int productNo);
    }

    public void setData(Product wishProduct, int productNo, NavController navController, int position){
        this.navController = navController;
        ProductService.loadImageByMediaName(productNo, "thumbnail",this.wishProductImage);
        this.wishProductName.setText(wishProduct.getProductTitle());
        DecimalFormat df = new DecimalFormat("#,###");
        this.wishProductPrice.setText(String.valueOf(df.format(wishProduct.getProductAdultPrice())));
        this.btnRemoveWish.setOnClickListener(v->{
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                    .setTitle("삭제하시겠습니까?")
                    .setIcon(R.drawable.ic_wish_list_delete_24dp)
                    .setMessage("진짜 지울거야?")
                    .setPositiveButton("삭제", (dialog, which) -> {
                                Log.i(TAG, "삭제 버튼이 클릭됨");
                                WishService wishService = ServiceProvider.getWishService(navController.getContext());
                                int userNo = Integer.parseInt(AppKeyValueStore.getValue(btnRemoveWish.getContext(),"userNo"));
                                Call<Void> call = wishService.deleteWish(productNo, userNo);
                        Log.i(TAG, "id: " + userNo);
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Log.i(TAG, "proNo: " + productNo);
                                        Log.i(TAG, "position: " + position);

                                        Bundle bundle = new Bundle();
                                        bundle.putInt("startPage",1);
                                        navController.navigate(R.id.dest_tabs_wish_recent_list,bundle);

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });

                    })
                    .setNegativeButton("취소", (dialog, which) -> Log.i(TAG, "취소 버튼이 클릭됨"))
                    .create();
            alertDialog.show();
        });
    }
}
