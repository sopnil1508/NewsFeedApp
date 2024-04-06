package com.sopnilshinde.newsfeedapp.presentation.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sopnilshinde.newsfeedapp.domain.model.News;
import com.sopnilshinde.newsfeedapp.domain.usecase.FetchNewsUseCase;

import java.util.List;

import javax.inject.Inject;


public class NewsViewModel extends ViewModel {
    private final FetchNewsUseCase getNewsUseCase;
    private final MutableLiveData<List<News>> newsFeed = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public NewsViewModel(FetchNewsUseCase getNewsFeedUseCase) {
        this.getNewsUseCase = getNewsFeedUseCase;
    }

    public LiveData<List<News>> getNewsFeed() {
        return newsFeed;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadNewsFeed() {
        try {
            List<News> articles = getNewsUseCase.execute();
            newsFeed.postValue(articles);
        } catch (RuntimeException e) {
            errorMessage.postValue(e.getMessage());
        }
    }
}