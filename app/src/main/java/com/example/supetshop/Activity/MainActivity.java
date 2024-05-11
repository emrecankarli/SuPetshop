package com.example.supetshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.supetshop.Adapter.PopularListAdapter;
import com.example.supetshop.Domain.PopularDomain;
import com.example.supetshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;

private RecyclerView.Adapter adapterPopular;
private RecyclerView recyclerViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView5);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }
        initRecyclerView();
        bottom_navigation();
        
    }

    private void bottom_navigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items= new ArrayList<>();
        items.add(new PopularDomain("Yüksek Sevgi Dolu Paket (Orta Irk Köpeklere Özel)","Sevimli patili dostunuzun sağlığı ve mutluluğu bizim için önemli! Yüksek Sevgi Dolu Paket ile onları şımartmanın tam zamanı!\n Bu özel paket, MyDog kalitesiyle hazırlanmış, köpeğinizin ihtiyaçlarına özel bir kombinasyon sunar.","hediye1bg",30,4.8,920));
        items.add(new PopularDomain("Motto Dog Sticks Kuzu Etli Ödül Çubuğu 3x5gr","Motto Köpek Ödülü daha fazlasını isteyen köpeğinizin tercihi. Tek kullanımlık ambalajı sayesinde, her zaman taze ve yumuşak kalacaktır. Günlük kullanım için uygundur.\n" +
                "\n" +
                "Benzersiz yapısı ve lezzeti ile köpeğinizin vazgeçemeyeceği bir ödül mamasıdır.\n" +
                "\n" +
                "Besleme Önerileri: Köpeğinizin ırkına ve kilosuna göre her gün 1-2 adet verebilirsiniz. 3 aydan küçük yavru köpeklere verilmesi uygun değildir. Yanında daima taze su bulundurunuz.","mama1",20,4.2,22.92));
        items.add(new PopularDomain("Dışkı Toplama Poşeti 4'lü","yedek dışkı toplama poşeti\n" +
                "\n" +
                "paket içinde 4 rulo bulunmaktadır","poset1",100,4.5,36));

        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterPopular=new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}