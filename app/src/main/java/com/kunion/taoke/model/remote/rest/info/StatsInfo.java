package com.kunion.taoke.model.remote.rest.info;

/**
 * Created by Administrator on 2017/11/7.
 */

public class StatsInfo extends AVBaseBean {

    private float rec;
    private String adzoneId;
    private int mixClick;
    private float alipayRec;
    private int alipayNum;
    private String tkacc;
    private String thedate;

    public float getRec() {
        return rec;
    }

    public void setRec(float rec) {
        this.rec = rec;
    }

    public String getAdzoneId() {
        return adzoneId;
    }

    public void setAdzoneId(String adzoneId) {
        this.adzoneId = adzoneId;
    }

    public int getMixClick() {
        return mixClick;
    }

    public void setMixClick(int mixClick) {
        this.mixClick = mixClick;
    }

    public float getAlipayRec() {
        return alipayRec;
    }

    public void setAlipayRec(float alipayRec) {
        this.alipayRec = alipayRec;
    }

    public int getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(int alipayNum) {
        this.alipayNum = alipayNum;
    }

    public String getTkacc() {
        return tkacc;
    }

    public void setTkacc(String tkacc) {
        this.tkacc = tkacc;
    }

    public String getThedate() {
        return thedate;
    }

    public void setThedate(String thedate) {
        this.thedate = thedate;
    }
}
