package com.example.secondminiproject.ui.wish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentWishListBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;

import java.util.ArrayList;
import java.util.Random;

public class WishListFragment extends Fragment {

    private static final String TAG = "WishListFragment";
    private FragmentWishListBinding binding;
    private NavController navController;
    ArrayList<Wish> wishProducts = new ArrayList<>();

    boolean position_flag = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWishListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();
        initBtnProductListImsi();
        initBtnProductDetailImsi();



        return binding.getRoot();
    }

    private void initBtnProductListImsi() {
        binding.btnToProductListImsi.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initBtnProductDetailImsi() {
        binding.btnToProductDetailImsi.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_detail);
        });
    }

    private void initRecyclerView() {
        //수직방향으로 1라인에 1개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        //2d열로 출력할때
        /* GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);*/
        binding.recyclerViewWish.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        WishAdapter wishAdapter = new WishAdapter();

        //데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=17; i++){
            Wish wishProduct = new Wish();
            wishProduct.setProductName("Title "+i);

            //이미지명, resource의 어디에있는지, 패키지명("com.example.myapplication"  or getApplication().getPackageName() ) 을 넣어야함.
            wishProduct.setProductImage(getResources().getIdentifier("photo" +(random.nextInt(17)+1), "drawable","com.example.secondminiproject"));
            wishProduct.setProductPrice(100000 * (random.nextInt(10)+1)); //10 -> 0~9 , 10 + 1 -> 1~10

            wishAdapter.addWishProduct(wishProduct);

            wishProducts.add(wishProduct);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerViewWish.setAdapter(wishAdapter);

        //항목을 클릭했을때 콜백 객체를 등록
        wishAdapter.setOnItemClickListener(new WishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "빔 항목 클릭 됨 ");
                //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                Wish wishProduct = wishAdapter.getItem(position);
                Bundle args = new Bundle();
                //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                args.putSerializable("wishProduct", wishProduct);
                navController.navigate(R.id.action_dest_wish_list_to_dest_product_detail,args);
            }
        });

        binding.recyclerViewWish.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if((!v.canScrollVertically(-1))){
                binding.btnWishGoListTop.hide();
            }else {
                binding.btnWishGoListTop.show();
            }
            position_flag = false;

        });

        binding.btnWishGoListTop.setOnClickListener(v -> {
            binding.recyclerViewWish.scrollToPosition(0);
        });


    }

}