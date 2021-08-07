package com.wx.main.Model;

public class Posting {
    private int article_id;
    private String article_title;
    private String article_content;
    private String article_image;
    private String sort_id;


    public Posting() {
    }

    public Posting(int article_id, String article_title, String article_content, String article_image, String sort_id) {
        this.article_id = article_id;
        this.article_title = article_title;
        this.article_content = article_content;
        this.article_image = article_image;
        this.sort_id = sort_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_image() {
        return article_image;
    }

    public void setArticle_image(String article_image) {
        this.article_image = article_image;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_image='" + article_image + '\'' +
                ", sort_id='" + sort_id + '\'' +
                '}';
    }
}
