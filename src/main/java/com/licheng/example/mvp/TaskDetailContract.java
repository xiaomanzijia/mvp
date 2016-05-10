package com.licheng.example.mvp;

import java.util.List;

/**
 * Created by licheng on 29/4/16.
 */
public interface TaskDetailContract {

    //http://www.diandidaxue.com:8080/apiServer.do?opcode=getBeauty&pageNum=1&numPerPage=5
    interface Presenter extends BasePresenter{
        void loadBeauty(int pageIndex, int pageSize);
    }

    interface View extends BaseView<Presenter>{
        void showProgress();
        void hideProgress();
        void showBeauty(List<Beauty> beautyList);
    }
}
