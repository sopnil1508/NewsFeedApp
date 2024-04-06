package com.sopnilshinde.newsfeedapp.di;

import android.app.Application;
import android.content.Context;

import com.sopnilshinde.newsfeedapp.data.repository.NewsDataSource;
import com.sopnilshinde.newsfeedapp.data.repository.NewsRepositoryImpl;
import com.sopnilshinde.newsfeedapp.domain.usecase.FetchNewsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private final Application application;

    public NewsModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public NewsDataSource provideNewsRepository(Context context) {
        return new NewsRepositoryImpl(application.getApplicationContext());
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public FetchNewsUseCase provideGetNewsFeedUseCase(NewsDataSource newsDataSource) {
        return new FetchNewsUseCase(newsDataSource);
    }

}
