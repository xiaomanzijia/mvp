package com.licheng.example.mvp;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by licheng on 29/4/16.
 */
public class ServerHelper {

    private DataLoadListener listener;

    public void getGankList(int pageSize, int pageIndex){
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + pageSize + "/" + pageIndex;
        Log.i("test", url);
        OkHttpUtils.get().url(url).build().execute(new GankResponseCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                listener.failure(e);
                Log.i("test", "errorerror");
            }

            @Override
            public void onResponse(List<Gank> response) {
                Log.i("test", "数据获取成功 "  + new Gson().toJson(response, List.class));
                listener.success(response);
            }
        });
    }

    public interface DataLoadListener{
        void failure(Exception e);
        void success(List<Gank> gankList);
    }

    public void setListener(DataLoadListener listener) {
        this.listener = listener;
    }
}
