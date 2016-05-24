package com.licheng.example.mvp;

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

    public void getBeautyList(int pageIndex, int pageSize){
        String url = "http://www.diandidaxue.com:8080/apiServer.do";
        Map<String, String> params = new HashMap<String, String>();
        params.put("opcode", "getBeauty");
        params.put("pageNum", String.valueOf(pageIndex));
        params.put("numPerPage", String.valueOf(pageSize));
        OkHttpUtils.post().url(url).params(params).build().execute(new ListBeautyCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                listener.failure(e);
            }

            @Override
            public void onResponse(List<Beauty> response) {
                listener.success(response);
            }
        });
    }

    public interface DataLoadListener{
        void failure(Exception e);
        void success(List<Beauty> beautyList);
    }

    public void setListener(DataLoadListener listener) {
        this.listener = listener;
    }
}
