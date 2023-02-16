package dk.serik.recipes.testutil;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OffsetDateTimeProvider {

    public final static ZoneId COPENHAGEN_ZONE_ID = ZoneId.of("Europe/Copenhagen");
//    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm", Locale.forLanguageTag("da-DK"));


    public static OffsetDateTime provide(String timestamp) {
        java.time.format.DateTimeFormatter parser = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        java.time.LocalDateTime dt = java.time.LocalDateTime.parse(timestamp, parser);
        ZonedDateTime zdt = ZonedDateTime.of(dt, COPENHAGEN_ZONE_ID);
        return OffsetDateTime.from(zdt);
//        OffsetDateTime offsetDateTime = ZonedDateTime.parse(timestamp, formatter).toOffsetDateTime();
//        return offsetDateTime;
    }
}
