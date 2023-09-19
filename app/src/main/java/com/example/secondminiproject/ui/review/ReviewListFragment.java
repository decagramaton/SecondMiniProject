package com.example.secondminiproject.ui.review;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewList3Binding;
import com.example.secondminiproject.databinding.FragmentReviewListBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ReviewService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.ui.reservation.ReservationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewListFragment extends Fragment {

    private static final String TAG = "ReviewListFragment";
    private FragmentReviewListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        binding = FragmentReviewListBinding.inflate(inflater);

        //initBtnReviewUpdate();
        initRecyclerView();


        return binding.getRoot();
    }


    /*private void initBtnReviewUpdate() {
        binding.btnReviewListReviewUpdate.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_review_list_to_dest_review_update);
        });
    }*/

    private void initRecyclerView() {
        //수직방향으로 1라인에 1개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        //2d열로 출력할때
        /* GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);*/
        binding.reviewListRecyclerView.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        ReviewAdapter reviewAdapter = new ReviewAdapter();

        //데이터 받아와서 어뎁터에 설정
        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        //로그인 연결시 DB랑 연동
        Call<List<Review>> call = reviewService.getReviewListByUserNo(3);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> ReviewList = response.body();

                reviewAdapter.setList(ReviewList);
                binding.reviewListRecyclerView.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
        /*Random random = new Random();
        for(int i=1; i <=9; i++){
            Review review = new Review();
            review.setReviewTitle( i + " 번 [상품명] 이 들어갈 곳");
            review.setStartDate("23.06.0"+i);
            review.setEndDate("23.06.1"+i);
            review.setReviewRating(i%5+1);
            review.setReviewContent(i +" 번 상품 리뷰에 대한 리뷰 내용");

            //productAdapter.addProduct(product);
            reviewAdapter.addReview(review);
        }*/

        //리사이클러뷰에 어댑터 설정
        binding.reviewListRecyclerView.setAdapter(reviewAdapter);

        binding.reviewListRecyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if((!v.canScrollVertically(-1))){
                binding.btnReviewListGoListTop.hide();
            }else {
                binding.btnReviewListGoListTop.show();
            }

        });

        binding.btnReviewListGoListTop.setOnClickListener(v -> {
            binding.reviewListRecyclerView.scrollToPosition(0);
        });
    }
}