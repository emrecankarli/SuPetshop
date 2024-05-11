package com.example.supetshop.Activity;

import static java.util.ResourceBundle.getBundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.supetshop.Domain.PopularDomain;
import com.example.supetshop.Helper.ManagementCart;
import com.example.supetshop.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,reviewTxt,scoreTxt;
    private ImageView picItem,backBtn;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object=(PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getPrice() + " TL");
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview() + "");
        scoreTxt.setText(object.getScore() + "");

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            managementCart.insertFood(object);
        });
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        feeTxt=findViewById(R.id.priceTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        picItem=findViewById(R.id.itemPic);
        reviewTxt=findViewById(R.id.reviewTxt);
        scoreTxt=findViewById(R.id.scoreTxt);
        backBtn=findViewById(R.id.backBtn);
    }
}