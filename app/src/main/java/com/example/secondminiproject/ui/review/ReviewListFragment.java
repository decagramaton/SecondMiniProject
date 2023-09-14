package com.example.secondminiproject.ui.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewListBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.ui.reservation.ReservationAdapter;

import java.util.Random;


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
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        ReviewAdapter reviewAdapter = new ReviewAdapter();

        //데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=17; i++){
            Reservation reservation = new Reservation();

            reservation.setImsiReservationDate("23.09.08");

            //productAdapter.addProduct(product);
            reviewAdapter.addReservation(reservation);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerView.setAdapter(reviewAdapter);
    }
}