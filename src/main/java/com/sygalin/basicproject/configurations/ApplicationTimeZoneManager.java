package com.sygalin.basicproject.configurations;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public interface ApplicationTimeZoneManager {
    OffsetDateTime getTimeZoneOffset(String zoneId);
    LocalDateTime moment(LocalDateTime dateTime,String zoneId);
}
