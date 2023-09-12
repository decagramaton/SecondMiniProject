package com.example.secondminiproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentPageBinding;


public class PageFragment extends Fragment {
    private static final String TAG = "PageFragment";
    private FragmentPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPageBinding.inflate(inflater);

        //전달 데이터 받기(페이지 번호 받기)
        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");

        //페이지 별로 UI 세팅
        initUIByPageNo(pageNo);


        return binding.getRoot();
    }

    private void initUIByPageNo(int pageNo) {
        if(pageNo==1){
            binding.imageView3.setImageResource(R.drawable.ggomong);
        }else if(pageNo==2){
            binding.imageView3.setImageResource(R.drawable.banner1);
        }else if(pageNo==3){
            binding.imageView3.setImageResource(R.drawable.banner2);
        }else if(pageNo==4){
            binding.imageView3.setImageResource(R.drawable.banner3);
        }else if(pageNo==5) {
            binding.imageView3.setImageResource(R.drawable.banner4);
        }
    }
}