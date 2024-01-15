package com.kitri.myservletboard.util;

import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        buildLimit(pagination);
        return sql;
    }

    private static String buildOrderBy(Search search) {
        String query = "";
        if (search.getOrderBy().equals("latest")) {
            query = " ORDER BY created_at DESC ";
        } else if (search.getOrderBy().equals("views")) {
            query = " ORDER BY view_count DESC ";
        } else if (search.getOrderBy().equals("accuracy")) {
            query = " ORDER BY CASE WHEN " + search.getType() + "='" + search.getKeyword() + "' THEN 0 " +
                    "WHEN " + search.getType() + " LIKE '" + search.getKeyword() + "%' THEN 1 " +
                    "WHEN " + search.getType() + " LIKE '%" + search.getKeyword() + "%' THEN 2 " +
                    "WHEN " + search.getType() + " LIKE '%" + search.getKeyword() + "' THEN 3 " +
                    "ELSE 4 END ";
        }
        return query;
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
