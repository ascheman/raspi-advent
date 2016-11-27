package net.aschemann.raspi.advent

import groovy.util.logging.Slf4j
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants

/**
 * Created by ascheman on 27.11.16.
 */
@Slf4j
class AdventComputer {
    DateTime now

    public AdventComputer (DateTime now = new DateTime()) {
        this.now = now
    }

    int current () {
        // Christmas in current year!
        DateTime christmas = new DateTime().withMonthOfYear(12).withDayOfMonth(24)
        log.info ("Christmas is {}", christmas)
        // If Christmas is on a sunday it is the fourth of Advent!
        DateTime advent4 = christmas.dayOfWeek() == DateTimeConstants.SUNDAY ? christmas :
                christmas.withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7)
        log.info ("Advent #4 is {}", advent4)
        DateTime advent3 = advent4.minusDays(7)
        log.info ("Advent #3 is {}", advent3)
        DateTime advent2 = advent3.minusDays(7)
        log.info ("Advent #2 is {}", advent2)
        DateTime advent1 = advent2.minusDays(7)
        log.info ("Advent #1 is {}", advent1)
        log.info ("Computing Advent for {}", now)
        if (now.isBefore(advent1.millis) || now.isAfter(christmas)) {
            return 0
        } else if (now.isBefore(advent2.millis)) {
            return 1
        } else if (now.isBefore(advent3.millis)) {
            return 2
        } else if (now.isBefore(advent4.millis)) {
            return 3
        }
        return -1
    }
}
