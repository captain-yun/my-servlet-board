package com.kitri.myservletboard.util;

import com.kitri.myservletboard.data.common.Pagination;
import com.kitri.myservletboard.data.common.Search;

import java.time.LocalDate;

public class QueryUtil {
    public static String buildCountQuery(Search search) {
        String sql = "";

        sql += buildSelect(search, "count(*)");
        sql += buildPeriod(search.getPeriod());

        return sql;
    }

    public static String buildGetBoardsQuery(Pagination pagination, Search search) {
        String sql = "";

        sql += buildSelect(search, "*");
        sql += buildPeriod(search.getPeriod());
        sql += buildOrderBy(search);
        sql += buildLimit(pagination);

        return sql;
    }

    public static String buildGetCommentsByBoardId() {
        //        SELECT comment.*, member.name FROM comment
        //        JOIN member ON comment.member_id = member.id
        //        WHERE board_id = 175;
        String sql = "SELECT comment.*, member.name, member.login_id, member.id m_id FROM comment\n" +
                "JOIN member ON comment.member_id = member.id\n" +
                "WHERE board_id = ?";
        return sql;
    }

    private static String buildOrderBy(Search search) {
        String orderByQuery = "";
        String sortBy = " ASC";
        if (search.getOrderBy().equals("latest")) {
            orderByQuery = "created_at";
            sortBy = " DESC";
        } else if (search.getOrderBy().equals("views")) {
            orderByQuery = "view_count";
            sortBy = " DESC";
        } else if (search.getOrderBy().equals("accuracy")) {
            orderByQuery = "CASE WHEN " + search.getType() + "='" + search.getKeyword() + "' THEN 0 " +
                    "WHEN " + search.getType() + " LIKE '" + search.getKeyword() + "%' THEN 1 " +
                    "WHEN " + search.getType() + " LIKE '%" + search.getKeyword() + "%' THEN 2 " +
                    "WHEN " + search.getType() + " LIKE '%" + search.getKeyword() + "' THEN 3 " +
                    "ELSE 4 END ";
        }

        return " ORDER BY " + orderByQuery + sortBy;
    }

    private static String buildLimit(Pagination pagination) {
        return " LIMIT " + (pagination.getPage() - 1) * pagination.getMaxRecordsPerPage() + ", " + pagination.getMaxRecordsPerPage();
    }

    private static String buildSelect(Search search, String column) {
        String sql = "";
        if (search.getType().equals("title") && !search.getKeyword().equals("")) {
            sql = "SELECT " + column + " FROM board WHERE title LIKE '%" + search.getKeyword() +"%'";
        } else if (search.getType().equals("writer")) {
            sql = "SELECT " + column + " FROM board WHERE writer='" + search.getKeyword() + "'";
        } else {
            sql = "SELECT " + column + " FROM board WHERE true";
        }
        return sql;
    }

    private static String buildPeriod(String period) {
        if (!period.equals("")) {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now().plusDays(1);
            switch (period) {
                case "day":
                    startDate = startDate.minusDays(1);
                    break;
                case "week":
                    startDate = startDate.minusWeeks(1);
                    break;
                case "month":
                    startDate = startDate.minusMonths(1);
                    break;
                case "halfYear":
                    startDate = startDate.minusMonths(6);
                    break;
                case "year":
                    startDate = startDate.minusYears(1);
                    break;
            }
            return " AND created_at BETWEEN '" + startDate + "' and '" + endDate + "'";
        }
        return "";
    }
}
