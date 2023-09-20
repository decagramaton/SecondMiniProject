package com.example.secondminiproject.ui.userInfo;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.databinding.FragmentMyPageBinding;
import com.example.secondminiproject.databinding.FragmentUserInfoBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.UserService;
import com.example.secondminiproject.ui.home.HomeBannerAdapter;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserInfoFragment extends Fragment {
    private static final String TAG = "UserInfoFragment";
    private FragmentUserInfoBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);

        binding = FragmentUserInfoBinding.inflate(inflater);

        initBtnImageChange();

        initUserInformation();

        return binding.getRoot();
    }

    private void initBtnImageChange() {
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if (uri != null) {
                                binding.profileImage.setImageURI(uri);
                                binding.profileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                saveUserProfileImageToDB();
                            }
                        }
                );
        binding.btnImageChange.setOnClickListener(v -> {
            PickVisualMediaRequest request = new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build();
            activityResultLauncher.launch(request);
        });
    }


    private void initUserInformation() {
        UserService.loadUserProfileImage(Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo")), binding.profileImage);
        binding.userName.setText(AppKeyValueStore.getValue(getContext(),"userKoName"));
        binding.email.setText(AppKeyValueStore.getValue(getContext(),"userEmail"));
        binding.enName.setText(AppKeyValueStore.getValue(getContext(),"userEnName"));
        binding.phoneNo.setText(AppKeyValueStore.getValue(getContext(),"userPhone"));
        binding.birthday.setText(AppKeyValueStore.getValue(getContext(),"userBirth"));
    }



    /**
     * @author 고팀장
     * 나는 능이버섯이다.
     */
    private void saveUserProfileImageToDB() {
        // Step1. ImageView의 이미지 데이터를 BitMap으로 변환
        BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.profileImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Step2. BitMap의 데이터 형식과 byte[] 형태로 변환
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);       // ( 이미지 확장자 설정, 원본 비율(100%), byte[] )
        byte[] bytes = baos.toByteArray();

        // Step3. MultiPart 사용을 위한 requestBody 전처리
        String fileName = new Date().getTime() + ".jpg";
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), bytes);

        // Step4. Multipart의 Part 설정
        MultipartBody.Part battach = MultipartBody.Part.createFormData("battach", fileName, requestBody);
        MultipartBody.Part userNo = MultipartBody.Part.createFormData("userNo", AppKeyValueStore.getValue(getContext(),"userNo"));
        
        /*// Step1. ImageView의 이미지 데이터를 서버에 전송할 데이터로 변환
        BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.profileImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // Step2. 사진 크기가 기본값 초과시 크기 보정
        bitmap = resize(bitmap);

        // Step3. 비트맵을 서버에 저장하기 위해서는 블롭 형식으로 담아야 한다.
        //블롭 객체에 담으려면, Drawable을 Bitmap으로 바꾸고, Bitmap을 byte []으로 바꾸고, 그 byte []를 스트링으로 변환하는 과정을 거쳐서,
        //스트링으로 만들어야 비로소 이진 데이터로 DB에 저장을 할 수 있다.
        //위 과정을 순차적으로 해결하게 해주는 메서드
        String userProfileImage = bitmapToByteArray(bitmap);*/

        // Step5. MultipartBody 데이터를 서버에 전달
        UserService userService = ServiceProvider.getUserService(getContext());
        Call<Void> call = userService.setUserProfileImage(battach, userNo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, "프로필 사진 변경 Post API 통신 성공");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG, "프로필 사진 변경 Post API 통신 실패");
            }
        });
    }

    /**
     * 이미지가 바이너리 값을 넘기거나, 사진 크기가 안 맞을 수 있으므로, 사진 크기가 기본 값을 초과하면 크기 조정
     * @param - bitmap
     * @return - bitmap
     */
    private Bitmap resize(Bitmap bm){
        Configuration config=getResources().getConfiguration();
        if(config.smallestScreenWidthDp>=800)
            bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
        else if(config.smallestScreenWidthDp>=600)
            bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
        else if(config.smallestScreenWidthDp>=400)
            bm = Bitmap.createScaledBitmap(bm, 200, 120, true);
        else if(config.smallestScreenWidthDp>=360)
            bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
        else
            bm = Bitmap.createScaledBitmap(bm, 160, 96, true);
        return bm;
    }


    /**
     * 비트맵을 바이너리 바이트 배열로 바꾸어주는 메서드
     */
    public String bitmapToByteArray(Bitmap bitmap) {
        String image = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        image = "&image=" + byteArrayToBinaryString(byteArray);
        return image;
    }

    /**
     * 바이너리 바이트 배열을 스트링으로 바꾸어주는 메서드
     */
    public static String byteArrayToBinaryString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }

    /**
     * 바이너리 바이트를 스트링으로 바꾸어주는 메서드
     */
    public static String byteToBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }
}