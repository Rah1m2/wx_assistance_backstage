package com.wx.main.POJO;

public class QueryParams {
    private int article_id;
    private int tab_id;
    private int sort_id;
    private int pageNum;
    private int pageSize;

    public QueryParams() {
    }

    public QueryParams(int article_id, int tab_id, int sort_id, int pageNum, int pageSize) {
        this.article_id = article_id;
        this.tab_id = tab_id;
        this.sort_id = sort_id;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getTab_id() {
        return tab_id;
    }

    public void setTab_id(int tab_id) {
        this.tab_id = tab_id;
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

    @Override
    public String toString() {
        return "QueryParams{" +
                "tab_id=" + tab_id +
                ", sort_id=" + sort_id +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
