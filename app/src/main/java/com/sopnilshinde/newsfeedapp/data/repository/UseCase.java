package com.sopnilshinde.newsfeedapp.data.repository;

public interface UseCase<T> {
    T execute() throws RuntimeException;
}