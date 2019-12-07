package com.cyb.blogserver.domain;

/**
 * 技术学习
 */
public class TecLearning {

    /**
     * ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别
     */
    private String category;

    /**
     * 标签
     */
    private String tag;

    /**
     * 前言
     */
    private String preface;

    /**
     * 图片
     */
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
