package com.zhangjiebo.grouppurchase.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
public class Advertisement02 {

    /**
     * content : Html/Milk
     * advert_type : 8
     * advert_id : 120400
     * title : 伊利：一分钱抢奶
     * update_time : 2015-05-16 07:28:51
     * img_big :
     * advert_name : 150413171119
     * img_small :
     * img_mid : http://d2.lashouimg.com/wirelessimg/1431732515_zhuanti.jpg
     * advert_position : 1
     */

    private List<HeaderBannerEntity> header_banner;
    /**
     * content : 10918872
     * advert_type : 1
     * advert_id : 130844
     * title : 宏图本利丰
     * update_time : 2015-05-20 11:47:31
     * img_big :
     * advert_name : 150520113444
     * img_small :
     * img_mid : http://d2.lashouimg.com/wirelessimg/1432092723_zhuanti.jpg
     * advert_position : 2
     */

    private List<CenterBannerEntity> center_banner;
    private List<?> down_slip_banner;
    private List<?> down_three_banner;
    private List<?> up_three_banner;
    private List<?> down_two_banner;
    private List<?> up_slip_banner;
    private List<?> up_two_banner;

    public void setHeader_banner(List<HeaderBannerEntity> header_banner) {
        this.header_banner = header_banner;
    }

    public void setCenter_banner(List<CenterBannerEntity> center_banner) {
        this.center_banner = center_banner;
    }

    public void setDown_slip_banner(List<?> down_slip_banner) {
        this.down_slip_banner = down_slip_banner;
    }

    public void setDown_three_banner(List<?> down_three_banner) {
        this.down_three_banner = down_three_banner;
    }

    public void setUp_three_banner(List<?> up_three_banner) {
        this.up_three_banner = up_three_banner;
    }

    public void setDown_two_banner(List<?> down_two_banner) {
        this.down_two_banner = down_two_banner;
    }

    public void setUp_slip_banner(List<?> up_slip_banner) {
        this.up_slip_banner = up_slip_banner;
    }

    public void setUp_two_banner(List<?> up_two_banner) {
        this.up_two_banner = up_two_banner;
    }

    public List<HeaderBannerEntity> getHeader_banner() {
        return header_banner;
    }

    public List<CenterBannerEntity> getCenter_banner() {
        return center_banner;
    }

    public List<?> getDown_slip_banner() {
        return down_slip_banner;
    }

    public List<?> getDown_three_banner() {
        return down_three_banner;
    }

    public List<?> getUp_three_banner() {
        return up_three_banner;
    }

    public List<?> getDown_two_banner() {
        return down_two_banner;
    }

    public List<?> getUp_slip_banner() {
        return up_slip_banner;
    }

    public List<?> getUp_two_banner() {
        return up_two_banner;
    }

    public static class HeaderBannerEntity {
        private String content;
        private String advert_type;
        private String advert_id;
        private String title;
        private String update_time;
        private String img_big;
        private String advert_name;
        private String img_small;
        private String img_mid;
        private String advert_position;

        public void setContent(String content) {
            this.content = content;
        }

        public void setAdvert_type(String advert_type) {
            this.advert_type = advert_type;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public void setImg_big(String img_big) {
            this.img_big = img_big;
        }

        public void setAdvert_name(String advert_name) {
            this.advert_name = advert_name;
        }

        public void setImg_small(String img_small) {
            this.img_small = img_small;
        }

        public void setImg_mid(String img_mid) {
            this.img_mid = img_mid;
        }

        public void setAdvert_position(String advert_position) {
            this.advert_position = advert_position;
        }

        public String getContent() {
            return content;
        }

        public String getAdvert_type() {
            return advert_type;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public String getTitle() {
            return title;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public String getImg_big() {
            return img_big;
        }

        public String getAdvert_name() {
            return advert_name;
        }

        public String getImg_small() {
            return img_small;
        }

        public String getImg_mid() {
            return img_mid;
        }

        public String getAdvert_position() {
            return advert_position;
        }
    }

    public static class CenterBannerEntity {
        private String content;
        private String advert_type;
        private String advert_id;
        private String title;
        private String update_time;
        private String img_big;
        private String advert_name;
        private String img_small;
        private String img_mid;
        private String advert_position;

        public void setContent(String content) {
            this.content = content;
        }

        public void setAdvert_type(String advert_type) {
            this.advert_type = advert_type;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public void setImg_big(String img_big) {
            this.img_big = img_big;
        }

        public void setAdvert_name(String advert_name) {
            this.advert_name = advert_name;
        }

        public void setImg_small(String img_small) {
            this.img_small = img_small;
        }

        public void setImg_mid(String img_mid) {
            this.img_mid = img_mid;
        }

        public void setAdvert_position(String advert_position) {
            this.advert_position = advert_position;
        }

        public String getContent() {
            return content;
        }

        public String getAdvert_type() {
            return advert_type;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public String getTitle() {
            return title;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public String getImg_big() {
            return img_big;
        }

        public String getAdvert_name() {
            return advert_name;
        }

        public String getImg_small() {
            return img_small;
        }

        public String getImg_mid() {
            return img_mid;
        }

        public String getAdvert_position() {
            return advert_position;
        }
    }
}
