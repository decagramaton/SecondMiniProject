package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.telephony.ClosedSubscriberGroupInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.WishService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    private static final String TAG = "ProductDetailFragment";
    private FragmentProductDetailBinding binding;
    private NavController navController;
    private BottomSheetDialogFragment bottomSheet;
    private int productNo ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);


        initSetWish();
        initSetData();
        initBtnMakeReservation();
        //initVideo();
        initPagerView();

        initBtnWish();

        hideBottomNavigation(true);

        return binding.getRoot();

    }

    private void initSetWish() {
        WishService wishService = ServiceProvider.getWishService(getContext());
        Bundle bundle = getArguments();
        this.productNo = bundle.getInt("productNo");

        String targetUserNo = AppKeyValueStore.getValue(getContext(),"userNo");

        if(targetUserNo != null){
            int userNo = Integer.parseInt(targetUserNo);
            Call<Integer> call1 = wishService.checkWishByUserNoAndProductNo(productNo,userNo); //추후 DB연결되면 번들이랑 AppDataStore로 받아오자
            call1.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    Integer isWish = response.body();
                    if(isWish ==0){
                        binding.btnProductDetailWish.setChecked(false);
                    } else if (isWish ==1) {
                        binding.btnProductDetailWish.setChecked(true);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        } else {
            binding.btnProductDetailWish.setChecked(false);
        }

    }

    private void initSetData() {
        ProductService productService = ServiceProvider.getProductService(getContext());
        Bundle bundle = getArguments();
        Log.i(TAG, "여기에 들어가는 productNo  상품번호는 :  "+ productNo);
        Call<Board> call = productService.getProductByProductNo(productNo);
        call.enqueue(new Callback<Board>() {
            private static final String TAG = "ProductDetailFragment";
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Log.i(TAG, " 받아오는 상품 번호 : "+ productNo);
                Board productInfo = response.body();
                //binding.productDetailRating.setRating(Math.round(productInfo.getAverageRating()));
                Log.i(TAG, "productInfo.getReviewList() : "+productInfo.getReviewList());
                if(productInfo.getReviewList()==null){
                    binding.productDetailReviewCount.setText(String.valueOf(0));
                }else {
                    binding.productDetailReviewCount.setText(String.valueOf(productInfo.getReviewList().size()));
                }
                binding.productDetailReservationCount.setText(String.valueOf(productInfo.getProductReservationNumber()));
                binding.productDetailTitle.setText(productInfo.getProductTitle());
                DecimalFormat df = new DecimalFormat("#,###");
                binding.productDetailPrice.setText(String.valueOf(df.format(productInfo.getProductAdultPrice())));
                int days = (int) (productInfo.getTourEndDate()/100000000-productInfo.getTourStartDate()/100000000);
                binding.productDetailTravelDays.setText(days+"박 "+(days+1)+"일");
                binding.productDetailTravelTransportation.setText(productInfo.getProductVehicle());
                binding.productDetailTravelArea.setText(productInfo.getProductVisitPlace());
                binding.productDetailLeftTicket.setText("몇장?");
                binding.productDetailTravelContent.setText(productInfo.getProductContent());
                Log.i(TAG, "비디오 url : "+productInfo.getProductVideoUrl());
                if(productInfo.getProductVideoUrl()==null){
                    binding.productDetailVideoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener(){
                        @Override
                        public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                            youTubePlayer.loadVideo("uCvqPcqdxFA",0);
                        }
                    });
                }else{
                    String videoId =productInfo.getProductVideoUrl();
                    binding.productDetailVideoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener(){
                        @Override
                        public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                            youTubePlayer.loadVideo(videoId,0);
                        }
                    });

                }


            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });
    }


    private void initBtnWish() {
        binding.btnProductDetailWish.setOnClickListener(v -> {
            startShakeAnimation();
            WishService wishService = ServiceProvider.getWishService(getContext());
            int userNo =Integer.parseInt( AppKeyValueStore.getValue(getContext(),"userNo"));
            //추후에 올바른 정보 삽입
            Call<Void> call =  wishService.clickWishBtn(productNo,userNo);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    initSetWish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });
    }

    private void startShakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake);
        // 이미지 버튼에 애니메이션을 적용.
        CheckBox productDetailwish = binding.btnProductDetailWish;
        productDetailwish.startAnimation(shake);
    }

    private void initBtnMakeReservation() {

        binding.btnProductDetailPayment.setOnClickListener(v -> {

            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                ProductDetailBottomSheetFragment productDetailBottomSheetFragment = new ProductDetailBottomSheetFragment();
                //추후 상품 번호 들어오면 번들로 받자.
                productDetailBottomSheetFragment.getProductNo(productNo);
                productDetailBottomSheetFragment.show(getActivity().getSupportFragmentManager(), "productDetailBottomSheetFragment");
            }
        });
    }

    private void initPagerView() {
        DetailBannerAdapter detailBannerAdapter = new DetailBannerAdapter(getActivity());
        detailBannerAdapter.setProductNo(productNo);
        binding.detailBanner.setAdapter(detailBannerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigation(false);
    }

    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottom_navigation);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

}