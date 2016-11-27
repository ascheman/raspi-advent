package net.aschemann.raspi.advent

import org.junit.Test


/**
 * Created by ascheman on 27.11.16.
 */
class AdventComputerTest {
    @Test
    void "test now" () {
        AdventComputer ac = new AdventComputer()
        assert 1 == ac.current()
    }
}
