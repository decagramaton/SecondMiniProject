package com.example.secondminiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.secondminiproject.databinding.ActivityMainBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private NavController navController;
    private SharedPreferences SPreferences;
    private Bundle bundle;
    private final String NameSPreferences = "Day";
    private String strSDFormatDay = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = new Bundle();
        bundle.putStringArrayList("recentProductList", new ArrayList<String>());

        /*//현재시간
        long today = System.currentTimeMillis();
        //Date에 저장
        Date date = new Date(today);
        //원하는 형식으로 가져오기
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String getTime = sdf.format(date);

        //현재시간에 하루 더하기
        long nextDay = System.currentTimeMillis()+ 86400000;

        // SharedPreferences 획득
        SharedPreferences SPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String strSPreferencesDay = SPreferences.getString(NameSPreferences, "0");*/

        //광고창 띄우기
        /*if((Integer.parseInt(getTime) - Integer.parseInt(strSPreferencesDay)) != 0)
            showAdvertiseDialog();*/

        // NavController 설정
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();

        //최초 앱 실행 확인
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isInit = sharedPreferences.getBoolean("isInit", false);
        if(!isInit) {
            Log.i(TAG,"최초실행");
        }

        // Header AppBar 초기 설정
        initHeaderAppBar();

        //ToolBar title 이미지로 변경
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_mainlogo_neomutour);

        // Bottom Navagation 초기 설정
        //NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        initBottomNav();
        showAdvertiseDialog();

        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    //최초 실행시 광고창 실행
    private void showAdvertiseDialog(){

        View view = getLayoutInflater().inflate(R.layout.advertisement_main, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
                alertDialog.show();


         Button btnAdCloseDay = view.findViewById(R.id.btn_ad_close_day);
            btnAdCloseDay.setOnClickListener(v -> {


                Log.i(TAG, "showAdvertiseDialog: Close today");
                alertDialog.dismiss();
            });

        Button btnAdClose = view.findViewById(R.id.btn_ad_close);
        btnAdClose.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
    }

    private void initBottomNav() {
        binding.bottomNavigation.setSelectedItemId(R.id.dest_home);

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
                if(AppKeyValueStore.getValue(getBaseContext(), "userNo") == null) {
                    navController.navigate(R.id.dest_login);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("startPage", 1);
                    navController.navigate(R.id.dest_tabs_wish_recent_list, bundle, navOptions);
                }
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

                Window window = getWindow();
                new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());

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