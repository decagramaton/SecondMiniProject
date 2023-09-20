package com.example.secondminiproject.ui.wish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentWishListBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.WishService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListFragment extends Fragment {

    private static final String TAG = "WishListFragment";
    private FragmentWishListBinding binding;
    private NavController navController;
    ArrayList<Wish> wishProducts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWishListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();

        return binding.getRoot();
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


        WishService wishService = ServiceProvider.getWishService(getContext());

        Call<List<Product>> call = wishService.getWishListByUserNo(Integer.parseInt(AppKeyValueStore.getValue(getContext(), "userNo")));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                wishAdapter.setWishList(productList);
                binding.recyclerViewWish.setAdapter(wishAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i(TAG, "검색 결과 통신 실패 이벤트 호출");
            }
        });


        /*//데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=17; i++){
            Wish wishProduct = new Wish();
            wishProduct.setProductName("우당탕탕 이것이 여행의 진수다. 황홀한 풍경과 달달구리한 과일! 다양한 액티비티가 넘처 흐르는 여행 패키지 제 "+i +" 번! 상품!");

            //이미지명, resource의 어디에있는지, 패키지명("com.example.myapplication"  or getApplication().getPackageName() ) 을 넣어야함.
            wishProduct.setProductImage(getResources().getIdentifier("photo" +(random.nextInt(17)+1), "drawable","com.example.secondminiproject"));
            wishProduct.setProductPrice(100000 * (random.nextInt(10)+1)); //10 -> 0~9 , 10 + 1 -> 1~10

            wishAdapter.addWishProduct(wishProduct);

            wishProducts.add(wishProduct);
        }*/

        //리사이클러뷰에 어댑터 설정
        binding.recyclerViewWish.setAdapter(wishAdapter);

        //항목을 클릭했을때 콜백 객체를 등록
        wishAdapter.setOnItemClickListener(new WishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG, position + "번 항목 클릭 됨 ");
                //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                Product wishProduct = wishAdapter.getItem(position);
                Bundle args = new Bundle();
                //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                args.putSerializable("productNo", wishProduct.getProductNo());

                navController.navigate(R.id.dest_product_detail, args,null);
            }
        });

        binding.recyclerViewWish.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if((!v.canScrollVertically(-1))){
                binding.btnWishGoListTop.hide();
            }else {
                binding.btnWishGoListTop.show();
            }

        });

        binding.btnWishGoListTop.setOnClickListener(v -> {
            binding.recyclerViewWish.scrollToPosition(0);
        });
    }
}