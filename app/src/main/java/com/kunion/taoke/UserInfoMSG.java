package com.kunion.taoke;



import com.kunion.taoke.model.remote.rest.info.UserGroupInfo;
import com.kunion.taoke.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class UserInfoMSG {

    private static UserInfoMSG instance;
    private List<UserGroupInfo> mObjects;
    private List<String> groups = new ArrayList<>();
    private String destGroup = null;

    private UserInfoMSG() {}
    public static UserInfoMSG getInstance() {
        if(instance == null) {
            instance = new UserInfoMSG();
        }
        return instance;
    }

    public void setTMObjects(List<UserGroupInfo> avObjects) {
        mObjects = avObjects;
        groups.clear();

        for (UserGroupInfo object:avObjects) {
            String groupName = object.getGroup();
            groups.add(groupName);
        }
    }

    public List<UserGroupInfo> getGroupObject() {
        return mObjects;
    }

    public List<String> getGroupPIDs() {
        List<String> pids = new ArrayList<>();
        for (UserGroupInfo object:mObjects) {
            String groupName = object.getPid();
            pids.add(groupName);
        }

        return pids;
    }


    public List<String> getGroupName() {
        List<String> groupNames = null;
        if(ListUtil.isNotBlank(mObjects)) {
            groupNames = new ArrayList<>();
            for (int i = 0; i < mObjects.size(); i++) {
                UserGroupInfo object = mObjects.get(i);
                groupNames.add(object.getGroup());
            }
        }

        return groupNames;
    }

    public String getTkaccByPosition(int position) {
        String tkacc = null;
        if(mObjects.size() > position) {
            tkacc = mObjects.get(position).getTkacc();
        }

        return tkacc;
    }

    public UserGroupInfo getGroupObjByName(String groupName) {
        if(ListUtil.isNotBlank(mObjects)) {
            for (int i = 0; i < mObjects.size(); i++) {
                UserGroupInfo object = mObjects.get(i);
                if(object.getGroup() != null && object.getGroup().equalsIgnoreCase(groupName)) {
                    return object;
                }
            }
        }

        return null;
    }

    public String getDestGroup() {
        return destGroup;
    }

    public void setDestGroup(String desGroup) {
        this.destGroup = desGroup;
    }
}
