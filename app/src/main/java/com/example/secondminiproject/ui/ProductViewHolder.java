package com.example.secondminiproject.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Product;

import java.text.DecimalFormat;
import java.util.Currency;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ProductViewHolder";
    private int pno;
    private ImageView image;
    private TextView title;
    private TextView subTitle;
    private TextView content;
    private TextView price;
    private RatingBar rating;
    private TextView ratingScore;
    private TextView ratingCountByProduct;


    public ProductViewHolder(@NonNull View itemView, ProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.subTitle = itemView.findViewById(R.id.subtitle);
        this.content = itemView.findViewById(R.id.content);
        this.price = itemView.findViewById(R.id.price);
        this.rating = itemView.findViewById(R.id.rating);
        this.ratingScore = itemView.findViewById(R.id.rating_score);
        this.ratingCountByProduct = itemView.findViewById(R.id.rating_count_by_product);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    public void setData(Product product){
        this.pno = product.getPno();
        this.image.setImageResource(product.getImage());
        this.title.setText(product.getTitle());
        this.subTitle.setText(product.getSubTitle());
        this.content.setText(product.getContent());

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " + df.format(product.getPrice()));

        this.rating.setRating(product.getRating());
        this.ratingScore.setText(String.valueOf(product.getRating()));
        this.ratingCountByProduct.setText("(" + product.getRatingCountByProduct() + ")");
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

