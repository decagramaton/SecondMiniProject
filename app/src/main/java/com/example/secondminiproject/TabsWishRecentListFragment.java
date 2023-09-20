package com.example.secondminiproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.databinding.FragmentTabsWishRecentListBinding;
import com.example.secondminiproject.ui.RecentProduct.RecentProductListFragment;
import com.example.secondminiproject.ui.wish.WishListFragment;
import com.google.android.material.tabs.TabLayout;


public class TabsWishRecentListFragment extends Fragment {
    private static final String TAG = "TabLayoutMainFragment";

    private NavController navController;
    private FragmentTabsWishRecentListBinding binding;

    private TabLayout tabs;
    private WishListFragment wishListFragment;
    private RecentProductListFragment recentProductListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabsWishRecentListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        wishListFragment = new WishListFragment();
        recentProductListFragment = new RecentProductListFragment();

        Bundle bundle = getArguments();
        int startPage = bundle.getInt("startPage");
        Log.i(TAG, "startPage: "+startPage);

        tabs = binding.tabs;

        if(startPage == 1){
            getChildFragmentManager().beginTransaction().add(R.id.container, wishListFragment).commit();
            tabs.addTab(tabs.newTab().setText("    찜    "));
            tabs.addTab(tabs.newTab().setText("최근 본 상품"));
        }else{
            getChildFragmentManager().beginTransaction().add(R.id.container, recentProductListFragment).commit();
            tabs.addTab(tabs.newTab().setText("최근 본 상품"));
            tabs.addTab(tabs.newTab().setText("    찜    "));
        }

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(startPage == 1){
                    if(position == 0)
                        selected = wishListFragment;
                    else if(position == 1)
                        selected = recentProductListFragment;
                }else{
                    if(position == 1)
                        selected = wishListFragment;
                    else if(position == 0)
                        selected = recentProductListFragment;
                }

                getChildFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    // Inflate the layout for this fragment
        return binding.getRoot();
    }
}