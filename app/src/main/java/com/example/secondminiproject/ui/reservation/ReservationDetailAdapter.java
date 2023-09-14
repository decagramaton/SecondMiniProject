package com.example.secondminiproject.ui.reservation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetailAdapter extends RecyclerView.Adapter<ReservationDetailViewHolder> {
    private static final String TAG = "ReservationDetailAdapter";
    private List<Reservation> reservationList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    private NavController navController;


    @NonNull
    @Override
    public ReservationDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.reservation_list_item, parent, false);
        ReservationDetailViewHolder reservationDetailViewHolder = new ReservationDetailViewHolder(itemView,onItemClickListener, navController);

        return reservationDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationDetailViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.setData(reservation);
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public ReservationDetailAdapter(List<Reservation> reservationList) {
        Log.i(TAG, "예약상세어뎁터의 예약 내역들: "+reservationList);
        this.reservationList = reservationList;
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
