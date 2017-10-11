package com.kunion.taoke.model.remote.rest.info;

/**
 * Created by Administrator on 2017/6/15.
 *
 *
 public static final String DB="item";
 public static final String CPrice="c_price";
 public static final String Type="type";
 public static final String PrePrice="pre_price";
 public static final String CRate="c_rate";
 public static final String CurPricce="cur_price";
 public static final String Title="title";
 public static final String Img="img";
 public static final String Link="link";
 public static final String CLink="clink";
 }

 */

public class ItemInfo extends AVBaseBean {

    private float c_price;
    private String type;
    private float pre_price;
    private String c_rate;
    private float cur_price;
    private String title;
    private String img;
    private String link;
    private String clink;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getClink() {
        return clink;
    }

    public void setClink(String clink) {
        this.clink = clink;
    }

    public float getC_price() {
        return c_price;
    }

    public void setC_price(float c_price) {
        this.c_price = c_price;
    }

    public float getPre_price() {
        return pre_price;
    }

    public void setPre_price(float pre_price) {
        this.pre_price = pre_price;
    }

    public String getC_rate() {
        return c_rate;
    }

    public void setC_rate(String c_rate) {
        this.c_rate = c_rate;
    }

    public float getCur_price() {
        return cur_price;
    }

    public void setCur_price(float cur_price) {
        this.cur_price = cur_price;
    }
}
