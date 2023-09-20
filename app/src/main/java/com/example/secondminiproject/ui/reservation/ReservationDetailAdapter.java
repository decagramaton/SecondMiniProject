package com.example.secondminiproject.ui.reservation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationDetailAdapter extends RecyclerView.Adapter<ReservationDetailViewHolder> {
    private static final String TAG = "ReservationDetailAdapter";
    private List<Reservation> reservationList;

    private OnItemClickListener onItemClickListener;
    private NavController navController;
    private ProductService productService;


    public ReservationDetailAdapter(List<Reservation> reservationList, NavController navController, ProductService productService) {
        this.reservationList = reservationList;
        this.navController = navController;
        this.productService = productService;
    }


    @NonNull
    @Override
    public ReservationDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_recent_list, parent, false);
        ReservationDetailViewHolder reservationDetailViewHolder = new ReservationDetailViewHolder(itemView,onItemClickListener, navController);

        return reservationDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationDetailViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        Call<Board> call = productService.getProductByProductNo(reservation.getProductNo());
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Board productInfo = response.body();

                holder.setData(reservation, productInfo);
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public Reservation getItem(int position){
        return reservationList.get(position);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(ReservationDetailAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


}
