package com.example.supetshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supetshop.Adapter.CartListAdapter;
import com.example.supetshop.Adapter.PopularListAdapter;
import com.example.supetshop.Helper.ChangeNumberItemsListener;
import com.example.supetshop.Helper.ManagementCart;
import com.example.supetshop.R;

public class CartActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerView;
private ManagementCart managementCart;

private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
private double tax;
private ScrollView scrollView;
private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        managementCart=new ManagementCart(this);

        initView();
        setVariable();
        initList();
        calculateCart();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax=0.2;
        double delivery=10;
        tax=Math.round((managementCart.getTotalFee()*percentTax*100.0))/100.0;

        double total=Math.round((managementCart.getTotalFee()+tax+delivery) * 100) / 100;
        double itemTotal=Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText(itemTotal + " TL");
        taxTxt.setText(tax + " TL");
        deliveryTxt.setText(delivery + " TL");
        totalTxt.setText(total + " TL");
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerView=findViewById(R.id.view3);
        scrollView=findViewById(R.id.scrollView2);
        backBtn=findViewById(R.id.backBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
    }
}