package com.dev.admin.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * Created by yuboon on 2019/02/24
 */
public class PageVo implements Serializable {

    private int page;

    private int limit;

    private List<?> list;

    private long total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
