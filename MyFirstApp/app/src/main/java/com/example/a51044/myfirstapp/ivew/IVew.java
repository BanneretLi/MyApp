package com.example.a51044.myfirstapp.ivew;

public interface IVew<T> {
    void getSuccess(T data);
    void getError(T error);
}
