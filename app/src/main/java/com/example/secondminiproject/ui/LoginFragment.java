package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.databinding.FragmentLoginBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private FragmentLoginBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnLogin();
        initBtnCancel();

        return binding.getRoot();
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v->{
            // 정적 페이시 개발 시, 로그인 성공 시나리오로 구현한 코드
            // DB 연결 시 변경 필요
            AppKeyValueStore.put(getContext(), "mid", "user");
            AppKeyValueStore.put(getContext(), "mpassword", "12345");

            navController.popBackStack();
        });
    }

    private void initBtnCancel() {
        binding.btnCancel.setOnClickListener(v->{
            navController.popBackStack();
        });
    }

}