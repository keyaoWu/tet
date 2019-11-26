package com.bawei.wukeyao20191105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bawei.wukeyao20191105.adapter.MyAdapter;
import com.bawei.wukeyao20191105.bean.Bean;
import com.bawei.wukeyao20191105.persenter.Persenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity implements Contats.IView {
    private Contats.Persenter mpersenter;
    private XBanner xBanner;
    private GridView gridView;
   private String url = "http://blog.zhaoliang5156.cn/api/shop/jingdong.json";
   private  List<Bean.GriddataBean> mlist = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mpersenter = new Persenter();
        mpersenter.onAttach(this);
        mpersenter.onStartResult(url);
        myAdapter = new MyAdapter(MainActivity.this, mlist);
        gridView.setAdapter(myAdapter);
    }

    @Override
    public void onSuccess(String json) {
    //解析
        final List<Bean.BannerdataBean> bannerdata = new Gson().fromJson(json, Bean.class).getBannerdata();
        xBanner.setBannerData(bannerdata);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(MainActivity.this).load(bannerdata.get(position).getImageurl()).into((ImageView) view);
            }
        });

        List<Bean.GriddataBean> griddata = new Gson().fromJson(json, Bean.class).getGriddata();
        mlist.addAll(griddata);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaild(String error) {

    }

    private void initView() {
        xBanner = (XBanner) findViewById(R.id.xb);
        gridView = (GridView) findViewById(R.id.gird);
    }
}
