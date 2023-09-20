package com.example.secondminiproject.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private NavController navController;

    private Handler handler= new Handler();

    private TimerTask timerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        // 추천 여행 상품 목록 출력
        initNewRecyclerView();
        initrecommendRecyclerView();
        initTimeDealRecyclerView();
        initFamilyRecyclerView();
        initHealingRecyclerView();


        //배너
        initPagerView();

        //카테고리(나라별) 초기화
        initBtnCategory();

        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        timerTask.cancel();
        handler.removeCallbacksAndMessages(null);
    }

    private void initBtnCategory() {
        setCountry(binding.btnHomeCategoryJeju, binding.btnHomeCategoryJeju.getContentDescription().toString());
        setCountry(binding.btnHomeCategoryHongkong, "홍콩");
        
        setCountry(binding.btnHomeCategoryTaipei, "태국");
        setCountry(binding.btnHomeCategoryJapan, "일본");
        
        setCountry(binding.btnHomeCategoryChina, "중국");
        setCountry(binding.btnHomeCategoryMongol, "몽골");
        
        setCountry(binding.btnHomeCategoryHawaii, "하와이");
        setCountry(binding.btnHomeCategoryFrance, "프랑스");

        setCountry(binding.btnHomeCategoryEngland, "영국");
        setCountry(binding.btnHomeCategoryNorway, "노르웨이");

        setCountry(binding.btnHomeCategoryGreece, "그리스");
        setCountry(binding.btnHomeCategoryItaly, "이탈리아");

        setCountry(binding.btnHomeCategoryGermany, "독일");
        setCountry(binding.btnHomeCategoryKenya, "케냐");

    }

    private void setCountry(View view, String country) {
        view.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", country);
            navController.navigate(R.id.dest_product_list, bundle);
        });
    }

    /**
     * 신규 여행 패키지 목록 메소드
     */
    private void initNewRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.newRecyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        NewPackageAdapter newPackageAdapter = new NewPackageAdapter();

        // Step3. Data를 얻고, Adapter에 설정
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                newPackageAdapter.setList(BoardList);
                binding.newRecyclerView.setAdapter(newPackageAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        //항목을 클릭했을때 콜백 객체를 등록
        newPackageAdapter.setOnItemClickListener(new NewPackageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = newPackageAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }

    /**
     * 추천 여행 패키지 목록 메소드
     */
    private void initrecommendRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recommendRecyclerView.setLayoutManager(linearLayoutManager);

        RecommendPackageAdapter recommendPackageAdapter = new RecommendPackageAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                recommendPackageAdapter.setList(BoardList);
                binding.recommendRecyclerView.setAdapter(recommendPackageAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        //항목을 클릭했을때 콜백 객체를 등록
        recommendPackageAdapter.setOnItemClickListener(new RecommendPackageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = recommendPackageAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }


    /**
     * 타임 특가 여행 패키지 목록 메소드
     */
    private void  initTimeDealRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.timeDealRecyclerView.setLayoutManager(linearLayoutManager);

        TimeDealPackageAdapter timeDealPackageAdapter = new TimeDealPackageAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                timeDealPackageAdapter.setList(BoardList);
                binding.timeDealRecyclerView.setAdapter(timeDealPackageAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        timeDealPackageAdapter.setOnItemClickListener(new TimeDealPackageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = timeDealPackageAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }


    /**
     *
     */
    private void  initFamilyRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.familyRecyclerView.setLayoutManager(linearLayoutManager);

        NewPackageAdapter newPackageAdapter = new NewPackageAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                newPackageAdapter.setList(BoardList);
                binding.familyRecyclerView.setAdapter(newPackageAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        newPackageAdapter.setOnItemClickListener(new NewPackageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = newPackageAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }

    /**
     *
     */
    private void  initHealingRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.timeDealRecyclerView.setLayoutManager(linearLayoutManager);

        NewPackageAdapter newPackageAdapter = new NewPackageAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                newPackageAdapter.setList(BoardList);
                binding.timeDealRecyclerView.setAdapter(newPackageAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        newPackageAdapter.setOnItemClickListener(new NewPackageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = newPackageAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }





    private void initPagerView() {
        HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(getActivity());
        binding.homeBanner.setAdapter(homeBannerAdapter);

        // Timer를 사용하여 이미지 자동 슬라이드 시작
        startAutoSlide();
    }
    //Timer: 일정 시간간격으로 작업을 실행하기 위함
    private Timer timer;
    //현재 페이지 번호를 받기위함
    private int currentPage = 0;
    private void startAutoSlide(){
        //Runnable update: 타이머가 실행될 때마다 호출되는 Runnable 객체
        //Runnable 내부에서 현재 페이지 번호를 확인하고, 페이지 번호를 증가시킴
        final Runnable update = () -> {
            //현재 페이지가 5에 도달하면 다시 0으로 초기화
            if(currentPage == 5){
                currentPage = 0;
            }
            binding.homeBanner.setCurrentItem(currentPage++, true);
        };
        timer = new Timer();

        //Timer를 사용하여 작업을 예약
        timer.schedule(timerTask =new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(update);
            }
        },3000,3000);
    }

}