package net.aschemann.raspi.advent

import groovy.util.logging.Slf4j
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate

/**
 * Created by ascheman on 27.11.16.
 */
@Slf4j
class AdventComputer {
    LocalDate today

    public AdventComputer (LocalDate now = new LocalDate()) {
        this.today = now
    }

    int current () {
        // Christmas in current year!
        LocalDate christmas = new LocalDate().withMonthOfYear(12).withDayOfMonth(24)
        log.info ("Christmas is {}", christmas)
        // If Christmas is on a sunday it is the fourth of Advent!
        LocalDate advent4 = christmas.dayOfWeek() == DateTimeConstants.SUNDAY ? christmas :
                christmas.withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7)
        log.info ("Advent #4 is {}", advent4)
        LocalDate advent3 = advent4.minusDays(7)
        log.info ("Advent #3 is {}", advent3)
        LocalDate advent2 = advent3.minusDays(7)
        log.info ("Advent #2 is {}", advent2)
        LocalDate advent1 = advent2.minusDays(7)
        log.info ("Advent #1 is {}", advent1)
        log.info ("Computing Advent for {}", today)
        if (today.isBefore(advent1) || today.isAfter(christmas)) {
            return 0
        } else if (today.isBefore(advent2)) {
            return 1
        } else if (today.isBefore(advent3)) {
            return 2
        } else if (today.isBefore(advent4)) {
            return 3
        }
        return -1
    }
}
