package com.example.secondminiproject.ui.reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private static final String TAG = "ReservationAdapter";
    private List<Reservation> reservationList = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();
    private NavController navController;
    @NonNull
    @Override
    //데이터들을 가지고오는거
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View dateView = layoutInflater.inflate(R.layout.fragment_reservation_list_date, parent, false);
        //괄호안에 inflater 넣어야함
        ReservationViewHolder reservationViewHolder = new ReservationViewHolder(dateView);

        return reservationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        Reservation reservation = reservationList.get(position);

        String dateLists = reservation.getImsiReservationDate();

        //홀더에 데이터를 세팅해준다.
        holder.setData(dateLists);

        Log.i(TAG, "홀더에 데이터 세팅하고 여기 실행되?");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false
        );
        List<Reservation> reservations = reservationList;
        Log.i(TAG, "onBindViewHolder의 reservationList 는? :" +reservations);

        ReservationDetailAdapter reservationDetailAdapter = new ReservationDetailAdapter(reservations);
        holder.recyclerViewReservationListDate.setLayoutManager(linearLayoutManager);
        holder.recyclerViewReservationListDate.setAdapter(reservationDetailAdapter);

        reservationDetailAdapter.setOnItemClickListener(new ReservationDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "빔 항목 클릭 됨 ");
                //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                Reservation reservation = reservationDetailAdapter.getItem(position);
                Bundle args = new Bundle();
                //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                args.putSerializable("reservation", reservation);
                navController.navigate(R.id.action_dest_reservation_list_to_reservationDetailFragment,args);
            }
        });

    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void addReservation(Reservation reservation){
        this.navController = reservation.getReservationNavController();
        reservationList.add(reservation);
        dateList.add(reservation.getImsiReservationDate());
    }




}
