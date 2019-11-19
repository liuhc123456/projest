package com.example.lenovo.apis;

public interface ResultCallBack <T>{
    void  onSuccess(T bean);
    void onFail(String error);
}
