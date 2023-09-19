package com.example.secondminiproject.ui;

import android.os.Bundle;

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
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);


        initProductDataSetting();
        initReservationInfo();
      /*  initBtnProductDetail();*/
        initBtnPayFinish();

        return binding.getRoot();
    }

    private void initProductDataSetting() {
        Bundle bundle = getArguments();
        this.productNo = bundle.getInt("productNo");
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
        binding.reservationAdultNumber.setText(String.valueOf(bundle.getInt("adultNumber")));
        binding.reservationChildNumber.setText(String.valueOf(bundle.getInt("childNumber")));
        binding.reservationTotalPrice.setText(String.valueOf(df.format(bundle.getInt("totalPrice"))));
    }

    private void initBtnPayFinish() {
        binding.btnPaymentMakeReservation.setOnClickListener(v -> {
            /*Reservation reservation = new Reservation();
            String reservationName = binding.reservationName.getText().toString();
            reservation.setreser
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 (E)", Locale.KOREAN);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                String reservationBirth = String.valueOf(sdf.parse(binding.reservationBirth.getText().toString()));
                Log.i(TAG, "예약자 생년월일 : " + reservationBirth);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }*/

            navController.navigate(R.id.dest_reservation_list);
        });
    }

    /*private void initBtnProductDetail() {
        binding.btnPaymentProductDetail.setOnClickListener(v->{
            navController.popBackStack();
        });
    }*/
}