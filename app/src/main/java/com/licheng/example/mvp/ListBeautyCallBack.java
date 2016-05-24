package com.licheng.example.mvp;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by licheng on 16/2/16.
 */
public abstract class ListBeautyCallBack extends Callback<List<Beauty>> {
    private final String TAG = "ListBeautyCallBack";
    @Override
    public List<Beauty> parseNetworkResponse(Response response) throws Exception {
        String htmlStr = response.body().string();
        Log.i(TAG,htmlStr);
        String responseBody = "{" +
                "\"beautylist\":" + htmlStr +"}";
        Log.i("beautylist",responseBody);
        BeautyList list = new Gson().fromJson(responseBody,BeautyList.class);
        return list.getList();
    }
}
