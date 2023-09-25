package com.example.secondminiproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentPageBinding;
import com.example.secondminiproject.dto.Board;


public class PageFragment extends Fragment {
    private static final String TAG = "PageFragment";
    private FragmentPageBinding binding;
    private int pageNo;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPageBinding.inflate(inflater);

        //페이지 별로 UI 세팅
        initUIByPageNo(pageNo);

        return binding.getRoot();
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    private void initUIByPageNo(int pageNo) {
        if(Math.abs(pageNo%5+1)==1){
            printMainBanner(R.drawable.ggomong2, 1);
        }else if(Math.abs(pageNo%5+1)==2){
            printMainBanner(R.drawable.banner1, 2);
        }else if(Math.abs(pageNo%5+1)==3 ){
            printMainBanner(R.drawable.banner2, 3);
        }else if(Math.abs(pageNo%5+1)==4 ){
            printMainBanner(R.drawable.banner3, 4);
            binding.imageView3.setImageResource(R.drawable.banner3);
        }else if(Math.abs(pageNo%5+1)==5 ) {
            printMainBanner(R.drawable.banner4, 5);
        }
    }

    private void printMainBanner(int resid, int productNo){
        binding.imageView3.setImageResource(resid);
        binding.imageView3.setOnClickListener(v-> {
            Bundle args = new Bundle();
            args.putInt("productNo", productNo);
            navController.navigate(R.id.dest_product_detail,args);
        });
    }
}