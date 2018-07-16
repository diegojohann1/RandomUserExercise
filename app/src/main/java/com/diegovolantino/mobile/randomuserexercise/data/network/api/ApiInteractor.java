package com.diegovolantino.mobile.randomuserexercise.data.network.api;

import android.util.Log;

import com.diegovolantino.mobile.randomuserexercise.data.model.RandomUser;
import com.diegovolantino.mobile.randomuserexercise.data.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */

public class ApiInteractor {
   private RandomUserApi rUserApi;
   private static final int MAX_TOP = 50;
    private Result interactorResultData;


    public ApiInteractor() {
        this.rUserApi= ApiClient.getClient().create(RandomUserApi.class);
    }

    public void getResults(final ResponseCallback cllbk){
        this.rUserApi.getResults(MAX_TOP).enqueue(new Callback<RandomUser>() {
            @Override
            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
//                Log.v("response", response.body().toString());
                if(cllbk!=null)
                    cllbk.onDataReady(response.body());
            }

            @Override
            public void onFailure(Call<RandomUser> call, Throwable t) {
                Log.e("failure","=>" + t.getMessage());
                if(cllbk!=null)
                    cllbk.onError(t);
            }
        });
    }

    public Result getInteractorResultData() {
        if(interactorResultData != null) {
            return interactorResultData;
        }
        return null;
    }

    public interface ResponseCallback{
        void onDataReady(RandomUser response);
        void onError(Throwable t);
    }
}