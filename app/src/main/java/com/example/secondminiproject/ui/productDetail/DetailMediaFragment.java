package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentDetailMediaBinding;
import com.example.secondminiproject.databinding.FragmentPageBinding;
import com.example.secondminiproject.service.ProductService;


public class DetailMediaFragment extends Fragment {
    private static final String TAG = "PageFragment";
    private FragmentDetailMediaBinding binding;
    private int productNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailMediaBinding.inflate(inflater);

        //전달 데이터 받기(페이지 번호 받기)
        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");
        this.productNo = bundle.getInt("productNo");
        Log.i(TAG, "번들로 온 상품 번호: "+productNo);
        //페이지 별로 UI 세팅
        initUIByPageNo(pageNo);

        return binding.getRoot();
    }


    private void initUIByPageNo(int pageNo) {
        if(Math.abs(pageNo%5+1)==1){
            ProductService.loadImageByMediaName( this.productNo, "name_main" ,binding.imageView3);
        }else if(Math.abs(pageNo%5+1)==2){
            ProductService.loadImageByMediaName( this.productNo, "name_thumbnail" ,binding.imageView3);
        }else if(Math.abs(pageNo%5+1)==3 ){
            ProductService.loadImageByMediaName( this.productNo, "name_sub_1" ,binding.imageView3);
        }else if(Math.abs(pageNo%5+1)==4 ){
            ProductService.loadImageByMediaName( this.productNo, "name_sub_2" ,binding.imageView3);
        }else if(Math.abs(pageNo%5+1)==5 ) {
            ProductService.loadImageByMediaName( this.productNo, "name_sub_3" ,binding.imageView3);
        }
    }
}