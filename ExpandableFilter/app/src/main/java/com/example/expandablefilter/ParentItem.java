package com.example.expandablefilter;

import java.util.ArrayList;

public class ParentItem {
    public String parentName;
    public ArrayList<ChildItem> childItems;
    boolean expandable;

    public ParentItem() {
        this.expandable = true;
    }
}
