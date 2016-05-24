package com.licheng.example.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by licheng on 29/4/16.
 */
public class BeautyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        BeautyFragment fragment = new BeautyFragment();
        if(fragment == null){
            fragment = BeautyFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.container);

    }
}
