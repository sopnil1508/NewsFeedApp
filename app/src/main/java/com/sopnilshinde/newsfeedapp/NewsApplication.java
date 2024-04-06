package com.sopnilshinde.newsfeedapp;

import android.app.Application;

import com.sopnilshinde.newsfeedapp.di.DaggerNewsAppComponent;
import com.sopnilshinde.newsfeedapp.di.NewsAppComponent;
import com.sopnilshinde.newsfeedapp.di.NewsModule;

public class NewsApplication extends Application {

    private NewsAppComponent newsAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        newsAppComponent = DaggerNewsAppComponent.builder()
                .newsModule(new NewsModule(this))
                .build();
    }

    public NewsAppComponent getNewsAppComponent() {
        return newsAppComponent;
    }
}
