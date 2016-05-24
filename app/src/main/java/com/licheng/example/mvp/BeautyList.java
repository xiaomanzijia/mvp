package com.licheng.example.mvp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by licheng on 16/1/16.
 */
public class BeautyList {
    @SerializedName("beautylist")
    private List<Beauty> list;

    public List<Beauty> getList() {
        return list;
    }

    public void setList(List<Beauty> list) {
        this.list = list;
    }
}
