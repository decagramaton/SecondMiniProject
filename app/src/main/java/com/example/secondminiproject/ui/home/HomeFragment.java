package com.example.secondminiproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.ui.home.ProductAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        // Header AppBar 생성
        initHeaderMenu();

        // 추천 여행 상품 목록 출력
        initRecyclerView();

        //배너
        initPagerView();


        //카테고리(나라별)
        initCategoryJeju();
        initCategoryJapan();
        initCategoryHongkong();
        initCategoryTaiwan();
        initCategoryChina();
        initCategoryMongolia();
        initCategoryHawaii();
        initCategoryParis();
        initCategoryEngland();
        initCategoryNorway();
        initCategoryGreece();
        initCategoryItaly();
        initCategoryGermany();
        initCategoryKenya();


        return binding.getRoot();
    }

    private void initCategoryGermany() {
        binding.btnHomeCategoryGermany.setOnClickListener(v ->{
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryItaly() {
        binding.btnHomeCategoryItaly.setOnClickListener(v ->{
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryEngland() {
        binding.btnHomeCategoryEngland.setOnClickListener(v ->{
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryParis() {
        binding.btnHomeCategoryParis.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryHawaii() {
        binding.btnHomeCategoryHawaii.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryChina() {
        binding.btnHomeCategoryChina.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryJeju() {
        binding.btnHomeCategoryJeju.setOnClickListener( v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryJapan() {
        binding.btnHomeCategoryJapan.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryHongkong() {
        binding.btnHomeCategoryHongkong.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryTaiwan() {
        binding.btnHomeCategoryTaipei.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryMongolia() {
        binding.btnHomeCategoryMongol.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryNorway() {
        binding.btnHomeCategoryNorway.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryGreece() {
        binding.btnHomeCategoryGreece.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryKenya() {
        binding.btnHomeCategoryKenya.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }


    private void initHeaderMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                // Step1. Menu Layout 인플레이션화.
                menuInflater.inflate(R.menu.home_head_menu, menu);


            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                // Step2. 아이콘 별 이벤트 처리
                if(menuItem.getItemId() == R.id.item_home_search){

                    return true;
                }

                return false;
            }
        };

        // Step3. Activity에 AppBar 출력 설정
        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        ProductAdapter productAdapter = new ProductAdapter();

        // Step3. Data를 얻고, Adapter에 설정
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> BoardList = response.body();

                productAdapter.setList(BoardList);
                binding.recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, t.toString());
            }
        });

        //항목을 클릭했을때 콜백 객체를 등록
        productAdapter.setOnItemClickListener(new com.example.secondminiproject.ui.home.ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Board board = productAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }
    private void initPagerView() {
        HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(getActivity());
        binding.homeBanner.setAdapter(homeBannerAdapter);

        /*//이게 있어야 배너광고의 점이 보임
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();*/
    }

}