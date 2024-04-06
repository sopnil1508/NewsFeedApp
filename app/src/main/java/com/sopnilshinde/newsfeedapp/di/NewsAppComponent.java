package com.sopnilshinde.newsfeedapp.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsModule.class})
public interface NewsAppComponent {

    NewsViewModelFactory getNewsViewModelFactory();
}
