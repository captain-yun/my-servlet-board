package com.kitri.myservletboard.util;

import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

public class QueryUtil {
    public static String buildCountQuery(Search search) {
        String sql = "";

        if (search.getType().equals("title")) {
            sql = "SELECT count(*) FROM board WHERE title LIKE '%" + search.getKeyword() +"%'";
        } else if (search.getType().equals("writer")) {
            sql = "SELECT count(*) FROM board WHERE writer='" + search.getKeyword() + "'";
        } else {
            sql = "SELECT count(*) FROM board";
        }

        return sql;
    }
    public static String buildGetBoardsQuery(Pagination pagination, Search search) {
        String sql = "";

        if (search.getType().equals("title")) {
            sql = "SELECT * FROM board WHERE title LIKE '%" + search.getKeyword() +"%'";
        } else if (search.getType().equals("writer")) {
            sql = "SELECT * FROM board WHERE writer='" + search.getKeyword() + "'";
        } else {
            sql = "SELECT * FROM board";
        }

        sql += " LIMIT " + (pagination.getPage() - 1) * pagination.getMaxRecordsPerPage() + ", " + pagination.getMaxRecordsPerPage();

        return sql;
    }
}
