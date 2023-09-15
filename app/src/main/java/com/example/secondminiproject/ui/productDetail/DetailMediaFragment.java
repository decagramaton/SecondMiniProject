package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentDetailMediaBinding;
import com.example.secondminiproject.databinding.FragmentPageBinding;


public class DetailMediaFragment extends Fragment {
    private static final String TAG = "PageFragment";
    private FragmentDetailMediaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailMediaBinding.inflate(inflater);

        //전달 데이터 받기(페이지 번호 받기)
        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");

        //페이지 별로 UI 세팅
        initUIByPageNo(pageNo);

        return binding.getRoot();
    }


    private void initUIByPageNo(int pageNo) {
        if(Math.abs(pageNo%5+1)==1){
            binding.imageView3.setImageResource(R.drawable.osaka_1);
        }else if(Math.abs(pageNo%5+1)==2){
            binding.imageView3.setImageResource(R.drawable.osaka_2);
        }else if(Math.abs(pageNo%5+1)==3 ){
            binding.imageView3.setImageResource(R.drawable.osaka_3);
        }else if(Math.abs(pageNo%5+1)==4 ){
            binding.imageView3.setImageResource(R.drawable.osaka_4);
        }else if(Math.abs(pageNo%5+1)==5 ) {
            binding.imageView3.setImageResource(R.drawable.osaka_5);
        }
    }
}