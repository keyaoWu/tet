package com.bawei.wukeyao20191105;

/**
 * 功能：Contats类
 * 作者：武柯耀
 * 当前日期：2019/11/5
 * 当前时间：9:00
 */
public interface Contats {

    interface IModel{
        void getInfo(String url,iCallBack iCallBack);
        void postInfo(String url,iCallBack iCallBack);
    }

    interface  IView{
        void onSuccess(String json);
        void onFaild(String error);
    }

    interface Persenter{
        void  onAttach(IView iView);
        void  onStartResult(String url);
        void onDeattch();
    }
    interface iCallBack{
        void onSuccess(String json);
        void onFaild(String error);
    }
}
