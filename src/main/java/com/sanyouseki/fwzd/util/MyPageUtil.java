package com.sanyouseki.fwzd.util;

import com.sanyouseki.fwzd.entity.Image;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyPageUtil {
    private static final int DEFAULT_CURRENT_PAGE = 1;

    private static final int DEFAULT_LIMIT_COUNT = 6;

    //元素个数
    private int itemCount;

    //当前页数
    private int currentPage = DEFAULT_CURRENT_PAGE;

    //每页显示个数
    private int limitCount = DEFAULT_LIMIT_COUNT;

    private List<Integer> itemIndex = new ArrayList<Integer>();

    private void setItemIndex() {
        itemIndex.clear();
        int start = (this.currentPage - 1) * limitCount;
        int size = itemCount - start;
        if (size > limitCount) {
            size = limitCount;
        }

        for (int i = 0; i < size; i++) {
            itemIndex.add(start + i);
        }
    }

    public List<Integer> getItemIndex(){
        return itemIndex;
    }

    public MyPageUtil(int itemCount, int currentPage, int limitCount) {
        this.itemCount = itemCount;
        this.currentPage = currentPage;
        this.limitCount = limitCount;
        setItemIndex();
    }

    public MyPageUtil(int itemCount, int limitCount) {
        this.itemCount = itemCount;
        this.limitCount = limitCount;
        setItemIndex();
    }

    public MyPageUtil(int itemCount) {
        this.itemCount = itemCount;
        setItemIndex();
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }
}
