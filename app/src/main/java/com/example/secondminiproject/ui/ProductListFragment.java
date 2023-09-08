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
        //수직방향으로 한 라인에 한 개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        //두 줄로 리스트 나오게 함
        /*GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerView.setLayoutManager(layoutManager);*/

        //어댑터 생성
        ProductAdapter productAdapter = new ProductAdapter();


        //데이터 받아와서 어댑터에 설정
        Random random = new Random();
        for(int i=1; i<=100; i++) {
            Product product = new Product();
            product.setPno(i);
            product.setTitle("Title" + i);
            product.setSubTitle("Sub Title" + i);
            product.setContent("This is the photo" + i + "'s description. this place is beautiful. help me");
            product.setImage(getResources().getIdentifier("photo" + (random.nextInt(17) + 1), "drawable", getContext().getPackageName()));
            product.setPrice(1000000 * (random.nextInt(10)+1));
            product.setRating(random.nextInt(5) + 1);
            product.setRatingCountByProduct(10 * (random.nextInt(10) + 1));
            productAdapter.addProduct(product);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerView.setAdapter(productAdapter);
    }
}