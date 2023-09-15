package com.example.secondminiproject.ui.wish;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;

public class WishViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WishViewHolder";

    private ImageView wishProductImage;
    private TextView wishProductName;
    private TextView wishTourDays;
    private TextView wishProductPrice;

    private ImageButton btnRemoveWish;


    public WishViewHolder(@NonNull View itemView, WishAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.wishProductImage = itemView.findViewById(R.id.wish_product_image);
        this.wishProductName = itemView.findViewById(R.id.wish_product_name);
        this.wishProductPrice = itemView.findViewById(R.id.wish_product_price);
        this.btnRemoveWish = itemView.findViewById(R.id.btnRemoveWish);

        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());//어뎁터 항목번호
        });
    }

    public void setData(Wish wishProduct){
        this.wishProductImage.setImageResource(wishProduct.getProductImage());
        this.wishProductName.setText(wishProduct.getProductName());
        this.wishProductPrice.setText(String.valueOf(wishProduct.getProductPrice()));
        this.btnRemoveWish.setOnClickListener(v->{
            Log.i(TAG, "다이얼로그가 실행이 되나요?");
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                    .setTitle("삭제하시겠습니까?")
                    .setIcon(R.drawable.ic_earth)
                    .setMessage("          진짜 지울거야?")
                    .setPositiveButton("삭제", (dialog, which) -> Log.i(TAG, "삭제 버튼이 클릭됨"))
                    .setNegativeButton("취소", (dialog, which) -> Log.i(TAG, "취소 버튼이 클릭됨"))
                    .create();
            alertDialog.show();
        });

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}
