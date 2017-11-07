package com.kunion.taoke.model.remote.rest.info;

import java.sql.Date;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SaleInfo extends AVBaseBean {
    private float payprice;
    private float ei;
    private String name;
    private String orderid;
    private String pid;
    private Date ctime;
    private String tkacc;
    private int count;
    private float incomerate;
    private String paystate;
    private String itemid;
    private String seller;
    private Date stime;
    public float getPayprice() {
        return payprice;
    }

    public void setPayprice(float payprice) {
        this.payprice = payprice;
    }

    public float getEi() {
        return ei;
    }

    public void setEi(float ei) {
        this.ei = ei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getTkacc() {
        return tkacc;
    }

    public void setTkacc(String tkacc) {
        this.tkacc = tkacc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getIncomerate() {
        return incomerate;
    }

    public void setIncomerate(float incomerate) {
        this.incomerate = incomerate;
    }

    public String getPaystate() {
        return paystate;
    }

    public void setPaystate(String paystate) {
        this.paystate = paystate;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }
}
