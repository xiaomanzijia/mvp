package com.licheng.example.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by licheng on 29/4/16.
 */
public class GankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        GankFragment fragment  = GankFragment.newInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.container);

        //设置presenter
        new GankPresenter(fragment);
    }
}
