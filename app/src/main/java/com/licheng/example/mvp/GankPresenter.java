package com.licheng.example.mvp;



import java.util.List;


/**
 * Created by licheng on 29/4/16.
 */
public class GankPresenter implements GankContract.Presenter,ServerHelper.DataLoadListener {

    private GankContract.View view;
    private ServerHelper serverHelper;

    //数据提供者 这里应该封装网络数据请求

    public GankPresenter(GankContract.View view) {
        this.view = view;
        view.setPresenter(this);
        serverHelper = new ServerHelper();
        serverHelper.setListener(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void failure(Exception e) {
        view.hideProgress();
    }

    @Override
    public void success(List<Gank> gankList) {
        view.hideProgress();
        view.showGank(gankList);
    }


    @Override
    public void loadGank(int pageIndex, int pageSize) {
        serverHelper.getGankList(pageIndex, pageSize);
    }
}
