package com.example.secondminiproject.ui.myPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentMyPageBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.service.UserService;

import java.util.Timer;
import java.util.TimerTask;


public class MyPageFragment extends Fragment {

    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        //binding =FragmentMyPageBinding.inflate(inflater);
        binding = FragmentMyPageBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        initBtnReviewList();
        initBtnReservationList();
        initBtnWishList();
        initBtnUserInfo();
        initBtnRecentList();


        initPagerView();
        settingLoginLogout();

        return binding.getRoot();
    }

    public void onStop() {
        super.onStop();
        timerTask.cancel();
        handler.removeCallbacksAndMessages(null);
    }

    private void initPagerView() {
        MypageBannerAdapter userInfoBannerAdapter = new MypageBannerAdapter(getActivity());
        binding.mypageBanner.setAdapter(userInfoBannerAdapter);

        // Timer를 사용하여 이미지 자동 슬라이드 시작
        startAutoSlide();
    }
    //Timer: 일정 시간간격으로 작업을 실행하기 위함
    private Timer timer;
    //현재 페이지 번호를 받기위함
    private int currentPage = 0;
    private void startAutoSlide(){
        //Runnable update: 타이머가 실행될 때마다 호출되는 Runnable 객체
        //Runnable 내부에서 현재 페이지 번호를 확인하고, 페이지 번호를 증가시킴
        final Runnable update = () -> {
            //현재 페이지가 5에 도달하면 다시 0으로 초기화
            if(currentPage == 5){
                currentPage = 0;
            }
            binding.mypageBanner.setCurrentItem(currentPage++, true);
        };
        timer = new Timer();

        //Timer를 사용하여 작업을 예약
        timer.schedule(timerTask =new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(update);
            }
        },3000,3000);
    }

    @Override
    public void onResume() {
        super.onResume();
        settingLoginLogout();
    }

    private void settingLoginLogout() {
        String userNo = AppKeyValueStore.getValue(getContext(), "userNo");
        if(userNo == null){
            binding.btnMyPageLoginLogout.setText("로그인");

            binding.btnMyPageLoginLogout.setOnClickListener(v -> {
                navController.navigate(R.id.dest_login);
            });
        } else {
            UserService.loadUserProfileImage(Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo")), binding.profileImage);
            binding.userName.setText(AppKeyValueStore.getValue(getContext(),"userKoName"));

            binding.btnMyPageLoginLogout.setText("로그아웃");
            binding.btnMyPageLoginLogout.setOnClickListener(v -> {
                AppKeyValueStore.remove(getContext(), "userNo");
                AppKeyValueStore.remove(getContext(), "userKoName");
                AppKeyValueStore.remove(getContext(), "userId");
                AppKeyValueStore.remove(getContext(), "userPassword");
                AppKeyValueStore.remove(getContext(), "userBirth");
                AppKeyValueStore.remove(getContext(), "userPhone");
                AppKeyValueStore.remove(getContext(), "userGender");
                AppKeyValueStore.remove(getContext(), "userEnName");
                AppKeyValueStore.remove(getContext(), "userEmail");

                navController.popBackStack(R.id.dest_home, false);
            });
        }
    }

    private void initBtnReviewList() {
        binding.btnMyPageReviewList.setOnClickListener(v -> {
            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                navController.navigate(R.id.action_dest_my_page_to_dest_review_list);
            }
        });
    }

    private void initBtnReservationList() {
        binding.btnMyPageReservationList.setOnClickListener(v -> {
            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                navController.navigate(R.id.action_dest_my_page_to_dest_reservation_list);
            }
        });
    }

    private void initBtnWishList() {
        binding.btnMyPageWishList.setOnClickListener(v -> {
            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("startPage",1);
                navController.navigate(R.id.action_dest_my_page_to_dest_tabs_wish_recent_list,bundle);
            }
        });
    }

    private void initBtnUserInfo() {
        binding.btnMyPageUserInfo.setOnClickListener(v -> {

            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                navController.navigate(R.id.action_dest_my_page_to_dest_user_info);
            }
        });
    }

    private void initBtnRecentList() {
        binding.btnMyPageRecentList.setOnClickListener(v -> {
            if(AppKeyValueStore.getValue(getContext(), "userNo") == null) {
                navController.navigate(R.id.dest_login);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("startPage",2);
                navController.navigate(R.id.action_dest_my_page_to_dest_tabs_wish_recent_list,bundle);
            }
        });
    }
}