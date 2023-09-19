package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentPaymentBinding;
import com.example.secondminiproject.databinding.FragmentProductDetailBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ReservationService;
import com.example.secondminiproject.service.ServiceProvider;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentFragment extends Fragment {

    private static final String TAG = "PaymentFragment";
    private FragmentPaymentBinding binding;
    private NavController navController;
    private int productNo;
    private int userNo;
    private int adultNumber;
    private int childrenNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);


        initProductDataSetting();
        initUserDataSetting();
        initReservationInfo();
      /*  initBtnProductDetail();*/
        initBtnPayFinish();

        return binding.getRoot();
    }

    private void initUserDataSetting() {
        this.userNo =Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo")) ;
        Log.i(TAG, "userNo 유저번호는? : "+ userNo);
        //이름(한글)
        String userName = AppKeyValueStore.getValue(getContext(),"userKoName");
        binding.reservationName.setText(userName);
        //생년월일
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd");
        String userBirth = AppKeyValueStore.getValue(getContext(),"userBirth");
        binding.reservationBirth.setText(userBirth);
        //성별 체크
        String userGender = AppKeyValueStore.getValue(getContext(),"userGender");
        if(userGender.equals("남자") || userGender.equals("상남자")){
            binding.reservationSex.check(R.id.reservation_sex_male);
        }else{
            binding.reservationSex.check(R.id.reservation_sex_female);
        }
        //이름(영문)
        String userEnName = AppKeyValueStore.getValue(getContext(),"userEnName");
        binding.reservationENName.setText(userEnName);
        //휴대폰 번호
        String userPhone = AppKeyValueStore.getValue(getContext(),"userPhone");
        binding.reservationPhone.setText(userPhone);

    }

    private void initProductDataSetting() {
        Bundle bundle = getArguments();
        this.productNo = bundle.getInt("productNo");
        Log.i(TAG, "productNo 상품번호는? : "+ productNo);
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call< Board> call = productService.getProductByProductNo(productNo);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Board productInfo = response.body();
                binding.paymentProductTitle.setText(productInfo.getProductTitle());
                int days = (int) (productInfo.getTourEndDate()/100000000-productInfo.getTourStartDate()/100000000);
                binding.paymentProductTravelDays.setText(days-1+"박 "+days+"일");
                binding.paymentProductTransportation.setText(productInfo.getProductVehicle());
                Date startDate = new Date(productInfo.getTourStartDate());
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 (E)", Locale.KOREAN);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String startDateString = sdf.format(startDate);
                binding.paymentTravelStartDate.setText("(한국출발) "+startDateString +"\n(현지도착) "+startDateString);
                Date endDate = new Date(productInfo.getTourEndDate());
                String endDateString = sdf.format(endDate);
                binding.paymentTravelEndDate.setText("(현지출발) "+endDateString +"\n(한국도착) "+endDateString);
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });
    }

    private void initReservationInfo() {
        Bundle bundle = getArguments();
        this.productNo=bundle.getInt("productNo");
        DecimalFormat df = new DecimalFormat("#,###");
        binding.reservationAdultPrice.setText(String.valueOf(df.format(bundle.getInt("eachAdultPrice"))));
        binding.reservationChildPrice.setText(String.valueOf(df.format(bundle.getInt("eachChildPrice"))));
        this.adultNumber =bundle.getInt("adultNumber");
        binding.reservationAdultNumber.setText(String.valueOf(adultNumber));
        Log.i(TAG, "adultNumber 어른 숫자는? : "+ adultNumber);
        this.childrenNumber =bundle.getInt("childNumber");
        binding.reservationChildNumber.setText(String.valueOf(childrenNumber));
        Log.i(TAG, "childrenNumber 아이 숫자는? : "+ childrenNumber);
        binding.reservationTotalPrice.setText(String.valueOf(df.format(bundle.getInt("totalPrice"))));
    }

    private void initBtnPayFinish() {
        binding.btnPaymentMakeReservation.setOnClickListener(v -> {
            ReservationService reservationService = ServiceProvider.getReservationService(getContext());
            Call<Void> call = reservationService.setNewReservationInfo(userNo,productNo,adultNumber,childrenNumber);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                            .setTitle("예약이 완료 되었습니다.")
                            .setMessage("즐거운 여행이 되시길 바랍니다.")
                            .setPositiveButton("확인",((dialog, which) -> navController.navigate(R.id.dest_reservation_list)))
                            .create();
                    alertDialog.show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

            //navController.navigate(R.id.dest_reservation_list);
        });
    }
}