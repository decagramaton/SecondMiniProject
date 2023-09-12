package com.example.secondminiproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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

import com.example.secondminiproject.MainActivity;
import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.ui.home.HomeAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Random;

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

        //상단 배너
        initPagerView();

        //카테고리(나라별)
        initCategoryJapan();
        initCategoryHongkong();
        initCategoryTaiwan();
        initCategoryMongolia();
        initCategoryNorway();
        initCategoryGreece();
        initCategoryKeyna();


        return binding.getRoot();
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
        binding.btnHomeCategoryTaiwan.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryMongolia() {
        binding.btnHomeCategoryMongolia.setOnClickListener(v -> {
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

    private void initCategoryKeyna() {
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

                String mid = AppKeyValueStore.getValue(getContext(), "mid");
                if(mid == null) {
                    menu.findItem(R.id.item_home_login).setVisible(true);
                    menu.findItem(R.id.item_home_logout).setVisible(false);
                    menu.findItem(R.id.item_home_my_page).setVisible(false);
                } else {
                    menu.findItem(R.id.item_home_login).setVisible(false);
                    menu.findItem(R.id.item_home_logout).setVisible(true);
                    menu.findItem(R.id.item_home_my_page).setVisible(true);
                }


            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                // Step2. 아이콘 별 이벤트 처리
                if(menuItem.getItemId() == R.id.item_home_search){

                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_login) {
                    navController.navigate(R.id.action_dest_home_to_dest_login);
                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_logout) {
                    AppKeyValueStore.remove(getContext(), "mid");
                    AppKeyValueStore.remove(getContext(), "mpassword");
                    getActivity().invalidateMenu();
                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_my_page) {
                    navController.navigate(R.id.action_dest_home_to_dest_my_page);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        HomeAdapter homeAdapter = new HomeAdapter();

        // Step3. Data를 얻고, Adapter에 설정
        // 향후, DB 코드로 변환 필요, 지금은 실습을 위해 더미 데이터 생성
        Random random = new Random();
        for(int i=1; i<=100; i++){
            Product product = new Product();
            product.setPno(i);
            product.setTitle("Title"+i);
            product.setSubTitle("SubTitle"+i);
            product.setContent("photo"+i+" description");
            product.setImage(getResources().getIdentifier("photo"+ (random.nextInt(17)+1), "drawable", getContext().getPackageName()));
            product.setPrice(1000 * (random.nextInt(10)+1));
            product.setRating(random.nextInt(5)+1);
            product.setRatingCountByProduct(10 * (random.nextInt(10)+1));
            homeAdapter.addProduct(product);
        }

        // Step4. RecyclerView에 Adapter 설정
        binding.recyclerView.setAdapter(homeAdapter);

        //항목을 클릭했을때 콜백 객체를 등록
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "빔 항목 클릭 됨 ");
                //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                Product recommendProduct = homeAdapter.getItem(position);
                Bundle args = new Bundle();
                //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                args.putSerializable("recommendProduct", recommendProduct);
                navController.navigate(R.id.dest_product_detail,args);
            }
        });
    }

    private void initPagerView() {
        HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(this.getActivity());
        binding.homeBanner.setAdapter(homeBannerAdapter);

        //이게 있어야 배너광고의 점이 보임
       /* TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayout, binding.homeBanner, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();*/
    }


}