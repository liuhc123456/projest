package com.example.lenovo.apis;

import com.example.lenovo.beans.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";
    @GET("1")
    Observable<Bean> getData();
}
