package com.example.secondminiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.secondminiproject.databinding.ActivityMainBinding;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // NavController 설정
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        // Header AppBar 초기 설정
        initHeaderAppBar();

        // Bottom Navagation 초기 설정
        //NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        initBottomNav();


        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private void initBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            NavOptions navOptions = new NavOptions.Builder()
                    //.setPopUpTo(R.id.dest_review, false)
                    .setLaunchSingleTop(true)
                    .build();

            if (item.getItemId() == R.id.dest_home) {
                navController.navigate(R.id.dest_home, null, navOptions);
                return true;
            } else if (item.getItemId() == R.id.dest_product_list) {
                navController.navigate(R.id.dest_product_list, null, navOptions);
                return true;
            } else if (item.getItemId() == R.id.dest_wish_list) {
                navController.navigate(R.id.dest_wish_list, null, navOptions);
                return true;
            } else if (item.getItemId() == R.id.dest_my_page) {
                navController.navigate(R.id.dest_my_page, null, navOptions);
                return true;
            }
            return false;
        });
    }


    private void initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.dest_home){

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host, FragmentManager.findFragment(findViewById(R.id.dest_home)))
                            .commit();
                } else if (item.getItemId() == R.id.dest_product_list) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host, getSupportFragmentManager().findFragmentById(R.id.dest_product_list))
                            .commit();
                } else if (item.getItemId() == R.id.dest_my_page) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host, getSupportFragmentManager().findFragmentById(R.id.dest_my_page))
                            .commit();
                } else if (item.getItemId() == R.id.dest_wish_list) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host, getSupportFragmentManager().findFragmentById(R.id.dest_wish_list))
                            .commit();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_main).getActionView();
        searchView.setQueryHint("검색 내용을 입력하세요");
        searchView.setIconifiedByDefault(false);
        searchView.setBackgroundColor(990099);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("searchKeyword", query);
                navController.navigate(R.id.dest_product_list, bundle);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void initHeaderAppBar() {
        // Step1. Toolbar를 AppBar로 설정
        setSupportActionBar(binding.toolbar);

        // Step2. 그래프상에서 최상위 수준 대상을 지정(홈 아이콘으로 표시된 대상)
        AppBarConfiguration appBarConfiguration =  new AppBarConfiguration.Builder(navController.getGraph()).build();

        // Step3. Toolbar 기능 설정
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
    }
}