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

import com.example.secondminiproject.MainActivity;
import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentRecentProductListBinding;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.RecentProduct;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentProductListFragment extends Fragment {
    private static final String TAG = "RecentProductListFragme";
    private FragmentRecentProductListBinding binding;
    private NavController navController;
    private RecentProductAdapter recentProductAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecentProductListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initRecyclerView();
        return binding.getRoot();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewRecentProductList.setLayoutManager(linearLayoutManager);

        this.recentProductAdapter = new RecentProductAdapter();
        ProductService productService = ServiceProvider.getProductService(getContext());

        Bundle bundle = ((MainActivity)getActivity()).getBundle();
        if(bundle != null) {
            //this.recentProducts.clear();
            ArrayList<String> recentProductList = bundle.getStringArrayList("recentProductList");

            for(String item : recentProductList) {
                Call<Board> call = productService.getProductByProductNo(Integer.parseInt(item));

                call.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {
                        Board board = response.body();

                        RecentProduct recentProduct = new RecentProduct();
                        recentProduct.setProductNo(board.getProductNo());
                        recentProduct.setProductName(board.getProductTitle());
                        recentProduct.setProductPrice(board.getProductChildPrice());
                        recentProduct.setReviewNumber(board.getReviewList().size());
                        recentProduct.setProductRating(board.getReviewList().get(0).getReviewRating());

                        recentProductAdapter.addRecentProduct(recentProduct);
                        recentProductAdapter.notifyItemInserted(recentProductAdapter.getItemCount()-1);
                    }

                    @Override
                    public void onFailure(Call<Board> call, Throwable t) {

                    }
                });
            }

            recentProductAdapter.setOnItemClickListener(new RecentProductAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    RecentProduct recentProduct = recentProductAdapter.getItem(position);
                    Bundle args = new Bundle();
                    args.putInt("productNo", recentProduct.getProductNo());
                    navController.navigate(R.id.dest_product_detail,args,null);
                }
            });
        }

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

    @Override
    public void onResume() {
        super.onResume();

        binding.recyclerViewRecentProductList.setAdapter(this.recentProductAdapter);
    }
}