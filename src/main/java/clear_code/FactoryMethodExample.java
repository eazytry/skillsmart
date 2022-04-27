package clear_code;

import java.time.ZonedDateTime;

public class FactoryMethodExample {
    private Long timeOffset;

    private FactoryMethodExample(){}

    private FactoryMethodExample(Long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public static FactoryMethodExample fromLocal() {
        return new FactoryMethodExample(ZonedDateTime.now().getOffset().getTotalSeconds() / 3600L);
    }

    public static FactoryMethodExample fromZonedDateTime(ZonedDateTime dateTime) {
        return new FactoryMethodExample(dateTime.getOffset().getTotalSeconds() / 3600L);
    }

    public static FactoryMethodExample fromString(String offset) {
        return new FactoryMethodExample(Long.parseLong(offset));
    }

    public Long getTimeOffset() {
        return timeOffset;
    }
}
