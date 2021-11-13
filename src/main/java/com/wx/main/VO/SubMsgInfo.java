package com.wx.main.VO;

public class SubMsgInfo {
    private int article_id;
    private String user_openid;
    private String template_id;
    private String page;
    private String data;

    public SubMsgInfo() {
    }

    public SubMsgInfo(int article_id, String user_openid, String template_id, String page, String data) {
        this.article_id = article_id;
        this.user_openid = user_openid;
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

    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
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

    @Override
    public String toString() {
        return "SubMsgInfo{" +
                "article_id=" + article_id +
                ", user_openid='" + user_openid + '\'' +
                ", template_id='" + template_id + '\'' +
                ", page='" + page + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
