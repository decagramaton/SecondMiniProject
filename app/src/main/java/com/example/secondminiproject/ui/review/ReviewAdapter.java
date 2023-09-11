package com.example.secondminiproject.ui.review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private List<Reservation> reservationList = new ArrayList<>();

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View dateView = layoutInflater.inflate(R.layout.fragment_review_list2, parent, false);
        //괄호안에 inflater 넣어야함
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(dateView);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        Reservation reservation = reservationList.get(position);
        //홀더에 데이터를 세팅해준다.
        holder.setData(reservation);
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void addReservation(Reservation reservation){
        reservationList.add(reservation);
    }
}
