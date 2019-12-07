package com.cyb.blogserver.domain;

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
    private String desc;

    /**
     * 内容
     */
    private String content;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}
