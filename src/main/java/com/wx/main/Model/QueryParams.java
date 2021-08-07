package com.wx.main.Model;

public class QueryParams {
    private String query;
    private int sort_id;
    private int pageNum;
    private int pageSize;

    public QueryParams() {
    }

    public QueryParams(String query, int sort_id, int pageNum, int pageSize) {
        this.query = query;
        this.sort_id = sort_id;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
