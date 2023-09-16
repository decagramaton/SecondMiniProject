package com.example.secondminiproject.ui.RecentProduct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentRecentProductListBinding;
import com.example.secondminiproject.dto.RecentProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecentProductListFragment extends Fragment {
    private FragmentRecentProductListBinding binding;
    private NavController navController;
    private List<RecentProduct> recentProducts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecentProductListBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),LinearLayoutManager.VERTICAL, false
        );

        binding.recyclerViewRecentProductList.setLayoutManager(linearLayoutManager);

        RecentProductAdapter recentProductAdapter = new RecentProductAdapter();

        //데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=10; i++){
            RecentProduct recentProduct = new RecentProduct();
            recentProduct.setProductName("우당탕탕 이것이 여행의 진수다. 다양한 액티비티가 넘처 흐르는 여행 패키지 제 "+i +" 번! 상품!");
            recentProduct.setProductRating(i/2);
            recentProduct.setReviewNumber(i*7);
            //이미지명, resource의 어디에있는지, 패키지명("com.example.myapplication"  or getApplication().getPackageName() ) 을 넣어야함.
            recentProduct.setProductImage(getResources().getIdentifier("photo" +(random.nextInt(17)+1), "drawable","com.example.secondminiproject"));
            recentProduct.setProductPrice(100000 * (random.nextInt(10)+1)); //10 -> 0~9 , 10 + 1 -> 1~10

            recentProductAdapter.addRecentProduct(recentProduct);

            recentProducts.add(recentProduct);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerViewRecentProductList.setAdapter(recentProductAdapter);

        //항목을 클릭했을때 콜백 객체를 등록
        recentProductAdapter.setOnItemClickListener(new RecentProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                RecentProduct recentProduct = recentProductAdapter.getItem(position);
                Bundle args = new Bundle();
                //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                args.putSerializable("recentProduct", recentProduct);

                navController.navigate(R.id.action_tabLayoutMainFragment_to_dest_product_detail2,args,null);
            }
        });

        binding.recyclerViewRecentProductList.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if((!v.canScrollVertically(-1))){
                binding.btnRecentProductGoListTop.hide();
            }else {
                binding.btnRecentProductGoListTop.show();
            }

        });

        binding.btnRecentProductGoListTop.setOnClickListener(v -> {
            binding.recyclerViewRecentProductList.scrollToPosition(0);
        });
    }


}