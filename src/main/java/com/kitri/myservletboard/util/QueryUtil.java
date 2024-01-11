package com.kitri.myservletboard.util;

import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QueryUtil {
    public static String buildCountQuery(Search search) {
        String sql = "";

        if (search.getType().equals("title") && !search.getKeyword().equals("")) {
            sql = "SELECT count(*) FROM board WHERE title LIKE '%" + search.getKeyword() +"%'";
        } else if (search.getType().equals("writer")) {
            sql = "SELECT count(*) FROM board WHERE writer='" + search.getKeyword() + "'";
        } else {
            sql = "SELECT count(*) FROM board WHERE true";
        }

        sql += buildPeriod(search.getPeriod());

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

    public static String buildGetBoardsQuery(Pagination pagination, Search search) {
        String sql = "";

        if (search.getType().equals("title") && !search.getKeyword().equals("")) {
            sql = "SELECT * FROM board WHERE title LIKE '%" + search.getKeyword() +"%'";
        } else if (search.getType().equals("writer")) {
            sql = "SELECT * FROM board WHERE writer='" + search.getKeyword() + "'";
        } else {
            sql = "SELECT * FROM board WHERE true";
        }

        sql += buildPeriod(search.getPeriod());
        sql += " LIMIT " + (pagination.getPage() - 1) * pagination.getMaxRecordsPerPage() + ", " + pagination.getMaxRecordsPerPage();

        return sql;
    }
}
