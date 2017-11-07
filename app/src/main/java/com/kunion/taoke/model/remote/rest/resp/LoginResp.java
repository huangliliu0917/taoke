package com.kunion.taoke.model.remote.rest.resp;

import com.kunion.taoke.model.remote.rest.BaseBean;

/**
 * Created by Administrator on 2017/10/10.
 */

public class LoginResp extends BaseBean{
    private String name;
    private String role;
    private String tkacc;
    private String tkpid;
    private int rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTkacc() {
        return tkacc;
    }

    public void setTkacc(String tkacc) {
        this.tkacc = tkacc;
    }

    public String getTkpid() {
        return tkpid;
    }

    public void setTkpid(String tkpid) {
        this.tkpid = tkpid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
