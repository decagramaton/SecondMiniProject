package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.secondminiproject.ui.home.PageFragment;

public class DetailBannerAdapter extends FragmentStateAdapter {
    private static final String TAG = "DetailBannerAdapter";

    private int productNo;

    public DetailBannerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    //각 페이지로 넘어갈때의 fragment가 뭐야? 무슨페이지야?
    public Fragment createFragment(int position) {
        DetailMediaFragment detailMediaFragment = new DetailMediaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo",position+1);
        bundle.putInt("productNo",productNo);
        Log.i(TAG, "배너 어뎁터에서의 상품번호: "+productNo);
        detailMediaFragment.setArguments(bundle);

        return detailMediaFragment;
    }

    @Override
    public int getItemCount() {
        // 몇개를 돌릴거야? 지금 예시에서는 5개!
        return Integer.MAX_VALUE;
    }

    public void setProductNo(int productNo){
        this.productNo = productNo;
    }
}
