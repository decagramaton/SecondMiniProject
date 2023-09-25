package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.databinding.FragmentLoginBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.LoginResult;
import com.example.secondminiproject.dto.UserInfo;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.UserService;
import com.google.android.material.appbar.AppBarLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            // Step1. User Input Data Load
            String userId = binding.mid.getText().toString();
            String userPassword = binding.mpassword.getText().toString();

            UserService userService = ServiceProvider.getUserService(getContext());
            Call<LoginResult> call = userService.login(userId, userPassword);
            call.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                    LoginResult loginResult = response.body();
                    Log.i(TAG, "loginResult: " + loginResult);
                    if(loginResult.getResult().equals("success")){
                        getUserInfo(userId);
                        navController.popBackStack(R.id.dest_home, false);
                    } else if(loginResult.getResult().equals("fail_user_id")){
                        binding.midLayout.setError("계정정보가 일치하지 않습니다.");
                    } else if(loginResult.getResult().equals("fail_user_password")){
                        binding.mpasswordLayout.setHelperText("PW가 일치하지 않습니다.");
                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    Log.i(TAG, "로그인 요청 API 통신 연결 실패");
                }
            });
        });
    }

    private void getUserInfo(String userId) {

        UserService userService = ServiceProvider.getUserService(getContext());
        Call<UserInfo> call = userService.getUserInfo(userId);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo = response.body();
                Log.i(TAG, userInfo.toString());

                AppKeyValueStore.put(getContext(), "userNo", String.valueOf(userInfo.getUserNo()));
                AppKeyValueStore.put(getContext(), "userKoName", userInfo.getUserKoName());
                AppKeyValueStore.put(getContext(), "userId", userInfo.getUserId());
                AppKeyValueStore.put(getContext(), "userPassword", userInfo.getUserPassword());
                AppKeyValueStore.put(getContext(), "userBirth", DateFormat.format("MM/dd/yyyy", new Date(userInfo.getUserBirth())).toString());
                AppKeyValueStore.put(getContext(), "userPhone", userInfo.getUserPhone());
                AppKeyValueStore.put(getContext(), "userGender", userInfo.getUserGender());
                AppKeyValueStore.put(getContext(), "userEnName", userInfo.getUserEnName());
                AppKeyValueStore.put(getContext(), "userEmail", userInfo.getUserEmail());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    private void initBtnCancel() {
        binding.btnCancel.setOnClickListener(v->{
            navController.popBackStack();
        });
    }

}