package com.licheng.example.mvp;

import java.util.List;

/**
 * Created by licheng on 29/4/16.
 */
public interface GankContract {

    interface Presenter extends BasePresenter{
        void loadGank(int pageIndex, int pageSize);
    }

    interface View extends BaseView<Presenter>{
        void showProgress();
        void hideProgress();
        void showGank(List<Gank> gankList);
    }
}
