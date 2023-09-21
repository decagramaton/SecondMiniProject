package com.example.secondminiproject.ui.review;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewWriteBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ReviewService;
import com.example.secondminiproject.service.ServiceProvider;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewWriteFragment extends Fragment {
    private static final String TAG = "ReviewWriteFragment";
    private FragmentReviewWriteBinding binding;
    private NavController navController;
    private Button registerButton;
    private TextInputEditText textInputEditText;
    int enableButtonColor = Color.rgb(22, 49, 114);
    int disableButtonColor = Color.rgb(216, 216, 216);
    private int reservationNo;
    private  int productNo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentReviewWriteBinding.inflate(inflater);
        View rootView = inflater.inflate(R.layout.fragment_review_write, container, false);

        initSetData();
        initBtnReviewWrite();

        // TextInputEditText에 TextWatcher 추가
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트 변경 전에 실행되는 부분
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "텍스트가 변경됨: " + charSequence.toString());
                // 텍스트가 변경될 때 실행되는 부분
                if (charSequence.length() > 5) {
                    // 텍스트가 입력되면 버튼을 활성화
                    registerButton.setClickable(true);
                    registerButton.setBackgroundColor(enableButtonColor);
                } else {
                    // 텍스트가 없으면 버튼을 비활성화
                    registerButton.setClickable(false);
                    registerButton.setBackgroundColor(disableButtonColor);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // 텍스트 변경 후에 실행되는 부분
            }
        });

        //화면 터치로 키보드 숨기기
        binding.reviewWriteLayout.setOnClickListener(v->{
            InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(binding.reviewWriteContent.getWindowToken(), 0);
        });

        return binding.getRoot();
    }

    private void initSetData() {
        registerButton = binding.btnReviewWriteRegister;
        EditText reviewContent = binding.reviewWriteContent;
        textInputEditText = binding.reviewWriteContent;

        Bundle bundle = getArguments();
        productNo = bundle.getInt("productNo");
        reservationNo = bundle.getInt("reservationNo");
        Log.i(TAG, "리뷰작성에서의 예약번호 : "+reservationNo);;

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<Board> call = productService.getProductByProductNo(productNo);

        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Board productInfo = response.body();
                ProductService.loadImageByMediaName(productNo,"name_thumbnail",binding.reviewWriteImage);
                binding.reviewWriteTitle.setText(productInfo.getProductTitle());
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });


    }

    private void initBtnReviewWrite() {
        binding.btnReviewWriteRegister.setOnClickListener(v -> {
            if (isTextValid()) { // 텍스트가 유효한 경우에만 동작
                String title = binding.reviewWriteTitle.getText().toString();
                int rating = Math.round(binding.reviewWriteRating.getRating());
                Log.i(TAG, "레이팅 점수 : "+Math.round(binding.reviewWriteRating.getRating()));
                Log.i(TAG, "예약 번호 : "+reservationNo);
                String content = binding.reviewWriteContent.getText().toString();
                int userNo = Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo"));;
                ReviewService reviewService = ServiceProvider.getReviewService(getContext());
                Call<Void> call = reviewService.addReview(title,rating,content,reservationNo,userNo,productNo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        navController.navigate(R.id.dest_review_list);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    // 내용 작성되었는지 확인
    private boolean isTextValid() {
        String text = binding.reviewWriteContent.getText().toString();
        return text.length() > 5; // 텍스트가 5글자 이상일 때 true
    }
}