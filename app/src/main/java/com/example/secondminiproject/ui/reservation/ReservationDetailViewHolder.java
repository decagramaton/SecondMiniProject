package com.example.secondminiproject.ui.reservation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservationDetailViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReservationViewHolder";

    private NavController navController;

    // Title
    private ImageView foldingTitleImageview;
    private TextView foldingTitleName;
    private TextView foldingTitleProductPrice;


    // content
    private ImageView foldingContentImage;
    private TextView foldingContentTitle;
    private TextView foldingContentReservationState;
    private TextView foldingContentReservationNo;
    private TextView foldingContentReservationDate;
    private TextView foldingContentReservationStartDate;
    private TextView foldingContentReservationEndDate;
    private Button foldingContentReservationCancel;
    private Button foldingContentReviewWrite;

    // folding cell
    private com.ramotion.foldingcell.FoldingCell foldingCell;




/*  private TextView reservationListTitle;
    private TextView reservationListNumber;
    private TextView reservationListReservationDay;
    private TextView reservationListStartDay;
    private Button reservationListReservationState;

    private Button reservationListWriteReview;*/




    public ReservationDetailViewHolder(@NonNull View itemView, ReservationDetailAdapter.OnItemClickListener onItemClickListener, NavController reservationNavController) {
        super(itemView);

        this.navController = reservationNavController;


        this.foldingTitleImageview = itemView.findViewById(R.id.folding_title_imageview);
        this.foldingTitleName = itemView.findViewById(R.id.folding_title_name);
        this.foldingTitleProductPrice = itemView.findViewById(R.id.folding_title_product_price);

        this.foldingContentImage = itemView.findViewById(R.id.folding_content_image);
        this.foldingContentTitle = itemView.findViewById(R.id.folding_content_title);
        this.foldingContentReservationState = itemView.findViewById(R.id.folding_content_reservation_state);
        this.foldingContentReservationNo = itemView.findViewById(R.id.folding_content_reservation_no);
        this.foldingContentReservationDate = itemView.findViewById(R.id.folding_content_reservation_date);
        this.foldingContentReservationStartDate = itemView.findViewById(R.id.folding_content_reservation_start_date);
        this.foldingContentReservationEndDate = itemView.findViewById(R.id.folding_content_reservation_end_date);
        this.foldingContentReservationCancel = itemView.findViewById(R.id.folding_content_reservation_cancel);
        this.foldingContentReviewWrite = itemView.findViewById(R.id.folding_content_review_write);

        this.foldingCell = itemView.findViewById(R.id.folding_cell);

        initFolding();


        /*this.reservationListTitle = itemView.findViewById(R.id.reservation_list_title);
        this.reservationListNumber = itemView.findViewById(R.id.reservation_list_number);
        this.reservationListReservationDay = itemView.findViewById(R.id.reservation_list_reservation_day);
        this.reservationListStartDay = itemView.findViewById(R.id.reservation_list_start_day);
        this.reservationListReservationState = itemView.findViewById(R.id.reservation_list_reservation_state);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        });

        navController = reservationNavController;

        this.reservationListWriteReview = itemView.findViewById(R.id.btn_reservation_list_write_review);
        this.reservationListWriteReview.setOnClickListener(v -> {
            Log.i(TAG, "리뷰 작성 버튼 클릭 로그");

            navController.navigate(R.id.dest_review_write);
        });*/
    }


    public void setData(Reservation reservation, Board productInfo){
        ProductService.loadImageByMediaName(productInfo.getProductNo(),"name_main",this.foldingTitleImageview);
        //this.foldingTitleImageview.setImageResource(R.drawable.osaka_2);
        this.foldingTitleName.setText(productInfo.getProductTitle());
        DecimalFormat df = new DecimalFormat("#,###");
        this.foldingTitleProductPrice.setText(String.valueOf(df.format(productInfo.getProductAdultPrice())));
        ProductService.loadImageByMediaName(productInfo.getProductNo(),"name_thumbnail",this.foldingContentImage);
        //this.foldingContentImage.setImageResource(R.drawable.osaka_3);
        this.foldingContentTitle.setText(productInfo.getProductTitle());
        String reservationState=null;
        if(reservation.getReservationState()==1){
            reservationState ="발권완료";
        }else if(reservation.getReservationState()==2){
            reservationState ="예약완료";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소중";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소완료";
        }
        this.foldingContentReservationState.setText(reservationState);
        this.foldingContentReservationNo.setText(String.valueOf(reservation.getReservationNo()));

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 (E)", Locale.KOREAN);
        Date reservationdate = new Date(reservation.getReservationDate());
        this.foldingContentReservationDate.setText(sdf.format(reservationdate));
        Date startdate = new Date(productInfo.getTourStartDate());
        this.foldingContentReservationStartDate.setText(sdf.format(startdate));
        Date enddate = new Date(productInfo.getTourEndDate());
        this.foldingContentReservationEndDate.setText(sdf.format(enddate));
        this.foldingContentReservationCancel.setOnClickListener(v->{
            Log.i(TAG, "예약 취소 이벤트 호출 발생");
        });
        Bundle bundle = new Bundle();
        bundle.putInt("productNo",productInfo.getProductNo());
        this.foldingContentReviewWrite.setOnClickListener(v->{
            this.navController.navigate(R.id.dest_review_write,bundle);
        });




/*        this.navController = reservation.getReservationNavController();
        this.reservationListTitle.setText(reservation.getProductName());
        this.reservationListNumber.setText(String.valueOf(reservation.getReservationNo()));
        this.reservationListReservationDay.setText(reservation.getImsiReservationDate());
        this.reservationListStartDay.setText(reservation.getStartDate());
        String reservationState=null;
        if(reservation.getReservationState()==1){
            reservationState ="발권완료";
        }else if(reservation.getReservationState()==2){
            reservationState ="예약완료";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소중";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소완료";
        }
        this.reservationListReservationState.setText(reservationState);*/
    }


    private void initFolding() {
        foldingCell.initialize(30, 1000, Color.DKGRAY, 2);

        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
            }
        });
    }


}
