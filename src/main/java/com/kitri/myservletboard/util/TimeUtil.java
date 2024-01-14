package com.kitri.myservletboard.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    public static String getPassedTime(LocalDateTime from) {
        LocalDateTime to = LocalDateTime.now() ;

        long until = from.until(to, ChronoUnit.MINUTES);

        if (until < 60) {
            if (until < 1) {
                return "방금전";
            }
            return until + "분전";
        }

        until = from.until(to, ChronoUnit.HOURS);
        if (until < 24) {
            return until + "시간전";
        }

        until = from.until(to, ChronoUnit.DAYS);
        if (until < 24) {
            return until + "일전";
        }

        until = from.until(to, ChronoUnit.MONTHS);
        if (until < 12) {
            return until + "개월전";
        }

        until = from.until(to, ChronoUnit.YEARS);
        return until + "년전";
    }
}