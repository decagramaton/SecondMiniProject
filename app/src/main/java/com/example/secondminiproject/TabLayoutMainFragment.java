package com.example.secondminiproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.databinding.FragmentTabLayoutMainBinding;
import com.example.secondminiproject.ui.RecentListFragment;
import com.example.secondminiproject.ui.RecentProduct.RecentProductListFragment;
import com.example.secondminiproject.ui.wish.WishListFragment;
import com.google.android.material.tabs.TabLayout;


public class TabLayoutMainFragment extends Fragment {

    private NavController navController;
    private FragmentTabLayoutMainBinding binding;

    private TabLayout tabs;
    private TabLayoutTestFragment tabLayoutTestFragment;
    private TabLayoutTest2Fragment tabLayoutTest2Fragment;
    private WishListFragment wishListFragment;
    private RecentProductListFragment recentProductListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTabLayoutMainBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        tabLayoutTestFragment = new TabLayoutTestFragment();
        tabLayoutTest2Fragment = new TabLayoutTest2Fragment();
        wishListFragment = new WishListFragment();
        recentProductListFragment = new RecentProductListFragment();

        getChildFragmentManager().beginTransaction().add(R.id.container, wishListFragment).commit();

        tabs = binding.tabs;
        tabs.addTab(tabs.newTab().setText("찜"));
        tabs.addTab(tabs.newTab().setText("최근 본 상품"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0)
                    selected = wishListFragment;
                else if(position == 1)
                    selected = recentProductListFragment;
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