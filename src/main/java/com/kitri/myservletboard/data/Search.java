package com.kitri.myservletboard.data;

public class Search {
    private String type;
    private String keyword;
    private String period;
    private String orderBy;

    public Search() {
    }

    public Search(String type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }

    public Search(String type, String keyword, String period) {
        this.type = type;
        this.keyword = keyword;
        this.period = period;
    }

    public Search(String type, String keyword, String period, String orderBy) {
        this.type = type;
        this.keyword = keyword;
        this.period = period;
        this.orderBy = orderBy;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getPeriod() {
        return period;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean isEmpty() {
        if (this.type.equals("true")) {
            return true;
        }
        return false;
    }

}
