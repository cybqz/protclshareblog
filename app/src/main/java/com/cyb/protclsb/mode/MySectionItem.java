package com.cyb.protclsb.mode;

import com.qmuiteam.qmui.widget.section.QMUISection;

/**
 * SectionItem
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class MySectionItem implements QMUISection.Model<MySectionItem>{

    private final String id;
    private final String name;
    private final String introduce;

    public MySectionItem(String id,String name,String introduce){
        this.id = id;
        this.name = name;
        this.introduce = introduce;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    @Override
    public MySectionItem cloneForDiff() {
        return new MySectionItem(getId(),getName(),getIntroduce());
    }

    @Override
    public boolean isSameItem(MySectionItem other) {
        return id == other.id || (id != null && id.equals(other.id));
    }

    @Override
    public boolean isSameContent(MySectionItem other) {
        return true;
    }
}
