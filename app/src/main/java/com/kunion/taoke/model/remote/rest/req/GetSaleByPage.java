package com.kunion.taoke.model.remote.rest.req;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class GetSaleByPage {
    private int page;
    private int size;
    private List<String> groups;

    public GetSaleByPage(int page, int size, List<String> groups) {
        this.page = page;
        this.size = size;
        this.groups = groups;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
