package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =findViewById(R.id.button);
        Intent intent=new Intent(this,MainActivity2.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intent);
            }
        });

                }
            }


