package hr.markic.rlc.util;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class TimeUtils {

    public static String formatMs(long ms) {
        String formattedDuration = DurationFormatUtils.formatDuration(ms, "ss.SS");
        String otherFormattedDuration = DurationFormatUtils.formatDuration(ms, DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN);
        return formattedDuration + " seconds";
    }
}
