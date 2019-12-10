package com.cyb.blogserver.domain;

import java.util.Date;

/**
 * 章节
 */
public class Chapter {

    /**
     * ID
     */
    private String id;

    /**
     * 关联技术学习
     */
    private String teclearningId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String img;

    /**
     * 创建日期
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeclearningId() {
        return teclearningId;
    }

    public void setTeclearningId(String teclearningId) {
        this.teclearningId = teclearningId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
