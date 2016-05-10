package com.licheng.example.mvp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by licheng on 16/1/16.
 */
public class Beauty {
    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("fileType")
    private String fileType;

    @SerializedName("id")
    private String id;

    @SerializedName("isHide")
    private String isHide;

    @SerializedName("url")
    private String url;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
