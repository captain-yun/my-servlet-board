package com.kitri.myservletboard.data;

public class Pagination {

    private int currentPage;
    private int recordsPerPage = 10;
    private int pagesOnScreen = 5;
    private int startIndex = 0;
    private int totalRecords = 0;

    public Pagination(int currentPage) {
        this.currentPage = currentPage;
    }
    private int startPageOnScreen = 1;
    private int endPageOnScreen = this.startPageOnScreen + pagesOnScreen;
    private boolean hasNext = false;
    private boolean hasPrev = false;
    public void calcPagination() {
        int recordsOnScreen = recordsPerPage * pagesOnScreen; // 50
        int recordsUntilCurrentPage = (currentPage - 1) * recordsPerPage; // 73

        int startPage = pagesOnScreen * (int) Math.floor(recordsUntilCurrentPage / recordsOnScreen) + 1;
        if (startPage > pagesOnScreen) {
            this.hasPrev = true;
        }
        int endPage = startPage + pagesOnScreen - 1;

        if (endPage * recordsPerPage < totalRecords) {
            this.hasNext = true;
        } else {
            int recordsRemained = totalRecords - recordsUntilCurrentPage;;
            endPage = currentPage + (int) Math.floor((recordsRemained - 1) / recordsPerPage);
        }

        this.startPageOnScreen = startPage;
        this.endPageOnScreen = endPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPagesOnScreen() {
        return pagesOnScreen;
    }

    public void setPagesOnScreen(int pagesOnScreen) {
        this.pagesOnScreen = pagesOnScreen;
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

    public int getStartPageOnScreen() {
        return startPageOnScreen;
    }

    public void setStartPageOnScreen(int startPageOnScreen) {
        this.startPageOnScreen = startPageOnScreen;
    }

    public int getEndPageOnScreen() {
        return endPageOnScreen;
    }

    public void setEndPageOnScreen(int endPageOnScreen) {
        this.endPageOnScreen = endPageOnScreen;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }
}
