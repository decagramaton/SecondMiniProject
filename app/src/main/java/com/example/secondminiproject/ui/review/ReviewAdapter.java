package com.example.secondminiproject.ui.review;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewListBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private static final String TAG = "ReviewAdapter";
    private List<Review> reviewList = new ArrayList<>();

    private FragmentReviewListBinding binding;

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View reviewView = layoutInflater.inflate(R.layout.fragment_review_list3, parent, false);
        //괄호안에 inflater 넣어야함
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(reviewView);

        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        Review review = reviewList.get(position);
        //홀더에 데이터를 세팅해준다.
        holder.setData(review);

        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //인플레이션에서 앞의것을 뒤에것에 부착하겟다는 뜻.
                menuInflater.inflate(R.menu.review_list_card, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.toolbar_review_list_card_modify_review){
                    Log.i(TAG, "리뷰 수정 버튼 클릭됨");
                    return true;
                }else if(menuItem.getItemId()==R.id.toolbar_review_list_card_delete_review){
                    Log.i(TAG, "리뷰 삭제 버튼 클릭됨");
                    return true;
                }
                return false;
            }
        };

        /*addMenuProvider(menuProvider, this, Lifecycle.State.RESUMED );*/
    }

    public void setList(List<Review> reviewList) {this.reviewList = reviewList;}

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void addReview(Review review){
        reviewList.add(review);
    }
}
