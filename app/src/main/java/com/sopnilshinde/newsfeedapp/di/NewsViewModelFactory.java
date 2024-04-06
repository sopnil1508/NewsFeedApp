package com.sopnilshinde.newsfeedapp.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sopnilshinde.newsfeedapp.domain.usecase.FetchNewsUseCase;
import com.sopnilshinde.newsfeedapp.presentation.ui.NewsViewModel;

import javax.inject.Inject;

public class NewsViewModelFactory implements ViewModelProvider.Factory {
        private final FetchNewsUseCase getNewsFeedUseCase;

    @Inject
    public NewsViewModelFactory(FetchNewsUseCase getNewsFeedUseCase) {
            this.getNewsFeedUseCase = getNewsFeedUseCase;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(NewsViewModel.class)) {
                //noinspection unchecked
                return (T) new NewsViewModel(getNewsFeedUseCase);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
}
