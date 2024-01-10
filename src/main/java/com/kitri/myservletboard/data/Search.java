package com.kitri.myservletboard.data;

public class Search {
    private String type;
    private String keyword;

    public Search() {
    }

    public Search(String type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public boolean isEmpty() {
        if (this.type.equals("true")) {
            return true;
        }
        return false;
    }

}
