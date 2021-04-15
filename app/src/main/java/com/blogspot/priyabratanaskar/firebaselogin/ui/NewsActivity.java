package com.blogspot.priyabratanaskar.firebaselogin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.blogspot.priyabratanaskar.firebaselogin.JSONPlaceHolderAPI;
import com.blogspot.priyabratanaskar.firebaselogin.R;
import com.blogspot.priyabratanaskar.firebaselogin.adapters.NewsAdapter;
import com.blogspot.priyabratanaskar.firebaselogin.model.Article;
import com.blogspot.priyabratanaskar.firebaselogin.model.ResponseModel;
import com.blogspot.priyabratanaskar.firebaselogin.model.SourceModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private List<Article> mNewsData;
    private NewsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mNewsData = new ArrayList<>();

        loadNews();

        // Get the data.
        //mAdapter = new NewsAdapter(mNewsData,NewsActivity.this);
        //mRecyclerView.setAdapter(mAdapter);
    }
    private void loadNews(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://newsapi.org/v2/").
                addConverterFactory(GsonConverterFactory.create()).build();
        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);

        //Call<ResponseModel> call = jsonPlaceHolderAPI.getNews("in","health","84a9ce0f3b9040bf8edf144d10a95708");

        Call<ResponseModel> call = jsonPlaceHolderAPI.getResponse();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (!response.body().getStatus().equals("ok")){
                    Toast.makeText(NewsActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
                List<Article> articleList = response.body().getArticles();
                if (articleList.size()>0){
                    mNewsData = articleList;
                    mAdapter = new NewsAdapter(mNewsData,NewsActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(NewsActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeData(){
        mNewsData.add(new Article(new SourceModel("01","Dtnext.in"),"Akshay","Masks, social distancing by 60 pc people may curb Covid spread - DTNext",
                "Coronavirus' viral outbreaks can be prevented if at least 60 per cent of people start wearing masks, and maintain social distancing, according to a study.",
                "https://img.dtnext.in/Articles/2021/Apr/202104150938218538_Masks-social-distancing-by-60-pc-people-may-curb-Covid_SECVPF.gif",
                "Young women today may face increased health risks linked to breast cancer due to effects from a banned toxic pesticide lasting over three generations, according to a new study from the Public Health … [+4564 chars]",
                "2021-04-15T03:52:33Z"));

    }

}