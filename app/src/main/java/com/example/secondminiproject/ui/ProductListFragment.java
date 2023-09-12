package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductListBinding;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
             @Override
             public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                 List<Board> BoardList = response.body();

                 //어댑터에 데이터 세팅
                 productAdapter.setList(BoardList);

                 //RecyclerView에 어댑터 세팅
                 binding.recyclerView.setAdapter(productAdapter);
             }

             @Override
             public void onFailure(Call<List<Board>> call, Throwable t) {

             }
         });


        // Step4. RecyclerView에 Adapter 설정
        binding.recyclerView.setAdapter(productAdapter);
    }
}