package com.bawei.wukeyao20191105.persenter;

import com.bawei.wukeyao20191105.Contats;
import com.bawei.wukeyao20191105.model.ModelImpl;

/**
 * 功能：Persenter类
 * 作者：武柯耀
 * 当前日期：2019/11/5
 * 当前时间：9:06
 */
public class Persenter implements Contats.Persenter {
    private Contats.IModel mModel;
    private Contats.IView mView;
    @Override
    public void onAttach(Contats.IView iView) {
        this.mView = iView;
        mModel = new ModelImpl();
    }

    @Override
    public void onStartResult(String url) {
      mModel.getInfo(url, new Contats.iCallBack() {
          @Override
          public void onSuccess(String json) {
              mView.onSuccess(json);
          }

          @Override
          public void onFaild(String error) {
            mView.onFaild(error);
          }
      });
    }

    @Override
    public void onDeattch() {
      if (mModel!=null){
          mModel = null;
      }
      if (mView!= null){
          mView = null;
      }
    }
}
