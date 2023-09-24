package com.example.secondminiproject.ui.reservation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ReservationService;
import com.example.secondminiproject.service.ReviewService;
import com.example.secondminiproject.service.ServiceProvider;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private TextView foldingContentReservationStartDate;
    private TextView foldingContentReservationEndDate;
    private Button foldingContentReservationCancel;
    private Button foldingContentReviewWrite;
    private TextView foldingContentAdultNumber;
    private TextView foldingContentChildNumber;


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
        this.foldingContentReservationStartDate = itemView.findViewById(R.id.folding_content_reservation_start_date);
        this.foldingContentReservationEndDate = itemView.findViewById(R.id.folding_content_reservation_end_date);
        this.foldingContentReservationCancel = itemView.findViewById(R.id.folding_content_reservation_cancel);
        this.foldingContentReviewWrite = itemView.findViewById(R.id.folding_content_review_write);
        this.foldingContentAdultNumber = itemView.findViewById(R.id.folding_content_adult_number);
        this.foldingContentChildNumber = itemView.findViewById(R.id.folding_content_child_number);

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
            reservationState ="예약완료";
        }else if(reservation.getReservationState()==2){
            reservationState ="발권완료";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소중";
        }else if(reservation.getReservationState()==4){
            reservationState ="취소완료";
        }
        this.foldingTitleProductPrice.setText(productInfo.getProductAdultPrice());
        this.foldingContentReservationState.setText(reservationState);
        this.foldingContentReservationNo.setText(String.valueOf(reservation.getReservationNo()));
        this.foldingContentAdultNumber.setText(String.valueOf(reservation.getReservationAdultNumber()));
        this.foldingContentChildNumber.setText(String.valueOf(reservation.getReservationChildNumber()));

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 (E)", Locale.KOREAN);
        Date startdate = new Date(productInfo.getTourStartDate());
        this.foldingContentReservationStartDate.setText(sdf.format(startdate));
        Date enddate = new Date(productInfo.getTourEndDate());
        this.foldingContentReservationEndDate.setText(sdf.format(enddate));
        if(reservation.getReservationState()!=1){
            this.foldingContentReservationCancel.setEnabled(false);
            this.foldingContentReservationCancel.setTextColor(Color.GRAY);
        }else{
            this.foldingContentReservationCancel.setTextColor(Color.argb(250,30,86,160));
            this.foldingContentReservationCancel.setOnClickListener(v->{
                AlertDialog alertDialog =new AlertDialog.Builder(v.getContext())
                        .setTitle("예약을 취소하시겠습니까?")
                        .setNegativeButton("취소",((dialog, which) -> {
                            Log.i(TAG, "예약 취소를 취소하는 이벤트 호출 ");
                        }))
                        .setPositiveButton("예약취소",((dialog, which) -> {
                            Log.i(TAG, "예약 취소 버튼 클릭 ");
                            ReservationService reservationService = ServiceProvider.getReservationService(navController.getContext());
                            Call<Void> call = reservationService.reservationCancel(reservation.getReservationNo(),Integer.parseInt(AppKeyValueStore.getValue(navController.getContext(), "userNo")));
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    foldingContentReservationState.setText("취소중");
                                    foldingContentReservationCancel.setTextColor(Color.GRAY);
                                    foldingContentReservationCancel.setEnabled(false);
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }))
                        .create();
                alertDialog.show();
            });
        }

        Bundle bundle = new Bundle();
        Log.i(TAG, "예약번호가 잇는가??: "+reservation.getReservationNo());
        bundle.putInt("productNo",productInfo.getProductNo());
        this.foldingContentReviewWrite.setOnClickListener(v->{
                if(reservation.getReservationState()==3 || reservation.getReservationState()==4){
                    AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle("예약 취소시 리뷰 작성이 불가합니다.")
                            .setPositiveButton("닫기",((dialog, which) -> {
                                Log.i(TAG, "리뷰 작성 불가 메세지 출력 ");
                            }))
                            .create();
                    alertDialog.show();
                }else {
                    ReviewService reviewService = ServiceProvider.getReviewService(navController.getContext());
                    Call<Integer> call = reviewService.checkReview(reservation.getReservationNo());
                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            Integer reviewCount = response.body();
                            Log.i(TAG, "리뷰 개수는 ?? : "+reviewCount);
                            if(reviewCount == 0){
                                Toast toast = new Toast(navController.getContext());
                                toast.setText("리뷰작성페이지로 이동합니다");
                                toast.show();
                                bundle.putInt("reservationNo",reservation.getReservationNo());
                                navController.navigate(R.id.dest_review_write, bundle);
                            } else if (reviewCount ==1 ) {
                                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                                        .setTitle("이미 작성한 리뷰가 있습니다.")
                                        .setPositiveButton("확인",(dialog, which) -> {
                                            Log.i(TAG, "onResponse: 굳");
                                        })
                                        .create();
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });

                }
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
