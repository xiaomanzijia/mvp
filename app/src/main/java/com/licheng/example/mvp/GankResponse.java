package com.licheng.example.mvp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by licheng on 17/1/17.
 */

public class GankResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("results")
    private List<Gank> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }
}
