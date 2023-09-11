package com.example.secondminiproject.ui.wish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentWishListBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;

import java.util.Random;

public class WishListFragment extends Fragment {

    private static final String TAG = "WishListFragment";
    private FragmentWishListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWishListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();
        initBtnProductListImsi();
        initBtnProductDetailImsi();


        return binding.getRoot();
    }

    private void initBtnProductListImsi() {
        binding.btnToProductListImsi.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initBtnProductDetailImsi() {
        binding.btnToProductDetailImsi.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_detail);
        });
    }

    private void initRecyclerView() {
        //수직방향으로 1라인에 1개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        //2d열로 출력할때
        /* GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);*/
        binding.recyclerViewWish.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        WishAdapter wishAdapter = new WishAdapter();

        //데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=17; i++){
            Wish wishProduct = new Wish();
            wishProduct.setProductName("Title "+i);

            //이미지명, resource의 어디에있는지, 패키지명("com.example.myapplication"  or getApplication().getPackageName() ) 을 넣어야함.
            wishProduct.setProductImage(getResources().getIdentifier("photo" +(random.nextInt(17)+1), "drawable","com.example.secondminiproject"));
            wishProduct.setProductPrice(100000 * (random.nextInt(10)+1)); //10 -> 0~9 , 10 + 1 -> 1~10

            wishAdapter.addWishProduct(wishProduct);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerViewWish.setAdapter(wishAdapter);

    }

}