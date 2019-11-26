package com.bawei.wukeyao20191105.model;

import android.graphics.Bitmap;

import com.bawei.wukeyao20191105.Contats;
import com.bawei.wukeyao20191105.NetUntil;

/**
 * 功能：ModelImpl类
 * 作者：武柯耀
 * 当前日期：2019/11/5
 * 当前时间：9:04
 */
public class ModelImpl implements Contats.IModel {
    @Override
    public void getInfo(final String url, final Contats.iCallBack iCallBack) {
        NetUntil.getInstance().doGet(url, new NetUntil.myCallBack() {
            @Override
            public void onDoGetSuccess(String json) {
                iCallBack.onSuccess(json);
            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {

            }

            @Override
            public void onErrorJson(String error) {
             iCallBack.onFaild(error);
            }

            @Override
            public void onErrorBitmap(String error) {

            }
        });
    }

    @Override
    public void postInfo(String url, Contats.iCallBack iCallBack) {

    }
}
