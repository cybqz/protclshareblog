package com.cyb.blogserver.domain;

import java.util.Date;
import java.util.List;

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

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 章节集合
     */
    private List<Chapter> chapterList;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
