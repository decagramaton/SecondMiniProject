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
import com.google.android.material.textfield.TextInputEditText;


public class ReviewWriteFragment extends Fragment {
    private static final String TAG = "ReviewWriteFragment";
    private FragmentReviewWriteBinding binding;
    private NavController navController;
    private Button button;
    private TextInputEditText textInputEditText;
    int enableButtonColor = Color.rgb(22, 49, 114);
    int disableButtonColor = Color.rgb(216, 216, 216);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentReviewWriteBinding.inflate(inflater);
        button = binding.getRoot().findViewById(R.id.btn_review);
        View rootView = inflater.inflate(R.layout.fragment_review_write, container, false);
        EditText reviewContent = rootView.findViewById(R.id.review_content);
        textInputEditText = binding.getRoot().findViewById(R.id.review_content);

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
                    button.setClickable(true);
                    button.setBackgroundColor(enableButtonColor);
                } else {
                    // 텍스트가 없으면 버튼을 비활성화
                    button.setClickable(false);
                    button.setBackgroundColor(disableButtonColor);
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
            inputMethodManager.hideSoftInputFromWindow(binding.reviewContent.getWindowToken(), 0);
        });
        return binding.getRoot();
    }
    private void initBtnReviewWrite() {
        binding.btnReview.setOnClickListener(v -> {
            if (isTextValid()) { // 텍스트가 유효한 경우에만 동작
                navController.popBackStack();
            }
        });
    }

    // 내용 작성되었는지 확인
    private boolean isTextValid() {
        String text = binding.reviewContent.getText().toString();
        return text.length() > 5; // 텍스트가 5글자 이상일 때 true
    }
}