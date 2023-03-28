package com.java.backend.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {

    public static ZonedDateTime now() {
        ZoneId zoneId = ZoneId.of("Asia/Saigon");
        return ZonedDateTime.now(zoneId);
    }
}
