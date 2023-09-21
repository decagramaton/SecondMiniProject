package com.example.secondminiproject.ui.review;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewList3Binding;
import com.example.secondminiproject.databinding.FragmentReviewListBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
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
    private FragmentReviewListBinding fragmentReviewListBinding;
    private NavController navController;
    private  androidx.fragment.app.FragmentActivity reviewActivity;
    private LifecycleOwner lifecycleOwner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        fragmentReviewListBinding = FragmentReviewListBinding.inflate(inflater);

        initRecyclerView();

        reviewActivity = getActivity();

        this.lifecycleOwner = getViewLifecycleOwner();


        return fragmentReviewListBinding.getRoot();
    }


    private void initRecyclerView() {
        //수직방향으로 1라인에 1개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        //2d열로 출력할때
        /* GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);*/
        fragmentReviewListBinding.reviewListRecyclerView.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        ReviewAdapter reviewAdapter = new ReviewAdapter();
        reviewAdapter.setAppCompatActivityAndNavcontroller(reviewActivity,navController,lifecycleOwner);

        //데이터 받아와서 어뎁터에 설정
        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        //로그인 연결시 DB랑 연동
        Call<List<Review>> call = reviewService.getReviewListByUserNo(Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo")));

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> ReviewList = response.body();

                reviewAdapter.setList(ReviewList);
                fragmentReviewListBinding.reviewListRecyclerView.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });

        //리사이클러뷰에 어댑터 설정
        fragmentReviewListBinding.reviewListRecyclerView.setAdapter(reviewAdapter);

        fragmentReviewListBinding.reviewListRecyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if((!v.canScrollVertically(-1))){
                fragmentReviewListBinding.btnReviewListGoListTop.hide();
            }else {
                fragmentReviewListBinding.btnReviewListGoListTop.show();
            }

        });

        fragmentReviewListBinding.btnReviewListGoListTop.setOnClickListener(v -> {
            fragmentReviewListBinding.reviewListRecyclerView.scrollToPosition(0);
        });


    }

}