package net.aschemann.raspi.advent

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalOutput
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import groovy.util.logging.Slf4j

/**
 * Created by ascheman on 26.11.16.
 */
@Slf4j
class AdventMain {


    public static void main (String[] args) {
        log.info ("Starting into Advent")

        AdventController controller = new AdventController()

        while (true) {
            // Wer kennt nicht das tolle deutsche Gedicht?
            controller.reset()
            // Advent, Advent, ein Lichtlein brennt
            controller.advent(1)
            // Erst eins
            controller.advent(2)
            // Dann zwei
            controller.advent(3)
            // Dann drei
            controller.advent(4)
            // Dann vier
            controller.over()
            // Und wenn das fünfte Lichtlein brennt,
            // Dann hast du Weihnachten verpennt!

            controller.reset()
            // So jetzt nochmal den "richtigen" Advent anzeigen!
            controller.current()

            Thread.sleep(15000)
            log.info ("Starting the loop again!")
        }
        controller.stop()

        log.info("Advent is OVER - see you next year!")
    }
}
