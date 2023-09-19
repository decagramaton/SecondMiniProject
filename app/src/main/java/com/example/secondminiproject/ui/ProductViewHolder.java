package com.example.secondminiproject.ui;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.service.WishService;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ProductViewHolder";
    private int productNo;
    private ImageView image;
    private TextView title;
    private TextView visitPlace;
    private TextView price;
    private RatingBar rating;
    private TextView ratingCountByProduct;
    private CheckBox btnWish;


    public ProductViewHolder(@NonNull View itemView, ProductAdapter.OnItemClickListener onItemClickListener, ProductAdapter.OnBtnWishClickListener onBtnWishClickListener) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.visitPlace = itemView.findViewById(R.id.visitPlace);
        this.price = itemView.findViewById(R.id.price);
        this.rating = itemView.findViewById(R.id.rating);
        this.ratingCountByProduct = itemView.findViewById(R.id.rating_count_by_product);
        this.btnWish = itemView.findViewById(R.id.btn_wish);

        //찜 버튼 클릭시 이벤트 처리 - 그래서 여기서 productNo를 사용할수있게 가지고다니도록 설정
        btnWish.setOnClickListener(v -> {
            onBtnWishClickListener.onItemClick(v, getAdapterPosition(), productNo);
        });

        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());//어뎁터 항목번호
        });

    }

    public void setData(Board board){
        //BtnWish에서 필요한 productNo를 받아오는 부분 - 전역변수productNo에 담아준다 - 온클릭리스너로 ㄱㄱ
        this.productNo = board.getProductNo();
        
        // Image 다운 필요
        ProductService.loadImageByMediaName(this.productNo, "main",image);


        this.title.setText(board.getProductTitle());
        this.visitPlace.setText(board.getProductVisitPlace());

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " + df.format(board.getProductAdultPrice()));

        // 리뷰 요청 DB 필요
        /*this.rating.setRating(1);
        this.ratingScore.setText(String.valueOf(2));
        this.ratingCountByProduct.setText("(" + 3 + ")");*/

        List<Review> reviewList = board.getReviewList();

        int reviewTotalSum = 0;
        for(Review item : reviewList){
            reviewTotalSum += item.getReviewRating();
        }

        float reviewAverage = (float)(reviewTotalSum/reviewList.size());

        this.rating.setRating(reviewAverage);
        this.ratingCountByProduct.setText("(" + reviewList.size() + ")");
    }
}

/*    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);

        //아이템 UI 얻기
        battach = (ImageView) itemView.findViewById(R.id.battach);
        btitle = (TextView) itemView.findViewById(R.id.btitle);
        mid = (TextView)itemView.findViewById(R.id.mid);
        bdate = (TextView)itemView.findViewById(R.id.bdate);
        bcontent = (TextView)itemView.findViewById(R.id.bcontent);

    }

    public void setData(Board board) {
        BoardService.loadImage(board.getBno(), battach);
        btitle.setText(board.getBtitle());
        mid.setText(board.getMid());
        bdate.setText(board.getBdate());
        bcontent.setText(board.getBcontent());
    }
}*/

