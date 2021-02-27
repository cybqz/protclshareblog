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

    private final String text;

    public MySectionItem(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public MySectionItem cloneForDiff() {
        return new MySectionItem(getText());
    }

    @Override
    public boolean isSameItem(MySectionItem other) {
        return text == other.text || (text != null && text.equals(other.text));
    }

    @Override
    public boolean isSameContent(MySectionItem other) {
        return true;
    }
}
