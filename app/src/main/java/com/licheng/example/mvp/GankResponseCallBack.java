package com.licheng.example.mvp;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by licheng on 17/1/17.
 */

public abstract class GankResponseCallBack extends Callback<List<Gank>> {

    @Override
    public List<Gank> parseNetworkResponse(Response response) throws Exception {
        String responseBody = response.body().string();
        GankResponse gankResponse = new Gson().fromJson(responseBody, GankResponse.class);
        if(null != gankResponse && gankResponse.getResults().size() != 0){
            return gankResponse.getResults();
        }
        return null;
    }
}
