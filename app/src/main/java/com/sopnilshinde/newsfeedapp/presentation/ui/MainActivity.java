package com.sopnilshinde.newsfeedapp.presentation.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sopnilshinde.newsfeedapp.NewsApplication;
import com.sopnilshinde.newsfeedapp.R;
import com.sopnilshinde.newsfeedapp.di.NewsAppComponent;
import com.sopnilshinde.newsfeedapp.di.NewsViewModelFactory;
import com.sopnilshinde.newsfeedapp.presentation.adapter.NewsAdapter;

public class MainActivity extends AppCompatActivity {

    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listView); // we can also view viewBinding here
        newsAdapter = new NewsAdapter(MainActivity.this);

        NewsApplication newsApp = (NewsApplication) getApplication();
        NewsAppComponent newsAppComponent = newsApp.getNewsAppComponent();

        NewsViewModelFactory newsViewModelFactory = newsAppComponent.getNewsViewModelFactory();
        newsViewModel = new ViewModelProvider(this, newsViewModelFactory)
                .get(NewsViewModel.class);

        recyclerView = findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);

        newsViewModel.getNewsFeed().observe(this, newsList -> {
            newsAdapter.setNewsList(newsList);
        });

        newsViewModel.getErrorMessage().observe(this, errorMessage -> {
            // Display the error message to the user
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        });

        newsViewModel.loadNewsFeed();
    }
}