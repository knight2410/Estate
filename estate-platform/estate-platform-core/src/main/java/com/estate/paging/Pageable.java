package com.estate.paging;

public interface Pageable {
    int getPage();
    int getOffset();
    int getLimit();
}
