package com.diegovolantino.mobile.randomuserexercise.data.network.api;

import com.diegovolantino.mobile.randomuserexercise.data.model.RandomUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */

public interface RandomUserApi {

    @GET("/api/")
    Call<RandomUser> getResults(@Query("results") int amount);
}
