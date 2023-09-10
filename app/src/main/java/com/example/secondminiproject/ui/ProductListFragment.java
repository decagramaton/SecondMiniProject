package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductListBinding;
import com.example.secondminiproject.dto.Product;


import java.util.Random;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private FragmentProductListBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnProductDetail();
        initBtnHome();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initBtnProductDetail() {
        binding.btnProductListProductDetail.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_product_list_to_dest_product_detail);
        });
    }

    private void initBtnHome() {
        binding.btnProductListHome.setOnClickListener(v->{
            navController.popBackStack();
        });
    }

    private void initRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        ProductAdapter productAdapter = new ProductAdapter();

        // Step3. Data를 얻고, Adapter에 설정
        // 향후, DB 코드로 변환 필요, 지금은 실습을 위해 더미 데이터 생성
        Random random = new Random();
        for(int i=1; i<=100; i++){
            Product product = new Product();
            product.setPno(i);
            product.setTitle("AirBnB"+i);
            product.setSubTitle("떠나요"+i);
            product.setContent("photo"+i+"DB에서 받아와야돼");
            product.setImage(getResources().getIdentifier("photo"+ (random.nextInt(17)+1), "drawable", "com.example.secondminiproject"));
            product.setPrice(1000 * (random.nextInt(10)+1));
            product.setRating(random.nextInt(5)+1);
            product.setRatingCountByProduct(10 * (random.nextInt(10)+1));
            productAdapter.addProduct(product);
        }

        // Step4. RecyclerView에 Adapter 설정
        binding.recyclerView.setAdapter(productAdapter);
    }
}