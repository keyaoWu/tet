package com.bawei.wukeyao20191105;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 功能：NetUntil类
 * 作者：武柯耀
 * 当前日期：2019/11/5
 * 当前时间：8:45
 */
public class NetUntil {
    //单例模式
    private NetUntil() {
    }

    private static  class NetUntilSigle{
        private static NetUntil netUntil = new NetUntil();
    }

    public static NetUntil getInstance() {
        return NetUntilSigle.netUntil;
    }

    //判断是否有网
    public  boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }

    }


    //判断是否是WIFI
    public  boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }

    }


    //判断是否是移动网
    public  boolean isMobile(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }

    }

    //流转字符串
    public  String io2String(InputStream inputStream){
        int len =-1;
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String json ="";
        try {
            while ((len = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }

            byte[] bytes1 = outputStream.toByteArray();
             json = new String(bytes1);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关流
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return json;
    }


    //流转bitmap
    public  Bitmap io2Bitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }


    //doGet请求json
    public void  doGet(final String httpUrl, final myCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream= null;
                String json= "";
                try {
                    URL url = new URL(httpUrl);

                     httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("GET");

                    httpURLConnection.setReadTimeout(5000);

                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200){
                         inputStream = httpURLConnection.getInputStream();
                         json = io2String(inputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //关流
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.isEmpty()){
                    myCallBack.onErrorJson("网络错误");
                }else {
                    myCallBack.onDoGetSuccess(s);
                }
            }
        }.execute();
    }


    //doGetPhoto请求Bitmap
    public void doGetPhoto(final String httpUrl, final myCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream= null;
                Bitmap bitmap= null;
                try {
                    URL url = new URL(httpUrl);

                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("GET");

                    httpURLConnection.setReadTimeout(5000);

                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200){
                        inputStream = httpURLConnection.getInputStream();
                         bitmap = io2Bitmap(inputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //关流
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.onDoGetPhotoSuccess(bitmap);
            }
        }.execute();
    }


    //封装接口
    public  interface  myCallBack{
        void  onDoGetSuccess(String json);
        void onDoGetPhotoSuccess(Bitmap bitmap);
        void onErrorJson(String error);
        void onErrorBitmap(String error);
    }
}
