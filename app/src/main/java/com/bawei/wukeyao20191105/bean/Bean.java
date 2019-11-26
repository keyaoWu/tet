package com.bawei.wukeyao20191105.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

/**
 * 功能：Bean类
 * 作者：武柯耀
 * 当前日期：2019/11/5
 * 当前时间：9:38
 */
public class Bean  {
    /**
     * code : 200
     * bannerdata : [{"imageurl":"http://blog.zhaoliang5156.cn/api/images/banner01.jpg"},{"imageurl":"http://blog.zhaoliang5156.cn/api/images/banner02.jpg"},{"imageurl":"http://blog.zhaoliang5156.cn/api/images/banner03.jpg"},{"imageurl":"http://blog.zhaoliang5156.cn/api/images/banner04.jpg"},{"imageurl":"http://blog.zhaoliang5156.cn/api/images/banner05.jpg"}]
     * griddata : [{"title":"Yves Saint Laurent 圣罗兰黑管水光唇釉","price":"288","imageurl":"http://blog.zhaoliang5156.cn/api/images/shop01.jpeg"},{"title":"LADUREE 拉杜丽经典玫瑰花瓣腮红","price":"888","imageurl":"http://blog.zhaoliang5156.cn/api/images/shop02.png"},{"title":"BOBBI BROWN 芭比波朗炙恋唇膏","price":"240","imageurl":"http://blog.zhaoliang5156.cn/api/images/shop03.png"},{"title":"韩国nounours 北极熊看护宝宝小夜灯多功能看灯","price":"192","imageurl":"http://blog.zhaoliang5156.cn/api/images/shop04.jpeg"}]
     */

    private String code;
    private List<BannerdataBean> bannerdata;
    private List<GriddataBean> griddata;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BannerdataBean> getBannerdata() {
        return bannerdata;
    }

    public void setBannerdata(List<BannerdataBean> bannerdata) {
        this.bannerdata = bannerdata;
    }

    public List<GriddataBean> getGriddata() {
        return griddata;
    }

    public void setGriddata(List<GriddataBean> griddata) {
        this.griddata = griddata;
    }

    public static class BannerdataBean extends SimpleBannerInfo {
        /**
         * imageurl : http://blog.zhaoliang5156.cn/api/images/banner01.jpg
         */

        private String imageurl;

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }

    public static class GriddataBean {
        /**
         * title : Yves Saint Laurent 圣罗兰黑管水光唇釉
         * price : 288
         * imageurl : http://blog.zhaoliang5156.cn/api/images/shop01.jpeg
         */

        private String title;
        private String price;
        private String imageurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }
}
