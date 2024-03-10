package {{.PackageName}}.configurations;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class ApplicationTimeZoneManagerService implements ApplicationTimeZoneManager{
    @Override
    public OffsetDateTime getTimeZoneOffset(String zoneId) {
        ZoneId zone = ZoneId.of(zoneId);
        return OffsetDateTime.now(zone);
    }

    @Override
    public LocalDateTime moment(LocalDateTime dateTime, String zoneId) {
        OffsetDateTime timeZoneOffset = this.getTimeZoneOffset(zoneId);
        return timeZoneOffset.toLocalTime().atDate(LocalDate.from(dateTime));
    }
}
