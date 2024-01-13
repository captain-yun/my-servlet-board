package com.kitri.myservletboard.data;

public class Pagination {
    private int page;
    private int maxRecordsPerPage = 10;
    private int maxPagesOnScreen = 5;
    private int startIndex = 0;
    private int totalRecords = 0;
    private boolean hasNext = false;
    private boolean hasPrev = false;
    private int startPageOnScreen = 1;
    private int endPageOnScreen = this.maxPagesOnScreen;

    public void calcPagination() {
        // 페이지네이션 정보 계산
        // 1, 2, 3, 4, 5 -> start = 1, end = ??
        // 6, 7, 8, 9, 10 -> start = 6, end = ??

        int totalPages = (int)(Math.ceil((double)this.totalRecords / this.maxRecordsPerPage));

        this.startPageOnScreen
                = ((int)Math.ceil((double)this.page / this.maxPagesOnScreen) - 1) * this.maxPagesOnScreen + 1;

        this.endPageOnScreen = this.startPageOnScreen + this.maxPagesOnScreen - 1;
        if (this.endPageOnScreen > totalPages) {
            this.endPageOnScreen = totalPages;
        }

        if (this.endPageOnScreen < totalPages) {
            this.hasNext = true;
        }

        if (this.startPageOnScreen > this.maxPagesOnScreen) {
            this.hasPrev = true;
        }
    }

    public Pagination() {
    }

    public Pagination(int page) {
        this.page = page;
    }

    public Pagination(int page, int maxRecordsPerPage) {
        this.page = page;
        this.maxRecordsPerPage = maxRecordsPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxRecordsPerPage() {
        return maxRecordsPerPage;
    }

    public void setMaxRecordsPerPage(int maxRecordsPerPage) {
        this.maxRecordsPerPage = maxRecordsPerPage;
    }

    public int getMaxPagesOnScreen() {
        return maxPagesOnScreen;
    }

    public void setMaxPagesOnScreen(int maxPagesOnScreen) {
        this.maxPagesOnScreen = maxPagesOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public int getStartPageOnScreen() {
        return startPageOnScreen;
    }

    public int getEndPageOnScreen() {
        return endPageOnScreen;
    }
}

