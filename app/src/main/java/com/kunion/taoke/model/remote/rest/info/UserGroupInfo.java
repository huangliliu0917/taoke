package com.kunion.taoke.model.remote.rest.info;

/**
 * Created by Administrator on 2017/6/15.
 *
 *
 *
    public static final String Group= "group";
     public static final String PID = "pid";
     public static final String Tkacc = "tkacc";
     public static final String Peoplecount = "peoplecount";
     public static final String Owners = "owners";

 */
public class UserGroupInfo extends AVBaseBean {

    private String group;
    private String pid;
    private String tkacc;
    private int peoplecount;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTkacc() {
        return tkacc;
    }

    public void setTkacc(String tkacc) {
        this.tkacc = tkacc;
    }

    public int getPeoplecount() {
        return peoplecount;
    }

    public void setPeoplecount(int peoplecount) {
        this.peoplecount = peoplecount;
    }
}
