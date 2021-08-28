package com.wx.main.VO;

public class SubMsgInfo {
    private int article_id;
    private String template_id;
    private String page;
    private String data;

    public SubMsgInfo() {
    }

    public SubMsgInfo(int article_id, String template_id, String page, String data) {
        this.article_id = article_id;
        this.template_id = template_id;
        this.page = page;
        this.data = data;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
