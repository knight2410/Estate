package com.estate.paging;

public class PageRequest implements Pageable {

    private int page;
    private int maxPageItem;

    public PageRequest(int page, int maxPageItem) {
        this.page = page;
        this.maxPageItem = maxPageItem;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getOffset() {
        return (this.page -1) * this.maxPageItem;
    }

    @Override
    public int getLimit() {
        return maxPageItem;
    }
}
