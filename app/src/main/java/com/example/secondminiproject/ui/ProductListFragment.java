package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.MainActivity;
import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductListBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.WishService;
import com.example.secondminiproject.ui.wish.WishAdapter;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private FragmentProductListBinding binding;
    private NavController navController;
    private ProductAdapter productAdapter = new ProductAdapter();
    boolean position_flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);


        initRecyclerView();
        initBtnCategory();
        initSetWish();

        return binding.getRoot();
    }

    private void initBtnCategory() {
        setCountry(binding.productListJeju, "제주");
        setCountry(binding.productListJapen, "일본");

        setCountry(binding.productListGermany, "독일");
        setCountry(binding.productListChina, "중국");

        setCountry(binding.productListHawaii, "하와이");
        setCountry(binding.productListFrance, "프랑스");

        setCountry(binding.productListEngland, "영국");
        setCountry(binding.productListItaly, "이탈리아");

        setCountry(binding.productListHongkong, "홍콩");
        setCountry(binding.productListKenya, "케냐");

        setCountry(binding.productListTaiwan, "대만");
        setCountry(binding.productListMongol, "몽골");

        setCountry(binding.productListNorway, "노르웨이");
        setCountry(binding.productListGreece, "그리스");
    }

    private void setCountry(View view, String country) {
        view.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", country);
            navController.navigate(R.id.dest_product_list, bundle);
        });
    }

    private void initRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어댑터 생성
        Bundle bundle = getArguments();
        if(bundle != null) {

            String searchKeyword = bundle.getString("searchKeyword");
            String category = bundle.getString("category");

            if(searchKeyword != null) {
                settingProductAdapterData("searchKeyword", searchKeyword);
                bundle.remove("searchKeyword");
            } else if(category != null) {
                settingProductAdapterData("category", category);
                bundle.remove("category");
            }
        } else {
            initProductAdapterData();
        }

        //항목을 클릭했을때 콜백 객체를 등록
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Board board = productAdapter.getItem(position);
                Bundle args = new Bundle();
                args.putInt("productNo", board.getProductNo());

                Bundle bundle = ((MainActivity)getActivity()).getBundle();
                ArrayList<String> recentProductList = bundle.getStringArrayList("recentProductList");
                recentProductList.add(String.valueOf(board.getProductNo()));
                bundle.remove("recentProductList");
                bundle.putStringArrayList("recentProductList", recentProductList);
                ((MainActivity)getActivity()).setBundle(bundle);

                navController.navigate(R.id.action_dest_product_list_to_dest_product_detail,args);
            }
        });

        productAdapter.setOnBtnWishClickListener(new ProductAdapter.OnBtnWishClickListener() {
            @Override
            public void onItemClick(View itemView, int position, int productNo) {
                WishService wishService = ServiceProvider.getWishService(getContext());
                //회원정보 받아오기
                int userNo = Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo"));
                //productNo, userNo의 값과 일치하는 wishList 찾기
                Call<Void> call =  wishService.clickWishBtn(productNo,userNo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //하트 색깔 여부를 다시 출력
                        initSetWish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        binding.recyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if((!v.canScrollVertically(-5))){
                binding.btnProductListGoTop.hide();
            }else {
                binding.btnProductListGoTop.show();
            }
            position_flag = false;
        });

        binding.btnProductListGoTop.setOnClickListener(v -> {
            binding.recyclerView.scrollToPosition(0);
        });
    }


    private void settingProductAdapterData(String requestType, String value) {
        ProductService productService = ServiceProvider.getProductService(getContext());

        if(requestType.equals("searchKeyword")) {
            Call<List<Board>> call = productService.getProductListBySearchKeyword(value);

            call.enqueue(new Callback<List<Board>>() {
                @Override
                public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                    List<Board> boardList = response.body();
                    productAdapter.setList(boardList);
                    binding.recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<List<Board>> call, Throwable t) {
                    Log.i(TAG, "검색 결과 통신 실패 이벤트 호출");
                }
            });

        } else if (requestType.equals("category")) {
            Call<List<Board>> call = productService.getProductListByCategory(value);
            call.enqueue(new Callback<List<Board>>() {
                @Override
                public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                    List<Board> boardList = response.body();
                    productAdapter.setList(boardList);
                    binding.recyclerView.setAdapter(productAdapter);
                }
                @Override
                public void onFailure(Call<List<Board>> call, Throwable t) {
                    Log.i(TAG, "검색 결과 통신 실패 이벤트 호출");
                }
            });
        }
    }

    private void initProductAdapterData(){
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList("all");
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();
                Log.i(TAG, "전체 목록을 조회하는 callBack 호출 - initRecyclerView()");
                //어댑터에 데이터 세팅
                productAdapter.setList(BoardList);

                //RecyclerView에 어댑터 세팅
                binding.recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {

            }
        });
    }
    private void initSetWish() {
        WishService wishService = ServiceProvider.getWishService(getContext());
        Call<Integer> call1 = wishService.checkWishByUserNoAndProductNo(1,1); //추후 DB연결되면 번들이랑 AppDataStore로 받아오자
        call1.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer isWish = response.body();
                /*if(isWish ==0){
                    binding.btnWish.setChecked(false);
                } else if (isWish ==1) {
                    binding.btnWish.setChecked(true);
                }*/
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}