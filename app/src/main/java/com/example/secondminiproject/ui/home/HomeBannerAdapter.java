package com.example.secondminiproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeBannerAdapter extends FragmentStateAdapter {
    public HomeBannerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    //각 페이지로 넘어갈때의 fragment가 뭐야? 무슨페이지야?
    public Fragment createFragment(int position) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo",position+1);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public int getItemCount() {
        // 몇개를 돌릴거야? 지금 예시에서는 5개!
        return 5;
    }
}
