package net.aschemann.raspi.advent

import org.joda.time.LocalDate
import org.junit.Test


/**
 * Created by ascheman on 27.11.16.
 */
class AdventComputerTest {
    @Test
    void "test first of advent" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2017-12-03"))
        assert 1 == ac.current()
    }

    @Test
    void "test before first of advent" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2017-12-02"))
        assert 0 == ac.current()
    }

    @Test
    void "test christmas 2017 (exactly on 4th advent)" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2017-12-24"))
        assert 4 == ac.current()
    }

    @Test
    void "after christmas 2017" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2017-12-25"))
        assert 0 == ac.current()
    }

    @Test
    void "test second of advent" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2017-12-10"))
        assert 2 == ac.current()
    }

    @Test
    void "test fourth of advent in 2016" () {
        AdventComputer ac = new AdventComputer(new LocalDate("2016-12-21"))
        assert 4 == ac.current()
    }
}
