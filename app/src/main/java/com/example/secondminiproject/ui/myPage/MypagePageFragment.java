package com.example.secondminiproject.ui.myPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentMypagePageBinding;


public class MypagePageFragment extends Fragment {
    private static final String TAG = "MypagePageFragment";
    private FragmentMypagePageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypagePageBinding.inflate(inflater);

        //전달 데이터 받기(페이지 번호 받기)
        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");

        //페이지 별로 UI 세팅
        initUIByPageNo(pageNo);


        return binding.getRoot();
    }


    private void initUIByPageNo(int pageNo) {
        if(Math.abs(pageNo%5+1)==1){
            binding.imageView33.setImageResource(R.drawable.ggomong);
        }else if(Math.abs(pageNo%5+1)==2){
            binding.imageView33.setImageResource(R.drawable.banner1);
        }else if(Math.abs(pageNo%5+1)==3 ){
            binding.imageView33.setImageResource(R.drawable.banner2);
        }else if(Math.abs(pageNo%5+1)==4 ){
            binding.imageView33.setImageResource(R.drawable.banner3);
        }else if(Math.abs(pageNo%5+1)==5 ) {
            binding.imageView33.setImageResource(R.drawable.banner4);
        }
    }
}