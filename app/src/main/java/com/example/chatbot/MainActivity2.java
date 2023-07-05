package com.example.chatbot;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.LinearLayoutCompat;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

        import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
        import io.reactivex.rxjava3.core.Observable;
        import io.reactivex.rxjava3.schedulers.Schedulers;
        import okhttp3.FormBody;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.RequestBody;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    private static final String TAG = "MainActivity";
    EditText message;
    ImageView send,back;
    List<messageModel> list;
    massageAdapter adapter;
    OkHttpClient okHttpClient;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //getSupportActionBar().hide();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(150, TimeUnit.SECONDS)
                .writeTimeout(150, TimeUnit.SECONDS)
                .readTimeout(150, TimeUnit.SECONDS)
                .build();
        recyclerView =findViewById(R.id.recycleview);
        message=findViewById(R.id.message);
        send=findViewById(R.id.send);
        back=findViewById(R.id.imageView2);
        Intent intent2=new Intent(this,MainActivity.class);


        list=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        adapter =new massageAdapter(list);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);

            }
        });
        String start ="مرحباً بك أنا نور بوت للفتاوى كيف أساعدك ؟";
        addTochat(start,messageModel.sent_By_Bot);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String question= message.getText().toString().trim();
                if(question.isEmpty()){
                    Toast.makeText(MainActivity2.this,"write something ",Toast.LENGTH_SHORT).show();
                }  else {

                    addTochat(question,messageModel.sent_By_Me);
                    message.setText("");

                    addTochat("typing....",messageModel.sent_By_Bot);
                    RequestBody formbody = new FormBody.Builder().add("chatInput", question).build();

                    try {
                        Request request = new Request.Builder().url("http://192.168.1.43:5000/chat")
                                .post(formbody)
                                .build();
                        Observable.fromCallable(() -> okHttpClient.newCall(request).execute())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .map(response -> response.body().string())
                                .subscribe(responseString -> {
                                            Log.d(TAG, "onClick: " + responseString);
                                            list.remove(list.size()-1);
                                            addTochat(responseString,messageModel.sent_By_Bot);

                                        }
                                        , throwable -> Log.d(TAG, "onClick: " + throwable.toString()));
                    }
                    catch (Exception e) {}
                }
            }

        });

    }

    private void addTochat(String question, String sent_by_me) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.add(new messageModel(question,sent_by_me));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });

    }
}