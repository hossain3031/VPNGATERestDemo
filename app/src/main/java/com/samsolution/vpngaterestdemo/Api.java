package com.samsolution.vpngaterestdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //http://manage.v4v.info/api/?getserverlist&token=QklseUVlTHRYTTdEUThYTTdEUThiZVA1Unh5RWVMdHdBQUFBRXdBQUFBRUdiZVA1UnhHQkls
    String BASE_URL = "http://manage.v4v.info/";

    @GET("api/}")
    Call<List<Result>> getServerResult(@Query("getserverlist&token") String token);

}
