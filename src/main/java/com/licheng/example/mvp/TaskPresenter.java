package com.licheng.example.mvp;



import java.util.List;


/**
 * Created by licheng on 29/4/16.
 */
public class TaskPresenter implements TaskDetailContract.Presenter,ServerHelper.DataLoadListener {

    private TaskDetailContract.View view;
    private ServerHelper serverHelper;

    //数据提供者 这里应该封装网络数据请求

    public TaskPresenter(TaskDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
        serverHelper = new ServerHelper();
        serverHelper.setListener(this);
    }

    /**
     * 请求网络数据
     * @param pageIndex 页码
     * @param pageSize 一页显示的数量
     */
    @Override
    public void loadBeauty(final int pageIndex, int pageSize) {
        serverHelper.getBeautyList(pageIndex,pageSize);
    }

    @Override
    public void start() {

    }

    @Override
    public void failure(Exception e) {
        view.hideProgress();
    }

    @Override
    public void success(List<Beauty> beautyList) {
        view.hideProgress();
        view.showBeauty(beautyList);
    }
}
