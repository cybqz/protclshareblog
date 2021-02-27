package com.cyb.protclsb.mode;

import com.qmuiteam.qmui.widget.section.QMUISection;

/**
 * SectionHeader
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class MySectionHeader implements QMUISection.Model<MySectionHeader>{

    private final String text;

    public MySectionHeader(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public MySectionHeader cloneForDiff() {
        return new MySectionHeader(getText());
    }

    @Override
    public boolean isSameItem(MySectionHeader other) {
        return text == other.text || (text != null && text.equals(other.text));
    }

    @Override
    public boolean isSameContent(MySectionHeader other) {
        return true;
    }
}
