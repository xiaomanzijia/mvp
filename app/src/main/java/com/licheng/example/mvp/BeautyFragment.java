package com.licheng.example.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 29/4/16.
 */
public class BeautyFragment extends Fragment implements TaskDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

    private TaskDetailContract.Presenter presenter;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private BeautyAdapter adapter;
    private List<Beauty> beautyList;
    private int pageIndex = 1;
    private int pageSize = 8;
    private boolean isLastPage = false;
    private int lastVisibleItem = 0;
    private LinearLayoutManager linearLayoutManager;

    public static BeautyFragment newInstance(){
        return new BeautyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TaskPresenter(this);
        beautyList = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tabRecyler);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.tabSwipeRefresh);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);

        //设置下拉刷新
        refreshLayout.setColorSchemeColors(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);
        //第一次进入界面时候加载进度条
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new BeautyAdapter(beautyList,getActivity());
        recyclerView.setAdapter(adapter);

        presenter.loadBeauty(1,8);


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    Log.i("pageIndex",pageIndex+"");
                    //根据类目网络请求数据
                    if(!isLastPage){
                        presenter.loadBeauty(pageIndex,pageSize);
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showBeauty(List<Beauty> list) {
        ActivityUtils.checkNotNull(list);
        if(list != null){
            if(pageIndex == 1){
                beautyList.clear();
                beautyList.addAll(list);
                isLastPage = false;
                pageIndex ++;
            }else if(list                                                                                                                                                                                                                   .size() == pageSize){
                beautyList.addAll(list);
                isLastPage = false;
                pageIndex ++;
            }else {
                beautyList.addAll(list);
                isLastPage = true;
            }
        }
        adapter.notifyDataSetChanged();


    }


    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        presenter.loadBeauty(pageIndex,pageSize);
    }
}
