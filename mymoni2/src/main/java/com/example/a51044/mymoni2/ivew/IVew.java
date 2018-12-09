package com.example.a51044.mymoni2.ivew;

public interface IVew<T> {
    void success(T user);

    void error (T error);
}
